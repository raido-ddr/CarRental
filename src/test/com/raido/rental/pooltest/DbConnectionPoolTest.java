package test.com.raido.rental.pooltest;


import com.raido.rental.dao.pool.ConnectionPool;
import com.raido.rental.dao.pool.exception.ConnectionPoolException;
import org.junit.*;
import org.junit.rules.Timeout;

import java.sql.Connection;
import java.sql.SQLException;


public class DbConnectionPoolTest {

    private static ConnectionPool connectionPool;

    @Rule
    public Timeout timeout = new Timeout(5000);

    @BeforeClass
    public static void setUp() throws Exception {
        connectionPool = ConnectionPool.getInstance();
        connectionPool.initialize();
    }


    @Test
    public void TestConnectionPoolInitialization() throws ConnectionPoolException, SQLException {

        Connection connection = connectionPool.takeConnection();
        Assert.assertNotNull(connection);
        connection.close();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        connectionPool.dispose();
    }

}
