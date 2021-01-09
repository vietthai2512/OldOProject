package org.miranchuk.controllers;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShowTabPaneWithTextArea
{
	FXMLLoader fxmlLoader;
	Stage stage;
	Parent root;
	//TabPaneWithTextAreaController controller = (TabPaneWithTextAreaController)fxmlLoader.getController();
	
	public void load()
	{
		URL location = getClass().getResource("/org.miranchuk/fxml/TabPaneWithTextArea.fxml");
		fxmlLoader = new FXMLLoader(location);
		try 
		{
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Kết quả");
		stage.show();
	}
	
	public void setText(String text)
	{
		TabPaneWithTextAreaController controller = (TabPaneWithTextAreaController)fxmlLoader.getController();
		controller.setText(text);
	}
	
	public void toFront()
	{
		stage.toFront();
	}
	
	public void addTab(String name, String text)
	{
		TabPaneWithTextAreaController controller = (TabPaneWithTextAreaController)fxmlLoader.getController();
		controller.addTab(name, text);
	}
	
	public void renameFirstTab(String name)
	{
		TabPaneWithTextAreaController controller = (TabPaneWithTextAreaController)fxmlLoader.getController();
		controller.renameFirstTab(name);
	}
	
	public boolean isShowing()
	{
		if (stage != null)
		{
			return stage.isShowing();
		}
		else return false;
	}
}
