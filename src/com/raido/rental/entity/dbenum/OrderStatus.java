package com.raido.rental.entity.dbenum;


public enum OrderStatus {

    NEW,
    CONFIRMED,
    REJECTED,
    ARCHIVED;

    private String value = this.toString().toLowerCase();

    public String getValue() {
        return value;
    }


}
