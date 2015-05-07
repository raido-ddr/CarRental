package test.com.raido.rental.daotest;


import com.raido.rental.dao.UserDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.dao.pool.ConnectionPool;
import com.raido.rental.entity.User;
import com.raido.rental.logic.util.hash.MessageDigestHelper;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import test.com.raido.rental.daotest.provider.AuthorizationDataProvider;
import test.com.raido.rental.daotest.provider.UserDataProvider;
import test.com.raido.rental.daotest.provider.UserProvider;

@RunWith(JUnitParamsRunner.class)
public class MySqlUserDaoParamsRunnerTest {

    private static  UserDao userDao;

    @BeforeClass
    public static void setUp() throws Exception {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        connectionPool.initialize();


        DaoFactory daoFactory = DaoFactory.getInstance();
        userDao = daoFactory.getUserDao();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        ConnectionPool.getInstance().dispose();
    }

    @Test
    @Parameters(source = AuthorizationDataProvider.class,
        method = "provideCorrectData")
    public void TryAuthorizeExistingUser(String login, String password) throws DaoException {
        String hashedPassword =  MessageDigestHelper.getInstance()
                .getMd5Hash(password);

        User user = doAuthorizationTest(login, hashedPassword);

        Assert.assertNotNull("Failed to authorize user.", user);

    }

    @Test
    @Parameters(source = AuthorizationDataProvider.class,
            method = "provideIncorrectData")
    public void TryAuthorizeNonexistentUser(String login, String password) throws DaoException {
        String hashedPassword =  MessageDigestHelper.getInstance()
                .getMd5Hash(password);

        User user = doAuthorizationTest(login, hashedPassword);

        Assert.assertNull("Failed to authorize user.", user);
    }

    @Test(expected = DaoException.class)
    @Parameters(source = UserProvider.class, method = "provideUser")
    public void TryRegisterUserWithDuplicateLogin(User user) throws DaoException {

        int userId = doRegistrationTest(user);

    }

    @Test
    @Parameters(source = UserDataProvider.class, method = "provideExistingUserId")
    public void TryFindExistingUserById(int id) throws DaoException {

        User user = doFindByIdTest(id);

        Assert.assertNotNull("Failed to find existing user by id.", user);
    }

    @Test
    @Parameters(source = UserDataProvider.class, method = "provideNonexistentUserId")
    public void TryFindNonexistentUserById(int id) throws DaoException {

        User user = doFindByIdTest(id);

        Assert.assertNull("Failed to find existing user by id.", user);
    }

    private User doFindByIdTest(int id) throws DaoException {

        return userDao.findUserById(id);
    }


    private int doRegistrationTest(User user) throws DaoException {

        int id = userDao.createUser(user);
        return 0;
    }

    private User doAuthorizationTest(String login, String hashedPassword) throws DaoException {

        User user = userDao.authorizeUser(login, hashedPassword);
        return user;
    }




    //findbyid




}
