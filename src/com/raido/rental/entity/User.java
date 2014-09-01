package com.raido.rental.entity;

import java.util.Date;

/**
 *
 */
public class User {

    private int id;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String passportNumber;

    private String licenseNumber;

    private Date licenseExpiryDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Date getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(Date licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        if (!dateOfBirth.equals(user.dateOfBirth)) {
            return false;
        }
        if (!firstName.equals(user.firstName)) {
            return false;
        }
        if (!lastName.equals(user.lastName)) {
            return false;
        }
        if (!licenseExpiryDate.equals(user.licenseExpiryDate)) {
            return false;
        }
        if (!licenseNumber.equals(user.licenseNumber)) {
            return false;
        }
        if (!passportNumber.equals(user.passportNumber)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + passportNumber.hashCode();
        result = 31 * result + licenseNumber.hashCode();
        result = 31 * result + licenseExpiryDate.hashCode();
        return result;
    }
}
