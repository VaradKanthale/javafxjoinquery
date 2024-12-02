package Family.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Family.model.Menues;
import Family.model.customer;
import Family.service.MenuService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController implements Initializable {

	@FXML
	private TableColumn<Menues, Integer> menuidcol;

	@FXML
	private TableColumn<Menues, String> menucol;

	@FXML
	private Button addNew;

	@FXML
	private Button delete;

	@FXML
	private TableView<Menues> menuTableView;

	@FXML
	private Button resfresh;

	@FXML
	private Button save;

	@FXML
	private TextField menus;

	@FXML
	private TextField searchTextField;
	
	 @FXML
	 private TextField Amount2;

	@FXML
	private Button Orders;

	@FXML
	private Button Edit;
	
	 @FXML
	 private Button refresh;
	 
	 
	 @FXML
	 private TableColumn<Menues, Integer> Amountcol;
	 
	   @FXML
	    private TableColumn<Menues, Boolean> statuscol;

	 
	 @FXML
	 private Button Customers;


	MenuService service = new MenuService();

	private ObservableList<Menues> menues = FXCollections.observableArrayList();

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		show();
		listers();
		onRefresh();


	}
	
	  @FXML
	    void onOrder(ActionEvent event) {
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("/Family/view/Customer.fxml"));

				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();

			} catch (IOException e) {

				e.printStackTrace();
			}

	    }

	public void show() {
		menuidcol.setCellValueFactory(new PropertyValueFactory<Menues, Integer>("menu_id"));
		menucol.setCellValueFactory(new PropertyValueFactory<Menues, String>("menu"));
		Amountcol.setCellValueFactory(new PropertyValueFactory<Menues, Integer>("amount"));
		statuscol.setCellValueFactory(new PropertyValueFactory<Menues, Boolean>("status"));	

	}
	Menues selectedItem;
	int menu_id;
	@FXML
	void handleRowClick(MouseEvent event) {
		
		if (event.getClickCount() == 2) {
			selectedItem = menuTableView.getSelectionModel().getSelectedItem();
			menus.setText(selectedItem.getMenu());
			Amount2.setText(String.valueOf(selectedItem.getAmount()));
			
			 menu_id = selectedItem.getMenu_id();

			System.out.println("Select Data");

		}

	}

	public void listers() {

		menus.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				if (menus.getText().isEmpty()) {
				//	showAlert("Please Enter Menu.");
				} else {
					Amount2.requestFocus();
				}
			}
		});
		
		Amount2.setOnKeyPressed(event -> {

				if (event.getCode() == KeyCode.ENTER) {
					if (Amount2.getText().isEmpty()) {
					//	showAlert("Please Enter Amount.");
					} else {
						
						onAdd(null);
					}
			} 
		} );
		
	}

	@FXML
	void onAdd(ActionEvent event) {
		


		if (menus.getText().isEmpty()  || Amount2.getText().isEmpty()) {
			showAlert("All fields must be filled out.");
			return;
		}
		
	
		/*if (!menus.getText().matches("[a-zA-Z$#@_-]+")) {
			showAlert("Name must be a valid string format (letters and spaces only).");
			return;
		} */

		if (!menus.getText().matches("^[a-zA-Z]+(\s[a-zA-Z]+){0,2}$")) {
			showAlert("menu must be a valid string format (letters and spaces only).");
			return;
		}
		if ( Amount2.getText().isEmpty() || ! Amount2.getText().matches("\\d+")) {
			showAlert("Invalid Amount Please enter a valid amount.");

			return;
		}else {
			String status;
			if(menus.getText() != null && Amount2.getText() != null) {
				status = "Is Available";
			}else {
				status = "Not Available";
			}
			Menues menues = new Menues();
			
			menues.setMenu_id(menu_id);
			System.out.println("menu_id : "+menu_id);
			
			 if (menues.getMenu_id() == 0) {
				 boolean insertUser = service.insertUser( menus.getText() ,Integer.parseInt(Amount2.getText()) , status );

					if (insertUser) {
						onRefresh();
						System.out.println("inser Data");
						menus.requestFocus();
						clearData();

					} else {
						showAlert("Not Insert opperation.");
						menus.requestFocus();

					}		            return;
		        }else
		       if(menues.getMenu_id() > 0) {

		    	   menues.setMenu(menus.getText());
		    	   menues.setAmount(Integer.parseInt(Amount2.getText()));
			System.out.println("Menues : "+menues.getMenu());
				boolean updated = service.updatePerson(menues);
				if (updated) {
					onRefresh();
					menu_id = 0;
					System.out.println("Update Data");
					menus.requestFocus();
					clearData();


				} else {
					showAlert("Failed to update person.");
					menus.requestFocus();
					clearData();


				}
			}
		}
		}



	
/*	@FXML
	void editShow(ActionEvent event) {

		if (menus.getText().isEmpty()) {
			showAlert("All fields must be filled out.");
			return;
		}

		String menu = menus.getText();
		if (!menu.matches("[a-zA-Z ]+")) {
			showAlert("Name must be a valid string format (letters and spaces only).");
			return;
		} else {
			
		}

	} */

	

	@FXML
	void onDelete(ActionEvent event) {
		Menues selectedPerson = menuTableView.getSelectionModel().getSelectedItem();
		if (selectedPerson == null) {
			JOptionPane.showMessageDialog(null, "Please select a Menues to delete.");

			return;
		}

		boolean deleted = service.deletePerson(selectedPerson.getMenu_id());
		if (deleted) {
			JOptionPane.showMessageDialog(null, "Menues deleted successfully.");
			onRefresh();

		} else {
			JOptionPane.showMessageDialog(null, "Failed to delete Menues.");
			System.out.println("Not Delete Data");

			onRefresh();

		}
	}

	@FXML
	ObservableList<Menues> onRefresh() {
		menues = service.getAllUsers();

//		persons.forEach(user -> System.out.println(user.getMenu_id() + " - " + user.getMenu() +" - "+user.getAmount() +" - "+ user.isStatus()));
		
		menuTableView.setItems(menues);
		
		clearData();

		return menues;

	}
	void clearData() {
		menus.clear();
		Amount2.clear();	
	}

	@FXML
	void onSearch(KeyEvent event) {

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
