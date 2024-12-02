package Family.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Family.model.Menues;
import Family.mysqlconnect.Mydb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MenuService {

	public boolean insertUser(String menu, int amount, String status) {
		String query = "INSERT INTO Menues (menu , amount , status) VALUES (? , ? , ?)";

		try (Connection conn = Mydb.connect(); PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, menu);
			statement.setInt(2, amount);
			statement.setString(3, status);

			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				return true;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updatePerson(Menues person) {

		String query = "UPDATE Menues SET menu = ? , amount = ? WHERE menu_id = ?";
		try (Connection conn = Mydb.connect(); PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, person.getMenu());
			statement.setInt(2, person.getAmount());
			statement.setLong(3, person.getMenu_id());

			return statement.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletePerson(int menu_id) {

		String query = "DELETE FROM Menues WHERE menu_id = ?";

		try (Connection conn = Mydb.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, menu_id);

			int rowsDeleted = stmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();

			return false;

		}
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
				int amount = resultSet.getInt("amount");
				String status = resultSet.getString("status");

				users.add(new Menues(menu_id, menu, amount, status));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

}
