package test.com.raido.rental.testrunner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import test.com.raido.rental.daotest.MySqlUserDaoParamsRunnerTest;
import test.com.raido.rental.daotest.MySqlUserDaoTest;
import test.com.raido.rental.pooltest.DbConnectionPoolTest;


public class TestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DbConnectionPoolTest.class, MySqlUserDaoTest.class,
                MySqlUserDaoParamsRunnerTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
