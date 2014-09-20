package com.raido.rental.dao;

import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.entity.Car;
import com.raido.rental.entity.dbenum.CarStatus;

import java.sql.Date;
import java.util.List;

public abstract class CarDao extends Dao {

    public abstract void createCar(Car car) throws DaoException;

    public abstract Car findCarById(int id) throws DaoException;

    public abstract List<Car> selectAllCars() throws DaoException;

    public abstract List<Car> findCarsByStatus(CarStatus status)
            throws DaoException;

    public abstract void editCar(Car car) throws DaoException;

    public abstract List<Car> findCarsForPeriod(Date startDate, Date returnDate)
            throws DaoException;

}
