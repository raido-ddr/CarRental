package test.com.raido.rental.daotest.provider;

import test.com.raido.rental.datagenerator.UserGenerator;

import static junitparams.JUnitParamsRunner.$;

public class UserProvider {

    public static Object[] provideUser() {

        UserGenerator generator = new UserGenerator();

        return $(
            $(generator.GenerateUser())
        );
    }



}
