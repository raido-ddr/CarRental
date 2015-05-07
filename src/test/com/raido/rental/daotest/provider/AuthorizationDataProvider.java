package test.com.raido.rental.daotest.provider;


import static junitparams.JUnitParamsRunner.$;

public class AuthorizationDataProvider {

    public static Object[] provideCorrectData() {
        return $(
                    $("raido", "1234"),
                    $("admin", "1234")
        );
    }

    public static Object[] provideIncorrectData() {
        return $(
                $("raido", "4321"),
                $("unknown", "slkjdfie")
        );
    }

}
