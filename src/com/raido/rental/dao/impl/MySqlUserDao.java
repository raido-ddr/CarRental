package com.raido.rental.dao.impl;

import com.raido.rental.dao.UserDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.pool.ConnectionPool;
import com.raido.rental.dao.pool.exception.ConnectionPoolException;
import com.raido.rental.entity.User;
import com.raido.rental.logic.resourcemanager.MessageBundle;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySqlUserDao extends UserDao {

    private static final Logger LOGGER =
            Logger.getLogger(MySqlUserDao.class);

    private static final String SQL_AUTHORIZE_USER =
            "SELECT * FROM users WHERE login=? AND password=?";

    private static final String SQL_CREATE_USER =
            "INSERT INTO users (first_name, last_name," +
            "login, password, email, role, dob," +
            "license_expiry, passport_number)" +
            "VALUES (?,?,?,?,?,?,?,?,?)";

    private static final String SQL_CHECK_LOGIN_UNIQUENESS =
            "SELECT * FROM users WHERE login=?";

    public static final String SQL_FIND_USER_BY_ID =
            "SELECT * FROM users WHERE id=?";

    private static volatile MySqlUserDao instance;

    private static Lock lock = new ReentrantLock();

    private MySqlUserDao() {}

    public static MySqlUserDao getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new MySqlUserDao();
            }
            lock.unlock();

        }
        return instance;
    }

    /**
     * Inserts a new User entry into the 'users' table
     * of the database.
     *
     * @param user
     * @throws DaoException
     */
    public void createUser(User user) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getPooledConnection();

            preparedStatement =
                    connection.prepareStatement(SQL_CREATE_USER,
                            ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.setDate(7, (Date) user.getDateOfBirth());
            preparedStatement.setDate(8, (Date) user.getLicenseExpiryDate());
            preparedStatement.setString(9, user.getPassportNumber());

            int rowsCount = preparedStatement.executeUpdate();
            if(rowsCount != 1) {
                throw new DaoException(MessageBundle
                        .getString("exception_message", "database.error"));
            }

        } catch (SQLException e) {
            throw new DaoException(MessageBundle
                    .getString("exception_message", "database.error"),e);
        } finally {
            closePooledConnection(connection, preparedStatement);
        }

    }

    /**
     * Finds requested user entry based on id supplied
     * and wraps it in on object of appropriate type.
     *
     * @param id
     * @return User
     * @throws DaoException
     */

    public User findUserById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
             connection = getPooledConnection();
             preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID,
                     ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
             preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            User user = null;
            if(resultSet.next()) {
                user = createUserFromResultSet(resultSet);
            }
            return user;

        } catch (SQLException e) {
            throw new DaoException(MessageBundle
                    .getString("exception_message", "database.error"),e);
        } finally {
            closePooledConnection(connection, preparedStatement);
        }

    }

    /**
     * <p>Attempts to authorize user by searching in the
     * database an entry with the login and the password
     * according to parameters supplied.</p>
     *
     * <p>If the entry is found, then it is wrapped in
     * an object of appropriate type.</p>
     *
     * <p>If entry is not found, then the user is not
     * authorized.</p>
     *
     * @param login
     * @param hashedPassword
     * @return User
     * @throws DaoException
     */
    public User authorizeUser(String login, String hashedPassword)
            throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getPooledConnection();

            preparedStatement =
                    connection.prepareStatement(SQL_AUTHORIZE_USER,
                            ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, hashedPassword);

            resultSet = preparedStatement.executeQuery();

            User user = null;
            if(resultSet.next()) {
                 user = createUserFromResultSet(resultSet);
            } else {
                user = authorizeSuperAdmin(login, hashedPassword, connection);
            }

            return user;

        } catch (SQLException e) {
            throw new DaoException(MessageBundle
                    .getString("exception_message", "database.error"),e);
        } finally {
            closePooledConnection(connection, preparedStatement);
        }
    }


    private User authorizeSuperAdmin(String login, String hashedPassword,
            Connection connection) throws DaoException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            StringBuilder preparedQuery = new StringBuilder();
            preparedQuery.append("SELECT * FROM super_admin WHERE login=? AND password=?");

            preparedStatement =
                    connection.prepareStatement(preparedQuery.toString(),
                            ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, hashedPassword);

            resultSet = preparedStatement.executeQuery();

            User user = null;
            if(resultSet.next()) {
                user = createUserFromResultSet(resultSet);
            }

            return user;

        } catch (SQLException e) {
            throw new DaoException(MessageBundle
                    .getString("exception_message", "database.error"),e);
        } finally {
            closePooledConnection(connection, preparedStatement);
        }
    }

    /**
     * Checks login for uniqueness.
     *
     * @param login
     * @return
     * @throws DaoException
     */

    public boolean isLoginUnique(String login) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getPooledConnection();

            preparedStatement =
                    connection.prepareStatement(SQL_CHECK_LOGIN_UNIQUENESS,
                            ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();

            return (! resultSet.next());

        } catch (SQLException e) {
            throw new DaoException(MessageBundle
                    .getString("exception_message", "database.error"),e);
        } finally {
            closePooledConnection(connection, preparedStatement);
        }


    }

    /**
     * Wraps data from the database in on object
     * of User type.
     *
     * @param resultSet
     * @return User
     * @throws SQLException
     */
    private User createUserFromResultSet(ResultSet resultSet)
            throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setFirstName(resultSet.getString(2));
        user.setLastName(resultSet.getString(3));
        user.setLogin(resultSet.getString(4));
        user.setPassword(resultSet.getString(5));
        user.setEmail(resultSet.getString(6));
        user.setRole(resultSet.getString(7));
        user.setDateOfBirth(resultSet.getDate(8));
        user.setLicenseExpiryDate(resultSet.getDate(9));
        user.setPassportNumber(resultSet.getString(10));

        return user;
    }



}
