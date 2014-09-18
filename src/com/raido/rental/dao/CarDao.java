package com.raido.rental.dao;

import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.entity.Car;

import java.util.List;

public abstract class CarDao extends Dao {

    public abstract void createCar(Car car) throws DaoException;

    public abstract Car findCarById(int id) throws DaoException;

    public abstract List<Car> getDamagedCars() throws DaoException;

    public abstract List<Car> getAvailableCars() throws DaoException;

}
