package org.miranchuk.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class ChonCPController 
{
    @FXML private ChoiceBox chonCPChoiceBox;
    @FXML private Button nextButton;

    public void initialize()
    {
    	final ObservableList<String> cophieulist = FXCollections.observableArrayList("VN-Index", "MSN", "VCB");
    	chonCPChoiceBox.setValue("VN-Index");
    	chonCPChoiceBox.setItems(cophieulist);
    }
    
    @FXML
    private void nextButtonPressed(ActionEvent event) 
    {
    	if (chonCPChoiceBox.getValue() != "VN-Index")
    	{
    		Alert alert = new Alert(AlertType.ERROR, "Chưa có dữ liệu về loại cổ phiếu này!");
    		alert.setHeaderText(null);
    		alert.showAndWait();
    	}
    	else
    	{
    		ShowChonTag show = new ShowChonTag(new Stage());
    		
	    	// Hide this current window
	    	((Node)(event.getSource())).getScene().getWindow().hide();
    	}
    	
    }
}
