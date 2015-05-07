package test.com.raido.rental.daotest;

import com.raido.rental.dao.UserDao;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.dao.pool.ConnectionPool;
import com.raido.rental.entity.User;
import com.raido.rental.logic.util.hash.MessageDigestHelper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertNotNull;

@Ignore
@RunWith(Parameterized.class)
public class MySqlUserDaoTest {

    private String login;

    private String password;

    public MySqlUserDaoTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> authorizationData() {
        return Arrays.asList(new Object[][] {
                {"raido", "1234"},
                {"stranger", "sfkj234"},
                {"raido", "4312"}
        });
    }

    @BeforeClass
    public static void setUp() throws Exception {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.initialize();

    }

    @AfterClass
    public static void tearDown() throws Exception {
        ConnectionPool.getInstance().dispose();
    }



    @Test
    public void testAuthorizeUser() throws Exception {

        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.getUserDao();

        String login = this.login;
        String hashedPassword =
                MessageDigestHelper.getInstance().getMd5Hash(this.password);

        User user = userDao.authorizeUser(login, hashedPassword);

        assertNotNull("Failed to authorize user.", user);

    }


}