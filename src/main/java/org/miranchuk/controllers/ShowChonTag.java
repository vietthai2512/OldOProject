package org.miranchuk.controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShowChonTag
{
	Parent root;
	
	public ShowChonTag(Stage stage)
	{
		tryCatch();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void tryCatch()
	{
		try
		{
			root = FXMLLoader.load(getClass().getResource("/fxml/ChonTag.fxml"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}