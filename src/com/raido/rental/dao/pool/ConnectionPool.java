package com.raido.rental.dao.pool;

import com.raido.rental.dao.pool.exception.ConnectionPoolException;
import com.raido.rental.logic.ResourceName;
import com.raido.rental.logic.util.resourcemanager.MessageBundle;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {

    private static final Logger LOGGER =
            Logger.getLogger(ConnectionPool.class);

    private static final int DEFAULT_POOL_SIZE = 5;

    private static Lock lock = new ReentrantLock();

    private BlockingQueue<Connection> availableConnections;

    private BlockingQueue<Connection> providedConnections;

    private String driverName;

    private String url;

    private String user;

    private String password;

    private int poolSize;

    private static ConnectionPool instance;

    private ConnectionPool() {
        DatabaseResourceManager databaseResourceManager = DatabaseResourceManager.getInstance();

        this.driverName =
                databaseResourceManager.getValue(DatabaseParameterName.DB_DRIVER);
        this.url =
                databaseResourceManager.getValue(DatabaseParameterName.DB_URL);
        this.user =
                databaseResourceManager.getValue(DatabaseParameterName.DB_USER);
        this.password =
                databaseResourceManager.getValue(DatabaseParameterName.DB_PASSWORD);

        try {
            this.poolSize = Integer.parseInt(databaseResourceManager
                    .getValue(DatabaseParameterName.DB_POOL_SIZE));
        } catch (NumberFormatException e) {
            poolSize = DEFAULT_POOL_SIZE;
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new ConnectionPool();
            }
            lock.unlock();

        }
        return instance;
    }

    public void initialize() throws ConnectionPoolException {

        try {
            Class.forName(driverName);
            providedConnections = new ArrayBlockingQueue<>(poolSize);
            availableConnections = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager
                        .getConnection(url, user, password);
                PooledConnection pooledConnection =
                        new PooledConnection(connection);
                availableConnections.add(pooledConnection);
            }

        } catch (SQLException e) {
            throw new ConnectionPoolException(MessageBundle
                    .getString(ResourceName.COMMON_CAPTIONS,"pool.init.failed"), e);

        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException(MessageBundle
                    .getString(ResourceName.COMMON_CAPTIONS,"driver.not.found"), e);
        }
    }

    public void dispose() {
        clearConnectionQueue();
    }

    private void clearConnectionQueue() {
        try {
            closeConnectionsQueue(providedConnections);
            closeConnectionsQueue(availableConnections);
        } catch (SQLException e) {
            LOGGER.error(MessageBundle.getString(ResourceName.COMMON_CAPTIONS,
                    "connection.closing.error"), e);
        }
    }

    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection;

        try {
            connection = availableConnections.take();
            providedConnections.offer(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(MessageBundle
                    .getString(ResourceName.COMMON_CAPTIONS, "db.connection.error"), e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) {

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    public void closeConnection(Connection connection, Statement statement) {

        try {
            if(statement != null)
            {
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        closeConnection(connection);
    }

    private void closeConnectionsQueue(BlockingQueue<Connection> queue)
            throws SQLException {

        Connection connection;

        while ((connection = queue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            ((PooledConnection) connection).reallyClose();
        }
    }

    private class PooledConnection implements Connection {

        private Connection connection;

        public PooledConnection(Connection c) throws SQLException {
            this.connection = c;
            this.connection.setAutoCommit(true);
        }

        public void reallyClose() throws SQLException {
            connection.close();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }


        @Override
        public void close() throws SQLException {
            if (connection.isClosed()) {
                throw new SQLException(MessageBundle
                        .getString(ResourceName.COMMON_CAPTIONS, "closing.closed.connection"));
            }

            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }

            if (! providedConnections.remove(this)) {
                throw new SQLException(MessageBundle
                        .getString(ResourceName.COMMON_CAPTIONS, "pool.release.error"));
            }
            if (! availableConnections.offer(this)) {
                throw new SQLException(MessageBundle
                        .getString(ResourceName.COMMON_CAPTIONS, "pool.release.error"));
            }
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements)
                throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public Statement createStatement(int resultSetType,
                int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType,
                    resultSetConcurrency);
        }

        @Override
        public Statement createStatement(int resultSetType,
                int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {

            return connection.createStatement(resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes)
                throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql)
                throws SQLException {
            return connection.prepareCall(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType,
                int resultSetConcurrency) throws SQLException {

            return connection.prepareCall(sql, resultSetType,
                    resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType,
                int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {

            return connection.prepareCall(sql, resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql)
                throws SQLException {
            return connection.prepareStatement(sql);
        }

        @Override
        public PreparedStatement prepareStatement(String sql,
                int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql,
                int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql,
                String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        @Override
        public PreparedStatement prepareStatement(String sql,
                int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareStatement(sql, resultSetType,
                    resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql,
                int resultSetType, int resultSetConcurrency,
                int resultSetHoldability) throws SQLException {

            return connection.prepareStatement(sql, resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public void rollback()
                throws SQLException {
            connection.rollback();
        }

        @Override
        public void setAutoCommit(boolean autoCommit)
                throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        @Override
        public void setCatalog(String catalog)
                throws SQLException {
            connection.setCatalog(catalog);
        }

        @Override
        public void setClientInfo(String name, String value)
                throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        @Override
        public void setHoldability(int holdability)
                throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public void setReadOnly(boolean readOnly)
                throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public Savepoint setSavepoint()
                throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name)
                throws SQLException {
            return connection.setSavepoint(name);
        }

        @Override
        public void setTransactionIsolation(int level)
                throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface)
                throws SQLException {
            return connection.isWrapperFor(iface);
        }

        @Override
        public <T> T unwrap(Class<T> iface)
                throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public void abort(Executor executor)
                throws SQLException {
            connection.abort(executor);
        }

        @Override
        public int getNetworkTimeout()
                throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public String getSchema()
                throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint)
                throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        @Override
        public void rollback(Savepoint savepoint)
                throws SQLException {
            connection.rollback(savepoint);
        }

        @Override
        public void setClientInfo(Properties properties)
                throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }

        @Override
        public void setNetworkTimeout(Executor executor, int arg1)
                throws SQLException {
            connection.setNetworkTimeout(executor, arg1);
        }

        @Override
        public void setSchema(String schema)
                throws SQLException {
            connection.setSchema(schema);
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> typeMap)
                throws SQLException {
            connection.setTypeMap(typeMap);
        }

    }
}
