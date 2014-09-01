package com.raido.rental.entity;

/**
 *
 */
public class Car {

    private int id;

    private String make;

    private String model;

    private float mileage;

    private float power;

    private String fuelType;

    private String transmissionType;

    private String bodyStyle;

    private int seatCount;

    private boolean available;

    private boolean deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Car car = (Car) o;

        if (available != car.available) {
            return false;
        }
        if (deleted != car.deleted) {
            return false;
        }
        if (id != car.id) {
            return false;
        }
        if (Float.compare(car.mileage, mileage) != 0) {
            return false;
        }
        if (Float.compare(car.power, power) != 0) {
            return false;
        }
        if (seatCount != car.seatCount) {
            return false;
        }
        if (!bodyStyle.equals(car.bodyStyle)) {
            return false;
        }
        if (!fuelType.equals(car.fuelType)) {
            return false;
        }
        if (!make.equals(car.make)) {
            return false;
        }
        if (!model.equals(car.model)) {
            return false;
        }
        if (!transmissionType.equals(car.transmissionType)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + make.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + (mileage != +0.0f ? Float.floatToIntBits(mileage) : 0);
        result = 31 * result + (power != +0.0f ? Float.floatToIntBits(power) : 0);
        result = 31 * result + fuelType.hashCode();
        result = 31 * result + transmissionType.hashCode();
        result = 31 * result + bodyStyle.hashCode();
        result = 31 * result + seatCount;
        result = 31 * result + (available ? 1 : 0);
        result = 31 * result + (deleted ? 1 : 0);
        return result;
    }
}
