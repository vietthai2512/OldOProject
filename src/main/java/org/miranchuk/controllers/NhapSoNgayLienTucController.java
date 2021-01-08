package org.miranchuk.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class NhapSoNgayLienTucController
{	
	private static int input = 14; 
	public static boolean isInt = true;
	
    @FXML
    private TextField nhapSoNgayLienTuc;

    @FXML
    private Slider sliderSoNgayLienTuc;
    
    @FXML
    public void pressEnter(ActionEvent event) 
    {
    	
    }
    
    public int getInput()
    {
    	return this.input;
    }
    
    public void initialize()
    {    	
       	sliderSoNgayLienTuc.valueProperty().addListener
    	(
			new ChangeListener<Number>()
			{
				@Override 
				public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue)
				{
					int nhapSoNgay = newValue.intValue();
					input = nhapSoNgay;
					nhapSoNgayLienTuc.setText(String.valueOf(nhapSoNgay));
				}
			}
    	);
    	nhapSoNgayLienTuc.textProperty().addListener
		(
			new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue)
				{
					isInt = true;
					try
					{
						input = Integer.parseInt(newValue);
					}
					catch (NumberFormatException e)
					{
						input = 14;
						isInt = false;
					}
					nhapSoNgayLienTuc.setText(newValue);
				}
			}
			
		);
    }
}
