import javax.swing.*;
import java.sql.*;

public class Starter {

	public static void main(String[] args) throws ClassNotFoundException {
        String hostName = "jdbc:mysql://localhost:3306/book_library";
        String userName = "user";
        String userPass = "12345";

        SQLconnection con = new SQLconnection(hostName,userName,userPass);

        con.connection();

        JFrame frame = new JFrame("Book-Library");
        JPanel mainPanel = new JPanel();
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane);
        frame.setVisible(true);



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
