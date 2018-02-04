import javax.swing.*;
import java.sql.*;

public class Starter {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String hostName = "jdbc:mysql://localhost:3306/book_library";
        String userName = "user";
        String userPass = "12345";

        SQLconnection con = new SQLconnection(hostName,userName,userPass);

        Connection conn = con.connection();

        JFrame frame = new JFrame("Book-Library");
        JPanel mainPanel = new JPanel();

        TableModelA tableModelA = new TableModelA(conn, "book");

        JTable table = new JTable(tableModelA);

        JScrollPane scroller = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.getContentPane().add(scroller);
        frame.pack();
        frame.setVisible(true);

        conn.close();

    }

}
