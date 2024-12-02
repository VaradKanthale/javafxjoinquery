package Family.mysqlconnect;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Mydb {

	public static Connection connect() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerorder", "root",
					"manager");
			return conn;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Connection closed");
			return null;
		}

	}

}