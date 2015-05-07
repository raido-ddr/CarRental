package test.com.raido.rental.datagenerator;

import com.raido.rental.entity.User;

/**
 * Created by Raido_DDR on 3/30/2015.
 */
public class UserGenerator {

    public User GenerateUser() {
        User user = new User();

        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("doe@rental.com");
        user.setLogin("raido");
        user.setRole("user");
        user.setPassportNumber("sj879873");
        user.setDateOfBirth(DateGenerator.generateUserDob());
        user.setLicenseExpiryDate(DateGenerator.generateLicenseExpiryDate());

        return user;
    }

}
