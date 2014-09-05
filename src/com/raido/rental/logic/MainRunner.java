package com.raido.rental.logic;

import com.raido.rental.dao.AdminDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.dao.pool.ConnectionPool;
import com.raido.rental.dao.pool.exception.ConnectionPoolException;
import com.raido.rental.entity.User;

import java.sql.*;


public class MainRunner {
    public static void main(String[] args) {

        /*Class.forName("org.gjt.mm.mysql.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/car_rental",
                "raido", "1234");*/

        getUser();

    }

    private static void addUser() {

        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.initPoolData();
            Connection conn = connectionPool.takeConnection();
            //Connection conn2 = connectionPool.takeConnection();
            if(conn != null) System.out.println("conn established");
            //if(conn2 != null) System.out.println("conn2 established");

            AdminDao adminDao = DaoFactory.getInstance().getAdminDao();

            User user = new User();
            user.setFirstName("Вася");
            user.setLastName("Пупкин");
            user.setLogin("pupkin1");
            user.setPassword("asdf");
            user.setRole("user");
            user.setDateOfBirth(Date.valueOf("1987-10-01"));
            user.setLicenseExpiryDate(Date.valueOf("2015-12-25"));
            user.setPassportNumber("DT392874");
            user.setLicenseNumber("76823462");

            try {
                adminDao.createUser(user);
            } catch (DaoException e) {
                System.out.println("creation failed");
                e.printStackTrace();
            }
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

    }

    private static void getUser() {
        Connection conn;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.initPoolData();
            conn = connectionPool.takeConnection();

            AdminDao adminDao = DaoFactory.getInstance().getAdminDao();
            User user = adminDao.findUserById(1);

            System.out.println(user.getFirstName());
            System.out.println(user.getRole());
            System.out.println(user.getDateOfBirth());

        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    private static void addCars() {
        Connection conn = null;
        try {

            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.initPoolData();
            conn = connectionPool.takeConnection();
            //Connection conn2 = connectionPool.takeConnection();
            if(conn != null) System.out.println("conn established");
            //if(conn2 != null) System.out.println("conn2 established");

            AdminDao adminDao = DaoFactory.getInstance().getAdminDao();


            Statement st = conn.createStatement();

            int countRows = st.executeUpdate("INSERT INTO Cars (make, model, mileage, power, fuel_type," +
                    "transmission, seats_count, body_style, is_available, is_deleted)" +
                    " VALUES (\"Skoda\", \"Octavia\", 235.65, 190, " +
                    "\"Gasoline\", \"Manual\", 5, \"Wagon\", true, false)");
             countRows = st.executeUpdate("INSERT INTO Cars (make, model, mileage, power, fuel_type," +
                    "transmission, seats_count, body_style, is_available, is_deleted)" +
                    " VALUES (\"Range Rover\", \"Evoque\", 32.98, 245, " +
                    "\"Diesel\", \"Automatic\", 5, \"SUV\", true, false)");

            countRows = st.executeUpdate("INSERT INTO Cars (make, model, mileage, power, fuel_type," +
                    "transmission, seats_count, body_style, is_available, is_deleted)" +
                    " VALUES (\"Opel\", \"Insignia\", 178.9, 158, " +
                    "\"Diesel\", \"Manual\", 5, \"Wagon\", true, false)");

            countRows = st.executeUpdate("INSERT INTO Cars (make, model, mileage, power, fuel_type," +
                    "transmission, seats_count, body_style, is_available, is_deleted)" +
                    " VALUES (\"Opel\", \"Insignia\", 178.9, 158, " +
                    "\"Diesel\", \"Manual\", 5, \"Wagon\", true, false)");

            System.out.println(countRows);

            ResultSet rs = st.executeQuery("SELECT * FROM cars");

            int id = 0;
            String model = null;
            String  fuelType = null;
            float power = 0;

            if(rs.next()) {
                 id = rs.getInt(1);
                 model = rs.getString(3);
                 fuelType = rs.getString(6);
                 power = rs.getFloat(4);
            }

            System.out.println(id);
            System.out.println(model);
            System.out.println(fuelType);
            System.out.println(power);

            st.executeUpdate("DELETE FROM cars");


        }  catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
