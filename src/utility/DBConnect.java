
package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnect {
    private static String USERNAME ="sa";
    private static String PASSWORD = "123";
    private static String URL ="jdbc:sqlserver://localhost:1433;databaseName=DB_NhaHangThree;encrypt=true;trustServerCertificate=true;";
    
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    public static void main(String[] args) {
        Connection cn = null;
        try {
            cn = getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(cn!=null){
            System.out.println("Kết nối thành công!");
        }
    }
}
