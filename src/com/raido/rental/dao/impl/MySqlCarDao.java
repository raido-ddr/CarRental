package com.raido.rental.dao.impl;

import com.raido.rental.dao.CarDao;
import com.raido.rental.dao.OrderDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.Car;
import com.raido.rental.entity.Order;
import com.raido.rental.entity.dbenum.BodyStyle;
import com.raido.rental.entity.dbenum.CarStatus;
import com.raido.rental.entity.dbenum.FuelType;
import com.raido.rental.entity.dbenum.TransmissionType;
import com.raido.rental.logic.ResourceName;
import com.raido.rental.logic.util.resourcemanager.MessageBundle;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
                    "daily_cost, body_style, status)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";

    private static final String SQL_FIND_BY_STATUS =
            "SELECT * FROM cars WHERE status=?";

    private static final String SQL_SELECT_ALL =
            "SELECT * FROM cars";

    private static final String SQL_UPDATE_CAR =
            "UPDATE cars set make=?, model=?, mileage=?," +
                    "power=?, fuel_type=?, transmission_type=?," +
                    "seat_count=?, daily_cost=?, body_style=?," +
                    "status=? WHERE id=?";

    private static  MySqlCarDao instance;

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
            preparedStatement.setString(5, car.getFuelType().getValue());
            preparedStatement.setString(6, car.getTransmissionType().getValue());
            preparedStatement.setInt(7, car.getSeatCount());
            preparedStatement.setFloat(8, car.getDailyCost());
            preparedStatement.setString(9, car.getBodyStyle().getValue());
            preparedStatement.setString(10, car.getStatus().getValue());

            int rowsCount = preparedStatement.executeUpdate();
            if(rowsCount != 1) {
                throw new DaoException(MessageBundle
                        .getString(ResourceName.COMMON_CAPTIONS, "database.error"));
            }

        } catch (SQLException e) {
            throw new DaoException(MessageBundle
                    .getString(ResourceName.COMMON_CAPTIONS, "database.error"),e);
        } finally {
            closePooledConnection(connection, preparedStatement);
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
            throw new DaoException(MessageBundle
                    .getString(ResourceName.COMMON_CAPTIONS, "database.error"),e);
        } finally {
            closePooledConnection(connection, preparedStatement);
        }
    }

    @Override
    public List<Car> selectAllCars() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getPooledConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            resultSet = preparedStatement.executeQuery();

            List<Car> cars = new ArrayList<>();
            while(resultSet.next()) {
                Car car = createCarFromResultSet(resultSet);
                cars.add(car);
            }
            return cars;

        } catch (SQLException e) {
            throw new DaoException(MessageBundle
                    .getString(ResourceName.COMMON_CAPTIONS, "database.error"),e);
        } finally {
            closePooledConnection(connection, preparedStatement);
        }
    }

    public List<Car> findCarsByStatus(CarStatus status) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getPooledConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_STATUS,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setString(1, status.getValue());

            resultSet = preparedStatement.executeQuery();

            List<Car> cars = new ArrayList<>();
            while(resultSet.next()) {
                Car car = createCarFromResultSet(resultSet);
                cars.add(car);
            }
            return cars;

        } catch (SQLException e) {
            throw new DaoException(MessageBundle
                    .getString(ResourceName.COMMON_CAPTIONS, "database.error"),e);
        } finally {
            closePooledConnection(connection, preparedStatement);
        }

    }

    @Override
    public void editCar(Car car) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getPooledConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_CAR,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setString(1, car.getMake());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setFloat(3, car.getMileage());
            preparedStatement.setFloat(4, car.getPower());
            preparedStatement.setString(5, car.getFuelType().getValue());
            preparedStatement.setString(6, car.getTransmissionType().getValue());
            preparedStatement.setInt(7, car.getSeatCount());
            preparedStatement.setFloat(8, car.getDailyCost());
            preparedStatement.setString(9, car.getBodyStyle().getValue());
            preparedStatement.setString(10, car.getStatus().getValue());
            preparedStatement.setInt(11, car.getId());

            int rowsCount = preparedStatement.executeUpdate();
            if(rowsCount != 1) {
                throw new DaoException(MessageBundle
                        .getString(ResourceName.COMMON_CAPTIONS, "database.error"));
            }

        } catch (SQLException e) {
            throw new DaoException(MessageBundle
                    .getString(ResourceName.COMMON_CAPTIONS, "database.error"),e);
        } finally {
            closePooledConnection(connection, preparedStatement);
        }
    }

    @Override
    public List<Car> findCarsForPeriod(Date startDate, Date returnDate)
            throws DaoException {

        List<Car> cars = findCarsByStatus(CarStatus.AVAILABLE);

        List<Car> availableCars = new ArrayList<>();
        for(Car car : cars) {
            if( isCarAvailableForPeriod(car.getId(), startDate, returnDate)) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    private boolean isCarAvailableForPeriod(int carId,
            Date startDate, Date returnDate) throws DaoException {

        boolean isAvailable = true;
        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        List<Order> carOrders = orderDao.findOrdersByCarId(carId);
        for(Order order : carOrders) {
            if(order.overlayPeriod(startDate, returnDate)) {
                isAvailable = false;
                break;
            }
        }

        return isAvailable;
    }

    private Car createCarFromResultSet(ResultSet resultSet)
            throws SQLException {
        Car car = new Car();

        car.setId(resultSet.getInt("id"));
        car.setMake(resultSet.getString("make"));
        car.setModel(resultSet.getString("model"));
        car.setMileage(resultSet.getFloat("mileage"));
        car.setPower(resultSet.getFloat("power"));
        car.setFuelType(FuelType
                .valueOf(resultSet.getString("fuel_type").toUpperCase()));
        car.setTransmissionType(TransmissionType
                .valueOf(resultSet.getString("transmission_type").toUpperCase()));
        car.setSeatCount(resultSet.getInt("seat_count"));
        car.setDailyCost(resultSet.getFloat("daily_cost"));
        car.setBodyStyle(BodyStyle
                .valueOf(resultSet.getString("body_style").toUpperCase()));
        car.setStatus(CarStatus
                .valueOf(resultSet.getString("status").toUpperCase()));

        return car;
    }


}
