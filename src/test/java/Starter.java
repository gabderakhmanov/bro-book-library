import java.sql.*;

public class Starter {

	public static void main(String[] args) throws ClassNotFoundException {

        String hostName = "jdbc:mysql://localhost:3306/book_library";
        String userName = "user";
        String userPass = "12345";

        Connection conn = null;
        Statement stm = null;
        ResultSet rt = null;

        try {
            conn = DriverManager.getConnection(hostName,userName,userPass);

            String sql = "SELECT * FROM person";

            stm = conn.createStatement();
            rt = stm.executeQuery(sql);

            while (rt.next()) {
                System.out.print(rt.getString("person_id")+" ");
                System.out.print(rt.getString("person_name")+" ");
                System.out.print(rt.getString("person_surname")+" ");
                System.out.print(rt.getString("person_age")+"\n");
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
