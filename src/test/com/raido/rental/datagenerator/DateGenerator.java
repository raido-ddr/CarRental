package test.com.raido.rental.datagenerator;

import java.sql.Date;

public class DateGenerator {

    public static java.sql.Date generateLicenseExpiryDate() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Date utilDate = cal.getTime();
        java.sql.Date sqlDate = new Date(utilDate.getTime());

        return sqlDate;
    }

    public static java.sql.Date generateUserDob() {
        return null;
    }
}
