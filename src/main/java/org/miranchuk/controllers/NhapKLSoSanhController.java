package org.miranchuk.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NhapKLSoSanhController 
{
	private static double input; 
	public static boolean isDouble = true;

    @FXML
    private TextField nhapKLSoSanh;
    
    public double getInput()
    {
    	return this.input;
    }

    public void initialize()
    {
    	nhapKLSoSanh.textProperty().addListener
    	(
			new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue)
				{
					isDouble = true;
					try
					{
						input = Double.parseDouble(newValue);
					}
					catch (NumberFormatException e)
					{
						isDouble = false;
					}
					nhapKLSoSanh.setText(newValue);
				}
			}
    	);
    }
}
