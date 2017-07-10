package aopbn_models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class AopbnResult {
	
	private SimpleStringProperty nodeName;
	private SimpleDoubleProperty probNodeActive;
	private SimpleDoubleProperty probNodeInactive;
	
	public AopbnResult(String nodeName, double probNodeActive, double probNodeInactive){
		this.nodeName = new SimpleStringProperty(nodeName);
		this.probNodeActive = new SimpleDoubleProperty(probNodeActive);
		this.probNodeInactive = new SimpleDoubleProperty(probNodeInactive);
	}
	
	public SimpleStringProperty nodeNameProperty(){
		return this.nodeName;
	}
	
	public SimpleDoubleProperty probNodeActiveProperty(){
		return this.probNodeActive;
	}
	
	public SimpleDoubleProperty probNodeInactiveProperty(){
		return this.probNodeInactive;
	}

}
