package com.raido.rental.dao.impl;

import com.raido.rental.dao.CarDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.entity.Car;
import com.raido.rental.entity.dbenum.CarStatus;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySqlCarDao extends CarDao {

    private static final Logger LOGGER =
            Logger.getLogger(MySqlCarDao.class);

    public static final String SQL_FIND_CAR_BY_ID =
            "SELECT * FROM cars WHERE id=?";

    private static final String SQL_CREATE_CAR =
            "INSERT INTO cars (make, model, mileage, power," +
                    "fuel_type, transmission_type, seat_count," +
                    "daily_cost, body_style, status" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";

    private static final String SQL_FIND_AVAILABLE =
            "SELECT * FROM cars WHERE is_available=?";

    private static final String SQL_FIND_DAMAGED =
            "SELECT * FROM cars WHERE is_damaged=?";

    private static volatile MySqlCarDao instance;

    private static Lock lock = new ReentrantLock();

    private MySqlCarDao() {}

    public static MySqlCarDao getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new MySqlCarDao();
            }
            lock.unlock();

        }
        return instance;
    }


    @Override
    public void createCar(Car car) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getPooledConnection();

            preparedStatement =
                    connection.prepareStatement(SQL_CREATE_CAR,
                            ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setString(1, car.getMake());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setFloat(3, car.getMileage());
            preparedStatement.setFloat(4, car.getPower());
            preparedStatement.setString(5, car.getFuelType());
            preparedStatement.setString(6, car.getTransmissionType());
            preparedStatement.setInt(7, car.getSeatCount());
            preparedStatement.setFloat(8, car.getDailyCost());
            preparedStatement.setString(9, car.getBodyStyle());
            preparedStatement.setString(10, car.getStatus().getStatusValue());

            int rowsCount = preparedStatement.executeUpdate();
            if(rowsCount != 1) {
                ResourceBundle bundle =
                        ResourceBundle.getBundle("exception_message");
                throw new DaoException(bundle.getString("database.error"));
            }

        } catch (SQLException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message");
            throw new DaoException(bundle.getString("database.error"), e);
        } try {
            if(connection != null) {
                connection.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }


    }

    @Override
    public Car findCarById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getPooledConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_CAR_BY_ID,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            Car car = null;
            if(resultSet.next()) {
                car = createCarFromResultSet(resultSet);
            }
            return car;

        } catch (SQLException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message");
            throw new DaoException(bundle.getString("database.error"), e);
        } finally {
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                if(connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    private Car createCarFromResultSet(ResultSet resultSet)
            throws SQLException {
        Car car = new Car();

        car.setId(resultSet.getInt(1));
        car.setMake(resultSet.getString(2));
        car.setModel(resultSet.getString(3));
        car.setMileage(resultSet.getFloat(4));
        car.setPower(resultSet.getFloat(5));
        car.setFuelType(resultSet.getString(6));
        car.setTransmissionType(resultSet.getString(7));
        car.setSeatCount(resultSet.getInt(8));
        car.setDailyCost(resultSet.getFloat(9));
        car.setBodyStyle(resultSet.getString(10));
        car.setStatus(CarStatus.valueOf(resultSet.getString(11)));

        return car;
    }

    @Override
    public List<Car> getAvailableCars() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getPooledConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_AVAILABLE,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setBoolean(1, true);

            resultSet = preparedStatement.executeQuery();

            List<Car> damagedCars = new ArrayList<>();
            while(resultSet.next()) {
                Car car = createCarFromResultSet(resultSet);
                damagedCars.add(car);
            }
            return damagedCars;

        } catch (SQLException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message");
            throw new DaoException(bundle.getString("database.error"), e);
        } finally {
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                if(connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public List<Car> getDamagedCars() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getPooledConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_DAMAGED,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setBoolean(1, true);

            resultSet = preparedStatement.executeQuery();

            List<Car> availableCars = new ArrayList<>();
            while(resultSet.next()) {
                Car car = createCarFromResultSet(resultSet);
                availableCars.add(car);
            }
            return availableCars;

        } catch (SQLException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message");
            throw new DaoException(bundle.getString("database.error"), e);
        } finally {
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                if(connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }



}
