package Family.service;

import java.awt.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

import Family.model.Menues;
import Family.model.Orders;
import Family.model.customer;
import Family.mysqlconnect.Mydb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerService {

	public ObservableList<customer> getAllUser() {

		ObservableList<customer> users = FXCollections.observableArrayList();

		String query = "SELECT c.Customer_id, c.name, c.emailid, c.age, c.City , m.menu , o.order_date "
				+ "FROM customer c " + "JOIN Orders o ON c.Customer_id = o.Customer_id  "
				+ "JOIN Menues m ON o.menu_id =  m.menu_id ";

		try (Connection conn = Mydb.connect();

				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query)) {

			while (resultSet.next()) {

				int Customer_id = resultSet.getInt("Customer_id");
				String name = resultSet.getString("name");
				String emailid = resultSet.getString("emailid");
				int age = Integer.parseInt(resultSet.getString("age"));
				String City = resultSet.getString("City");
				String menus = resultSet.getString("menu");
				String menu = menus.toUpperCase();
				java.sql.Date order_date = resultSet.getDate("order_date");

				users.add(new customer(Customer_id, name, emailid, age, City, menu, order_date));

				//System.out.println("Value Sucessfully get");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public ObservableList<Menues> getAllUsers() {

		ObservableList<Menues> users = FXCollections.observableArrayList();

		String query = "SELECT * FROM Menues";

		try (Connection conn = Mydb.connect();

				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query)) {

			while (resultSet.next()) {

				int menu_id = resultSet.getInt("menu_id");
				String menus = resultSet.getString("menu");
				String menu = menus.toUpperCase();

				users.add(new Menues(menu_id, menu));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public boolean insertUser(String name, String emailid, int age, String City) {
		String query = "INSERT INTO customer (name, emailid, age, City) VALUES (?, ?, ?, ? )";

		try (Connection conn = Mydb.connect(); PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, name);
			statement.setString(2, emailid);
			statement.setInt(3, age);
			statement.setString(4, City);

			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println("Insert");
				return true;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int getMenuId(String menu) {
		int menu_id = -1;
		String query = "SELECT menu_id FROM Menues WHERE menu = ?";

		try (Connection conn = Mydb.connect(); PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, menu);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				menu_id = rs.getInt("menu_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menu_id;
	}

	public static int getname(String emailid) {
		int Customer_id = -1;
		System.out.println("emailid : " + emailid);
		String query = "SELECT Customer_id FROM customer WHERE emailid = ?";

		try (Connection conn = Mydb.connect(); PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, emailid);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				Customer_id = rs.getInt("Customer_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Customer_id;
	}

	public boolean updatePerson(customer person) {

		String query = "UPDATE customer SET name = ? , emailid = ? , age = ? , City = ? WHERE Customer_id = ?";
		try (Connection conn = Mydb.connect(); PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, person.getName());
			statement.setString(2, person.getEmailid());
			statement.setInt(3, person.getAge());
			statement.setString(4, person.getCity());

			statement.setInt(5, person.getCustomer_id());

			return statement.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean insertMenu(LocalDate order_date, String menu, String emailid) {

		int menuId = getMenuId(menu);
		int Customer_id = getname(emailid);

		System.out.println("menuId  " + menuId);
		System.out.println("Customer_id  " + Customer_id);
		System.out.println("order_date  " + order_date);

		if (menuId != -1 && Customer_id != -1) {
			String query = "INSERT INTO Orders(order_date, menu_id , Customer_id) VALUES (?, ? , ?)";

			try (Connection conn = Mydb.connect(); PreparedStatement statement = conn.prepareStatement(query)) {

				statement.setDate(1, java.sql.Date.valueOf(order_date));
				statement.setInt(2, menuId);
				statement.setInt(3, Customer_id);

				int rowsInserted = statement.executeUpdate();

				if (rowsInserted > 0) {
					return true;

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/*
	  public boolean updateMenu(customer person) { int menu_id =
	  getMenuId(person.getMenu());
	 
	  System.out.println("menu_id :  "+menu_id);
	  
	  String query = "UPDATE Menues SET menu = ?  WHERE menu_id = ?"; try
	  (Connection conn = Mydb.connect(); PreparedStatement statement =
	  conn.prepareStatement(query)) {
	  
	  statement.setString(1, person.getMenu());
	  
	  statement.setInt(2, menu_id);
	  
	  return statement.executeUpdate() > 0;
	  
	  } catch (SQLException e) { e.printStackTrace(); return false; }
	  
	 }
	 */
	private boolean isMenuIdValid(String menu) {
		int menu_id = getMenuId(menu);
		String query = "SELECT COUNT(*) FROM Menues WHERE menu_id = ?";

		try (Connection conn = Mydb.connect(); PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setInt(1, menu_id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean updatePerson2(customer person) {

		int menu_id = getMenuId(person.getMenu());
		// int menu_id = person.getMenu_id();

		if (!isMenuIdValid(person.getMenu())) {
			System.out.println("Invalid menu ID. The menu does not exist.");
			return false;
		}

		String query = "UPDATE Orders SET menu_id = ? , order_date = ?  WHERE Customer_id =?";
		try (Connection conn = Mydb.connect(); PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setInt(1, menu_id);
	        statement.setDate(2, person.getOrder_date());
	        statement.setInt(3, person.getCustomer_id());
	        
	        System.out.println();

			System.out.println("Customer_id :  " + person.getCustomer_id() + "--------------" + "Order_date :   "
					+ person.getOrder_date() + "--------------------" + "menu_id :  " + menu_id);
	        int rowsAffected = statement.executeUpdate();
			
			  if (rowsAffected > 0) {
		            System.out.println("Successfully updated the order.");
		            return true;
		        } else {
		            System.out.println("No rows updated. Check if Customer_id exists.");
		            return false;
		        }

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletePerson(int Customer_id) {

		String query = "DELETE FROM customer WHERE Customer_id = ?";

		try (Connection conn = Mydb.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, Customer_id);

			int rowsDeleted = stmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();

			return false;

		}
	}

	public boolean deletePerson2(int Customer_id) {

		String query = "DELETE FROM Orders WHERE Customer_id = ?";

		try (Connection conn = Mydb.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, Customer_id);

			int rowsDeleted = stmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();

			return false;

		}
	}

}
