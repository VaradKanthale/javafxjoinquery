package Family.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OrdertestymenusController implements Initializable {
	
	   @FXML
	    private Button menu2;

	    @FXML
	    private Button order2;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	 @FXML
	    void Menues(ActionEvent event) {
		 Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("/Family/view/Menu.fxml"));

				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();

			} catch (IOException e) {

				e.printStackTrace();
			}

	    }

	    @FXML
	    void order2(ActionEvent event) {
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

}
