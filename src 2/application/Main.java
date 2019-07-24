package application;
	
import java.io.IOException;

import aopbn_models.AopbnNetwork;
import aopbn_models.SteatosisNetwork;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private Stage primaryStage;
	
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("BISCT: Bayesian Bayesian Inference for Substance and Chemical Toxicity");
			showSBNE();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showSBNE(){
		try{
			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("BISCT.fxml"));
            AnchorPane sbne_overview = (AnchorPane) loader.load(); 
			
			// Set person overview into the center of root layout.
            Scene scene = new Scene(sbne_overview);
            primaryStage.setScene(scene);
            primaryStage.show();
            

			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
