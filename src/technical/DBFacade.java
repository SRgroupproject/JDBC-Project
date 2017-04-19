package hotelReception.technical;

import hotelReception.domain.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by suman on 4/18/2017.
 */
public class DBFacade {
    public List<Booking> customerList;

    private Connection conn;

    private  String DBNAME = "databaseName = DB_Hotel;";
    private  String USER;
    private  String PASSWORD;

    public DBFacade(String user, String dbPassword) {
        this.USER = user;
        this.PASSWORD = dbPassword;
    }

    public void connect() {

        try {
            System.out.println("Trying to connect...");
            //the sql server driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //the sql server url incl databasename, user, password
            String connectionUrl = "jdbc:sqlserver://LENOVO-PC\\SQLEXPRESS;"
                    + DBNAME
                    + "user="+USER+";"
                    + "password="+PASSWORD+";";
            //get the sql server database connection
            conn = DriverManager.getConnection(connectionUrl);

            if (!conn.isClosed()) {
                System.out.println("Connected");
            }

            conn.setAutoCommit(false);

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Class Not Found Exception: " + cE.toString());
        }
    }

    /**
     * closes an opened connection
     */
    public void closeConnect() {

        try {
            conn.close();
            System.out.println("Connection closed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("nooo");
        }

    }
    public List<Booking> allBookings() {
        customerList = new ArrayList<>();
        try {
            connect();
            String findCustomer = "EXEC AllBookings ?, ?, ?";

            PreparedStatement pst = conn.prepareStatement(findCustomer);
            pst.setString(1, "");
            pst.setString(2, "");
            pst.setString(3, "");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Booking b = new Booking(
                        rs.getInt("bookingID"),
                        rs.getInt("Roomnumber"),
                        rs.getDouble("Price"),
                        rs.getString("Email"),
                        rs.getString("RoomSize"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Description"),
                        rs.getString("VisitDate"),
                        rs.getString("EndDate"));
                customerList.add(b);
            }
            conn.commit();
        } catch (Exception ex) {
            System.out.println("SQL Exception: " + ex.toString());
            try {
                conn.rollback();
            } catch (Exception e) {
                System.out.println("Rollback failed: " + e.toString());
            }

        }
        return customerList;
    }


    public int getUserType(String Email, String Password) {
        try {
            connect();
            String SPsql = "EXEC LoginInfo ?,?";
            PreparedStatement ps = conn.prepareStatement(SPsql);

            ps.setString(1, Email);
            ps.setString(2, Password);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();
                String usertype = rs.getString(1);
                System.out.println(usertype);

                return Integer.parseInt(usertype);
            } else {
                System.out.println("shit");
            }

        } catch (Exception ex) {
            System.out.println("SQL Exception: " + ex.toString());
            try {
                conn.rollback();
            } catch (Exception e) {
                System.out.println("Rollback failed: " + e.toString());
            }

        }

        return 400;
    }
}
