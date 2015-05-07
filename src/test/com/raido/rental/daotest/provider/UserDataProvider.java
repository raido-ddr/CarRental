package test.com.raido.rental.daotest.provider;

import static junitparams.JUnitParamsRunner.$;


public class UserDataProvider {

    public static Object[] provideExistingUserId() {
        return $(
                $(2)
        );
    }

    public static Object[] provideNonexistentUserId() {
        return $(
                $(98)
        );
    }
}
