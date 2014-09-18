package com.raido.rental.entity.dbenum;

public enum BodyStyle {
    SEDAN,
    HATCHBACK,
    WAGON,
    MPV,
    SUV;

    public String getValue() {
        return this.toString().toLowerCase();
    }
}
