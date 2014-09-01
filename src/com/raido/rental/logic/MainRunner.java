package com.raido.rental.logic;

import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Raido_DDR on 8/29/2014.
 */
public class MainRunner {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/car_rental",
                    "raido", "1234");
            if(conn != null) System.out.println("conn established");

            Statement st = null;
            ResultSet rs = null;
/*

            st = conn.createStatement();
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
*/

            //rs = st.executeQuery("SELECT * FROM cars");



           /* int id = 0;
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
            System.out.println(power);*/

            //st.executeUpdate("DELETE FROM cars");


        } catch (ClassNotFoundException e) {
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
