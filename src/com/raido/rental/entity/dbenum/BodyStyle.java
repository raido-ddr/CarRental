package com.raido.rental.entity.dbenum;

public enum BodyStyle {
    SEDAN,
    HATCHBACK,
    WAGON,
    MPV,
    SUV;

    private String value = this.toString().toLowerCase();

    public String getValue() {
        return value;
    }
}
