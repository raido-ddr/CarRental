package com.raido.rental.entity;

import java.util.Date;

/**
 *
 */

public class Order {

    private int id;

    private int userId;

    private int carId;

    private Date startDate;

    private Date returnDate;

    private boolean confirmed;

    private boolean payed;

    private int rejectionId;

    private int damageId;

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

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public int getRejectionId() {
        return rejectionId;
    }

    public void setRejectionId(int rejectionId) {
        this.rejectionId = rejectionId;
    }

    public int getDamageId() {
        return damageId;
    }

    public void setDamageId(int damageId) {
        this.damageId = damageId;
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
        if (confirmed != order.confirmed) {
            return false;
        }
        if (damageId != order.damageId) {
            return false;
        }
        if (id != order.id) {
            return false;
        }
        if (payed != order.payed) {
            return false;
        }
        if (rejectionId != order.rejectionId) {
            return false;
        }
        if (userId != order.userId) {
            return false;
        }
        if (!returnDate.equals(order.returnDate)) {
            return false;
        }
        if (!startDate.equals(order.startDate)) {
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
        result = 31 * result + (confirmed ? 1 : 0);
        result = 31 * result + (payed ? 1 : 0);
        result = 31 * result + rejectionId;
        result = 31 * result + damageId;
        return result;
    }
}
