package ex01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainCasss extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("chartFXML.fxml"));
		Parent root = loader.load();
		Scene sc = new Scene(root);
		
		
		arg0.setScene(sc);
		arg0.show();  
	}
	public static void main(String[] args) {
		launch(args);
	}
}
