package com.raido.rental.entity;

import com.raido.rental.entity.dbenum.OrderStatus;

import java.util.Date;

public class Order {

    private int id;

    private int userId;

    private int carId;

    private Date startDate;

    private Date returnDate;

    private OrderStatus status;

    private float value;

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
}
