package com.raido.rental.entity;

import com.raido.rental.entity.dbenum.OrderStatus;

import java.sql.Date;

public class Order {

    private int id;

    private int userId;

    private int carId;

    private Date startDate;

    private Date returnDate;

    private OrderStatus status;

    private float value;

    private String rejectionReason;

    private String damageDescription;

    private float penaltyAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
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

    public boolean overlayPeriod(Date startDate, Date returnDate) {

        return ! ((startDate.after(this.returnDate))
            || returnDate.before(this.startDate));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        if (carId != order.carId) {
            return false;
        }
        if (id != order.id) {
            return false;
        }
        if (Float.compare(order.penaltyAmount, penaltyAmount) != 0) {
            return false;
        }
        if (userId != order.userId) {
            return false;
        }
        if (Float.compare(order.value, value) != 0) {
            return false;
        }
        if (damageDescription != null ? !damageDescription.equals(order.damageDescription) : order.damageDescription != null) {
            return false;
        }
        if (rejectionReason != null ? !rejectionReason.equals(order.rejectionReason) : order.rejectionReason != null) {
            return false;
        }
        if (!returnDate.equals(order.returnDate)) {
            return false;
        }
        if (!startDate.equals(order.startDate)) {
            return false;
        }
        if (status != order.status) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + carId;
        result = 31 * result + startDate.hashCode();
        result = 31 * result + returnDate.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + (value != +0.0f ? Float.floatToIntBits(value) : 0);
        result = 31 * result + (rejectionReason != null ? rejectionReason.hashCode() : 0);
        result = 31 * result + (damageDescription != null ? damageDescription.hashCode() : 0);
        result = 31 * result + (penaltyAmount != +0.0f ? Float.floatToIntBits(penaltyAmount) : 0);
        return result;
    }
}
