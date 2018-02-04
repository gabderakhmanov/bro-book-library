import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLconnection {
    public static Connection conn;
    public static String hostName;
    public static String userName;
    public static String userPass;

    public SQLconnection(String hostName, String userName,String userPass){
        this.hostName = hostName;
        this.userName = userName;
        this.userPass = userPass;
    }

    public static Connection connection(){
        try {
            if(conn == null) conn = DriverManager.getConnection(hostName,userName,userPass);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return null;
    }

}
