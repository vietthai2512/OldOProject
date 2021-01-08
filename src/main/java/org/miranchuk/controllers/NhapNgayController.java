package org.miranchuk.controllers;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class NhapNgayController {

    @FXML
    private DatePicker ngayBatDau;

    @FXML
    private DatePicker ngayKetThuc;
    
    private static LocalDate staticNgayBatDau;
    private static LocalDate staticNgayKetThuc;
    
    public LocalDate getNgayBatDau()
    {
    	return this.staticNgayBatDau;
    }
    
    public LocalDate getNgayKetThuc()
    {
    	return this.staticNgayKetThuc;
    }

    public void initialize()
    {
    	ngayBatDau.valueProperty().addListener((ov, oldValue, newValue) ->
    	{
    		staticNgayBatDau = newValue;
    	});
    	
    	ngayKetThuc.valueProperty().addListener((ov, oldValue, newValue) ->
    	{
    		staticNgayKetThuc = newValue;
    	});
    }
}
