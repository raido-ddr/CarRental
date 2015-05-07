package com.raido.rental.entity;

import java.sql.Date;

/**
 * Created by Raido_DDR on 21.09.2014.
 */
public class OrderSummary {

    private int orderId;

    private Date startDate;

    private Date returnDate;

    private float orderValue;

    private String rejectionReason;

    private String damageDescription;

    private float penaltyAmount;

    private String userFirstName;

    private String userLastName;

    private Date userDateOfBirth;

    private String userEmail;

    private Date licenseExpiryDate;

    private String carMake;

    private String carModel;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public float getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(float orderValue) {
        this.orderValue = orderValue;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getDamageDescription() {
        return damageDescription;
    }

    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }

    public float getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(float penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Date getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public void setUserDateOfBirth(Date userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(Date licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderSummary summary = (OrderSummary) o;

        if (orderId != summary.orderId) {
            return false;
        }
        if (Float.compare(summary.orderValue, orderValue) != 0) {
            return false;
        }
        if (Float.compare(summary.penaltyAmount, penaltyAmount) != 0) {
            return false;
        }
        if (!carMake.equals(summary.carMake)) {
            return false;
        }
        if (!carModel.equals(summary.carModel)) {
            return false;
        }
        if (damageDescription != null ? !damageDescription.equals(summary.damageDescription)
                : summary.damageDescription != null) {
            return false;
        }
        if (!licenseExpiryDate.equals(summary.licenseExpiryDate)) {
            return false;
        }
        if (rejectionReason != null ? !rejectionReason.equals(summary.rejectionReason)
                : summary.rejectionReason != null) {
            return false;
        }
        if (!returnDate.equals(summary.returnDate)) {
            return false;
        }
        if (!startDate.equals(summary.startDate)) {
            return false;
        }
        if (!userDateOfBirth.equals(summary.userDateOfBirth)) {
            return false;
        }
        if (!userEmail.equals(summary.userEmail)) {
            return false;
        }
        if (!userFirstName.equals(summary.userFirstName)) {
            return false;
        }
        if (!userLastName.equals(summary.userLastName)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + startDate.hashCode();
        result = 31 * result + returnDate.hashCode();
        result = 31 * result + (orderValue != +0.0f
                ? Float.floatToIntBits(orderValue)
                : 0);
        result = 31 * result + (rejectionReason != null
                ? rejectionReason.hashCode()
                : 0);
        result = 31 * result + (damageDescription != null
                ? damageDescription.hashCode()
                : 0);
        result = 31 * result + (penaltyAmount != +0.0f
                ? Float.floatToIntBits(penaltyAmount)
                : 0);
        result = 31 * result + userFirstName.hashCode();
        result = 31 * result + userLastName.hashCode();
        result = 31 * result + userDateOfBirth.hashCode();
        result = 31 * result + userEmail.hashCode();
        result = 31 * result + licenseExpiryDate.hashCode();
        result = 31 * result + carMake.hashCode();
        result = 31 * result + carModel.hashCode();
        return result;
    }
}
