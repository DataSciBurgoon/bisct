package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import aopbn_models.AopbnNetwork;
import aopbn_models.AopbnResult;
import aopbn_models.SteatosisNetwork;
import aopbn_models.SteroidogenesisNetwork;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EventHandlerController implements Initializable, ControlledScreen{
	
	@FXML
    private ComboBox<String> comboBox = new ComboBox<String>();
	
	@FXML private Stage stage;
	@FXML Label fileNameLabel;
	@FXML private TableView<AopbnResult> predictionTable;
	@FXML private TableColumn<AopbnResult, String> columnNodeName;
	@FXML private TableColumn<AopbnResult, Double> columnProbActive;
	@FXML private TableColumn<AopbnResult, Double> columnProbInactive;
	@FXML private ObservableList<AopbnResult> olAopbnResults;
	
	private String aopn;
	private File file;
	
	final FileChooser fileChooser = new FileChooser();
	
	ScreensController myController;
	
	public void handleFileOpenButtonAction(ActionEvent event){
		file = fileChooser.showOpenDialog(stage);
		fileNameLabel.setText(file.getName());
		
	}
	
	public void handleSubmitButtonAction(ActionEvent event){
		System.out.println("file name: " + file.getName());
		Parser parser = new Parser(file);
		if(olAopbnResults != null){
			olAopbnResults.clear();
		}
		
		if(aopn.contentEquals("steatosis")){
			//Run the steatosis model
			SteatosisNetwork steatosisNetwork = new SteatosisNetwork(parser.getEvidence());
			olAopbnResults = steatosisNetwork.getPosteriorProbsForGUI();
		}
		else if(aopn.contentEquals("steroidogenesis")){
			SteroidogenesisNetwork steroidogenesisNetwork = new SteroidogenesisNetwork(parser.getEvidence());
			olAopbnResults = steroidogenesisNetwork.getPosteriorProbsForGUI();
		}
		predictionTable.setItems(olAopbnResults);
		columnNodeName.setCellValueFactory(cellData -> cellData.getValue().nodeNameProperty());
		columnProbActive.setCellValueFactory(cellData -> cellData.getValue().probNodeActiveProperty().asObject());
		columnProbInactive.setCellValueFactory(cellData -> cellData.getValue().probNodeInactiveProperty().asObject());
		
		try{
			
			FileWriter fw = new FileWriter(file.getPath() + "_predictions.txt");
			fw.write("Node Name\tNode Active Probability\tNode Inactive Probability\n");
			for(AopbnResult aopbnresult: olAopbnResults){
				String output = aopbnresult.nodeNameProperty().getValue() + "\t";
				output += aopbnresult.probNodeActiveProperty().getValue().toString() + "\t";
				output += aopbnresult.probNodeInactiveProperty().getValue().toString() + "\n";
				fw.write(output);
				}
			fw.close();
			}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	
	

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		comboBox.getSelectionModel().selectedItemProperty();
		
		
		comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
				if(newValue != null){
					if(newValue.contentEquals("Steatosis")){
						aopn = "steatosis";
						
					}
					else if(newValue.contentEquals("Steroidogenesis")){
						System.out.println("yep");
						aopn = "steroidogenesis";
						
					}
				}
				
			}
			
		});
			
	}




	@Override
	public void setScreenParent(ScreensController screenPage) {
//		myController = screenParent;
		
	}
	
//	@FXML
//	private void goToMain(ActionEvent event){
//		myController.setScreen(ScreensFramework.MAIN_SCREEN);
//	}

}
