package org.miranchuk.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class TabPaneWithTextAreaController 
{
	@FXML
    private TabPane tabPane;

    @FXML
    private TextArea textArea;
    
    @FXML
    private Tab tabName;

    public void initialize()
    {		
    }
    
    public void addTab(String tabName, String text)
    {    	
    	TextArea newTextArea = new TextArea();	
    	newTextArea.setPrefSize(572.0, 342.0);
    	newTextArea.setLayoutY(14.0);
    	newTextArea.setEditable(false);
    	newTextArea.setFocusTraversable(false);
    	
    	AnchorPane tabContent = new AnchorPane();
    	tabContent.setPrefSize(200.0, 180.0);
    	tabContent.setMinSize(0, 0);
    	
    	Tab newTab = new Tab();
    	newTab.setText(tabName);
    	
    	tabContent.getChildren().add(newTextArea);
    	
    	AnchorPane.setTopAnchor(newTextArea, 8.0);
    	AnchorPane.setRightAnchor(newTextArea, 8.0);
    	AnchorPane.setLeftAnchor(newTextArea, 8.0);
    	AnchorPane.setBottomAnchor(newTextArea, 8.0);
    	
    	newTab.setContent(tabContent);
    	
    	if (newTab != null)
    		tabPane.getTabs().add(newTab);
    	newTextArea.setText(text);
    	tabPane.getSelectionModel().select(newTab);
    }
    
    public void setText(String text)
    {
    	textArea.setText(text);
    }
    
    public void renameFirstTab(String name)
    {
    	tabName.setText(name);
    }
}
