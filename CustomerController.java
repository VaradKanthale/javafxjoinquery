package Family.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Family.model.Menues;
import Family.model.Orders;
import Family.model.customer;
import Family.service.CustomerService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

public class CustomerController implements Initializable {

	@FXML
	private Button addNew;

	@FXML
	private TextField age2;

	@FXML
	private TableColumn<customer, Integer> ages;

	@FXML
	private TableColumn<customer, Date> datecol1;

	@FXML
	private TableColumn<customer, String> Menucol;

	@FXML
	private TextField city2;

	@FXML
	private TableColumn<customer, String> citys;

	@FXML
	private TableColumn<customer, Integer> customerid;

	@FXML
	private TableColumn<customer, String> customername;

	@FXML
	private DatePicker date2;

	@FXML
	private Button delete;

	@FXML
	private Button delete1;

	@FXML
	private TableColumn<customer, String> emailidcol;

	@FXML
	private TextField emailid2;

	@FXML
	private ComboBox<String> menu2;

	@FXML
	private TableView<customer> CustomerTable;

	@FXML
	private TextField name2;

	CustomerService service = new CustomerService();

	private ObservableList<customer> customers = FXCollections.observableArrayList();


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		show();
		listers();
		onRefresh(null);
		show2();

		// handleRowClick(null);
		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 * 
		 * try { order_date = sdf.parse(date2.getAccessibleText()); } catch
		 * (ParseException e) { e.printStackTrace(); }
		 */
	}

	public void show2() {
		List<Menues> menuList = service.getAllUsers();

		for (Menues menu : menuList) {
			menu2.getItems().add(menu.getMenu());

		}
	}

	

	public void show() {
		customerid.setCellValueFactory(new PropertyValueFactory<customer, Integer>("Customer_id"));
		customername.setCellValueFactory(new PropertyValueFactory<customer, String>("name"));
		Menucol.setCellValueFactory(new PropertyValueFactory<customer, String>("menu"));
		datecol1.setCellValueFactory(new PropertyValueFactory<customer, Date>("order_date"));
		emailidcol.setCellValueFactory(new PropertyValueFactory<customer, String>("emailid"));
		ages.setCellValueFactory(new PropertyValueFactory<customer, Integer>("age"));
		citys.setCellValueFactory(new PropertyValueFactory<customer, String>("City"));

	}

	customer selectedItem;
	int	Customer_id;
	@FXML
	void handleRowClick(MouseEvent event) {
		System.out.println("hii");

		if (event.getClickCount() == 2) {
			selectedItem = CustomerTable.getSelectionModel().getSelectedItem();
			name2.setText(selectedItem.getName());
			menu2.setValue(selectedItem.getMenu());
			date2.setValue(selectedItem.getOrder_date().toLocalDate());
			emailid2.setText(selectedItem.getEmailid());
			age2.setText(String.valueOf(selectedItem.getAge()));
			city2.setText(selectedItem.getCity());
			
				Customer_id = selectedItem.getCustomer_id();
				

		}

	}

	public void listers() {

		name2.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				if (name2.getText().isEmpty()) {
				//	showAlert("Please Enter Name.");
				} else {
					emailid2.requestFocus();
				}
			}
		});

		emailid2.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				if (emailid2.getText().isEmpty()) {
				//	showAlert("Please Enter Email id.");
				} else {
					age2.requestFocus();
				}
			}
		});

		age2.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				if (age2.getText().isEmpty()) {
				//	showAlert("Please Enter age.");
				} else {
					city2.requestFocus();
				}
			}
		});

		city2.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				if (city2.getText().isEmpty()) {
				//	showAlert("Please Enter city.");
				} else {
					menu2.requestFocus();
					Platform.runLater(() -> {
						menu2.show();
					});
				}
			}
		});

		menu2.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				if (menu2.getValue() == null) {
					//showAlert("Please Enter Menu.");
				} else {
					date2.requestFocus();
					Platform.runLater(() -> {
						date2.show();
					});
					
				}
			}
		});

		date2.setOnKeyPressed(event -> {
			
				Platform.runLater(() -> {
					date2.show();
				});
			if (event.getCode() == KeyCode.ENTER) {
				if (date2.getValue() == null) {
				//	showAlert("Please Enter date.");
				} else {

					onAdd(null);
				}
			}
		});
	}

	@FXML
	ObservableList<customer> onRefresh(ActionEvent event) {
		customers = service.getAllUser();

//		persons.forEach(user -> System.out.println(
//				user.getCustomer_id() + " - " + user.getName() + " - " + user.getMenu() + " - " + user.getOrder_date()
//						+ " - " + user.getEmailid() + " - " + user.getAge() + " - " + user.getCity()));
		
		CustomerTable.setItems(customers);

		return customers;

	}

	@FXML
	void onAdd(ActionEvent event) {
		System.out.println(date2.getValue() + " =====00=   ");

		if (name2.getText().isEmpty() || emailid2.getText().isEmpty() || age2.getText().isEmpty()
				|| city2.getText().isEmpty() || menu2.getValue() == null || date2.getValue() == null) {
			showAlert("All fields must be filled out.");
			return;
		}
		
		if (!name2.getText().matches("^[a-zA-Z]+(\\s[a-zA-Z]+){0,2}$")) {
			showAlert("Name must be a valid string format (letters and spaces only).");
			return;
		}
		

		if (!emailid2.getText().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
			showAlert("Email_id is Not valid.");
			return;
		}
		if (age2.getText().isEmpty() || !age2.getText().matches("\\d+")) {
			showAlert("Invalid age Please enter a valid age.");

			return;
		}


		String cityes = city2.getText();
		if (!cityes.matches("[a-zA-Z ]+")) {
			showAlert("city must be a valid string format (letters and spaces only).");
			return;
		} else {

			customer customers = new customer();

			customers.setCustomer_id(Customer_id);
			System.out.println("Customer_id : "+Customer_id);
			
			if (customers.getCustomer_id() == 0) {
				
				System.out.println("City value "+ city2.getText());
				boolean insertUser = service.insertUser(name2.getText(), emailid2.getText(), Integer.parseInt(age2.getText()), city2.getText());

				if (insertUser) {

				} else {
					showAlert("Not Insert opperation.");
				}
				
				System.out.println(date2.getValue() + " ======   " + menu2.getValue()  );
				boolean insertUser2 = service.insertMenu(date2.getValue(), menu2.getValue(),emailid2.getText());

				if (insertUser2) {
					clearData();
					System.out.println("Insert 2");
					
					onRefresh(null);
					name2.requestFocus();
					clearData();
				} else {
					showAlert("Not Insert opperation 2.");
					name2.requestFocus();

				}
				}
				
			 else if(customers.getCustomer_id() > 0) {

			
				 customers.setName(name2.getText());
				 customers.setEmailid(emailid2.getText());
				 customers.setAge(Integer.parseInt(age2.getText()));
				 customers.setCity(city2.getText());

				boolean updated = service.updatePerson(customers);
				if (updated) {
				//	System.out.println("Sucessfully update");

				} else {
					showAlert("Failed to update person.");

				}

				customers.setMenu(menu2.getValue());
				System.out.println(menu2.getValue() + "  Menu");
				  
			customers.setOrder_date(java.sql.Date.valueOf(date2.getValue()));				  

				boolean updated2 = service.updatePerson2(customers);

				if (updated2) {
					//System.out.println("Sucessfully update 2");
					onRefresh(null);
					Customer_id = 0;
					name2.requestFocus();
					clearData();
				

				} else {
					showAlert("Failed to update person 2.");
					name2.requestFocus();
					clearData();
					Customer_id = 0;
				
				}

			}

		}
		}
	

	void clearData() {
		name2.clear();
		emailid2.clear();
		age2.clear();
		city2.clear();
		menu2.setValue(null);
		System.out.println("Menu text : "+menu2.getValue());
		date2.setValue(null);
		
		
	}

	@FXML
	void onDelete(ActionEvent event) {
		customer selectedPerson = CustomerTable.getSelectionModel().getSelectedItem();
		if (selectedPerson == null) {
			JOptionPane.showMessageDialog(null, "Please select a Menues to delete.");

			return;
		}

		boolean deleted = service.deletePerson(selectedPerson.getCustomer_id());
		if (deleted) {
			JOptionPane.showMessageDialog(null, "Menues deleted successfully.");
			onRefresh(null);

		} else {
			JOptionPane.showMessageDialog(null, "Failed to delete Menues.");
			onRefresh(null);

		}

		boolean deleted2 = service.deletePerson2(selectedPerson.getCustomer_id());
		if (deleted2) {
			JOptionPane.showMessageDialog(null, "Menues deleted successfully.");
			onRefresh(null);

		} else {
			JOptionPane.showMessageDialog(null, "Failed to delete Menues.");
			onRefresh(null);

		}

	}

	private void showAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void showSuccessDialog(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
