package com.raido.rental.entity.dbenum;

/**
 * Created by Raido_DDR on 18.09.2014.
 */
public enum FuelType {
    GASOLINE,
    DIESEL;

    public String getStatusValue() {
        return this.toString().toLowerCase();
    }
}
