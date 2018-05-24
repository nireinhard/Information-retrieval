package searchengine.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ir
 *
 * @AUTHOR niklasreinhard
 * 24.05.18.
 */
public class JDBC {

    private static String DB_URL_ENV = System.getenv("DB_URL");
    private static String DB_URL = "jdbc:sqlite:/Users/XXX/nyt.sqlite";
    private static String DB_USER = "root";
    private static String DB_PASSWD = "";

    public static Connection getConnection(){
        try {
            if(DB_URL_ENV == null){
                return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            }else {
                return DriverManager.getConnection(DB_URL_ENV, DB_USER, DB_PASSWD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
