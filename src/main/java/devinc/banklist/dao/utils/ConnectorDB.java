package devinc.banklist.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectorDB {
    //		System.out.println("Connector started");
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/bank_list?serverTimezone=GMT";
    private static final String username = "root";
    private static final String password = "root";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException (driver load failed): " + e);
        }
        return DriverManager.getConnection(url, username, password);
    }

    public static void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            System.out.println("Statement wasn't closed");
        }
    }

    public static void close(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Statement wasn't closed");
        }
    }
}
