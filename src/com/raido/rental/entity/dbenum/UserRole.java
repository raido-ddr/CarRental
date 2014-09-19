package com.raido.rental.entity.dbenum;

public enum UserRole {

    ADMIN,
    USER,
    GUEST;

    private String value = this.toString().toLowerCase();

    public String getValue() {
        return value;
    }

}
