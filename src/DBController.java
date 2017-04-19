import java.sql.*;

/**
 * Created by Louise Windows on 18-04-2017.
 */
public class DBController {
    private static String userName = "sa";
    private static String password = "lagkage123";

    private static String port = "1433";
    private static String databaseName = "DB_Hotel";

    Connection connection = null;

    public DBController() {
        getConnection();
    }

    public Connection getConnection() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:" + port + ";databaseName=" +
                    databaseName, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public User findUserByUserName(String userName) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM tblSystemUsers WHERE fld_UserName = (?)");
            ps.setString(1, userName);
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }
}


