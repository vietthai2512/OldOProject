package org.miranchuk.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.miranchuk.cophieu.CoPhieu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.miranchuk.readinput.ReadInput;
import org.miranchuk.taocau.TaoCauGiamLienTuc;
import org.miranchuk.taocau.TaoCauGiamManh;
import org.miranchuk.taocau.TaoCauGiamNhe;
import org.miranchuk.taocau.TaoCauKLGiaoDichBe;
import org.miranchuk.taocau.TaoCauKLGiaoDichLon;
import org.miranchuk.taocau.TaoCauTangLienTuc;
import org.miranchuk.taocau.TaoCauTangManh;
import org.miranchuk.taocau.TaoCauTangNhe;
import org.miranchuk.taocau.TheoDoi;

public class ChonTagController 
{

    @FXML private Button backButton;
    @FXML private Button nextButton;
    @FXML private ListView<String> tagTongQuatListView;
    @FXML private ListView<String> tagChiTietListView;
    @FXML private Button quickTagTangManh;
    @FXML private Button quickTagGiamNhe;
    @FXML private Button quickTagTangLienTuc;
    @FXML private Button quickTagKhoiLuong;
    @FXML private Pane tuyChonPane;
    
    List<CoPhieu> listcp = readInput();
    
    
    private ShowTabPaneWithTextArea show = new ShowTabPaneWithTextArea();
    
    private String[] tagTongQuat = {"Điểm cổ phiếu tăng",
    								"Điểm cổ phiếu giảm",
    								"Theo dõi tình hình cổ phiếu",
    								"Khối lượng giao dịch"};
    
    private String[] tagCPTang = {"Tăng mạnh",
    							  "Tăng nhẹ",
    							  "Tăng liên tục"};
    
    private String[] tagCPGiam = {"Giảm mạnh",
    							  "Giảm nhẹ",
    							  "Giảm liên tục"};
    
    private String[] klGiaoDich = {"Khối lượng giao dịch lớn hơn",
    							   "Khối lượng giao dịch nhỏ hơn"};

    private String[] theoDoiCP = {"Tùy chọn ngày"};
    
    private final ObservableList<String> tagTongQuatList = 
    		FXCollections.observableArrayList();
    
    private final ObservableList<String> tagCPTangList = 
    		FXCollections.observableArrayList();
    
    private final ObservableList<String> tagCPGiamList = 
    		FXCollections.observableArrayList();
    
    private final ObservableList<String> klGiaoDichList = 
    		FXCollections.observableArrayList();
    
    private final ObservableList<String> theoDoiCPList = 
    		FXCollections.observableArrayList();
    
    @FXML
    private void backButtonPressed(ActionEvent event) 
    {
    	ShowChonCP show = new ShowChonCP();
    	try 
    	{
			show.start(new Stage());
		} catch (Exception e)
    	{
			e.printStackTrace();
		}
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public void nextButtonPressed(ActionEvent event) 
    {
    	if (tagChiTietListView.getSelectionModel().isEmpty())
    	{
    		showError("Chưa chọn tag chi tiết!");
    	}
    	else
    	{
    		String temp = new String(tagChiTietListView.getSelectionModel().getSelectedItem());
        	if (temp.equals(tagCPTang[0]))
        	{
        		//showConfirmation("");
        		TaoCauTangManh taocau = new TaoCauTangManh();
        		showOrAddNewTab("Tăng mạnh", taocau.taoCauTang(listcp));    		
        	}
        	else if (temp.equals(tagCPTang[1]))
        	{
        		//showConfirmation(content);
        		TaoCauTangNhe taocau = new TaoCauTangNhe();
        		showOrAddNewTab("Tăng nhẹ", taocau.taoCauTang(listcp));
        	}
        	else if (temp.equals(tagCPTang[2]))
        	{
        		NhapSoNgayLienTucController controller = loadFXML("NhapSoNgayLienTuc.fxml").getController();
        		if ((!controller.isInt) || (controller.getInput() < 2) || (controller.getInput() > 100))
        		{
        			showError("Số ngày không trong khoảng 2-100!");
        		}
        		else
        			showOrAddNewTab("Tăng liên tục " + controller.getInput() + " ngày", new TaoCauTangLienTuc(listcp, controller.getInput()).taoCauTang(listcp));
        	}
        	else if (temp.equals(tagCPGiam[0]))
        	{
        		//showConfirmation(content);
        		TaoCauGiamManh taocau = new TaoCauGiamManh();
        		showOrAddNewTab("Giảm mạnh", taocau.taoCauGiam(listcp));
        	}
        	else if (temp.equals(tagCPGiam[1]))
        	{
        		//showConfirmation(content);
        		TaoCauGiamNhe taocau = new TaoCauGiamNhe();
        		showOrAddNewTab("Giảm nhẹ",taocau.taoCauGiam(listcp));
        	}
        	else if (temp.equals(tagCPGiam[2]))
        	{
        		NhapSoNgayLienTucController controller = loadFXML("NhapSoNgayLienTuc.fxml").getController();
        		if ((!controller.isInt) || (controller.getInput() < 2) || (controller.getInput() > 100))
        		{
        			showError("Số ngày không trong khoảng 2-100!");
        		}
        		else
        			showOrAddNewTab("Giảm liên tục " + controller.getInput() + " ngày", new TaoCauGiamLienTuc(listcp, controller.getInput()).taoCauGiam(listcp));
        	}
        	else if (temp.equals(klGiaoDich[0]))
        	{
        		NhapKLSoSanhController controller = loadFXML("NhapKLSoSanh.fxml").getController();
        		if ((!controller.isDouble) || (controller.getInput() < 0))
        		{
        			showError("Khối lượng so sánh không hợp lệ");
        		}
        		else
        			showOrAddNewTab("Khối lượng giao dịch lớn hơn " + controller.getInput(), new TaoCauKLGiaoDichLon().taoCauKLGiaoDich(listcp, controller.getInput()));
        	}
        	else if (temp.equals(klGiaoDich[1]))
        	{
        		NhapKLSoSanhController controller = loadFXML("NhapKLSoSanh.fxml").getController();
        		if ((!controller.isDouble) || (controller.getInput() < 0))
        		{
        			Alert alert = new Alert(AlertType.ERROR, "Khối lượng so sánh không hợp lệ");
            		alert.setHeaderText(null);
            		alert.showAndWait();
        		}
        		else
        			showOrAddNewTab("Khối lượng giao dịch nhỏ hơn " + controller.getInput(), new TaoCauKLGiaoDichBe().taoCauKLGiaoDich(listcp, controller.getInput()));
        	}
        	else if (temp.equals(theoDoiCP[0]))
        	{
        		NhapNgayController controller = loadFXML("NhapNgay.fxml").getController();
        		
        		LocalDate lastday = LocalDate.of(2020, 4, 24);
        		LocalDate firstday = LocalDate.of(2020, 1, 1);
        		LocalDate batdau = controller.getNgayBatDau();
        		LocalDate ketthuc = controller.getNgayKetThuc();
        		
        		if (controller.getNgayBatDau() == null || controller.getNgayKetThuc() == null)
        		{
        			showError("Ngày bắt đầu hoặc ngày kết thúc không hợp lệ");
        		}
        		else if (batdau.isAfter(lastday) || ketthuc.isAfter(lastday) ||
        				 batdau.isBefore(firstday) || batdau.isBefore(firstday))
        		{
        			showError("Chỉ có dữ liệu từ ngày 1 tháng 1 đến ngày 24 tháng 4 năm 2020");
        		}
        		else if (batdau.isAfter(ketthuc))
        		{
        			showError("Ngày bắt đầu không thể sau ngày kết thúc");
        		}	
        		else
        			showOrAddNewTab("Theo dõi cổ phiếu", new TheoDoi().theoDoi(listcp, batdau.getDayOfMonth(), batdau.getMonthValue(), ketthuc.getDayOfMonth(), ketthuc.getMonthValue()));
        	}
    	}	
    }

    @FXML
    private void taoCauGiamNhe(ActionEvent event) 
    {
    	//showConfirmation(content);
    	TaoCauGiamNhe taocau = new TaoCauGiamNhe();
		showOrAddNewTab("Giảm nhẹ", taocau.taoCauGiam(listcp));
    }

    @FXML
    private void taoCauKLGiaoDichLon(ActionEvent event) 
    {
    	showOrAddNewTab("Khối lượng giao dịch lớn hơn 50.0", new TaoCauKLGiaoDichLon().taoCauKLGiaoDich(listcp, 50.0));
    }

    @FXML
    private void taoCauTangLienTuc(ActionEvent event) 
    {
    	showOrAddNewTab("Tăng liên tục 14 ngày", new TaoCauTangLienTuc(listcp, 14).taoCauTang(listcp));
    }

    @FXML
    private void taoCauTangManh(ActionEvent event) 
    {
    	TaoCauTangManh taocau = new TaoCauTangManh();
		showOrAddNewTab("Tăng mạnh",taocau.taoCauTang(listcp)); 
    }
   
    public void showConfirmation(String content)
    {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Xác nhận");
    	alert.setContentText(content);
    	alert.setHeaderText(null);
    	alert.showAndWait();
    }
    
    public void showError(String content)
    {
    	Alert alert = new Alert(AlertType.ERROR, content);
		alert.setHeaderText(null);
		alert.showAndWait();
    }
    
    public void showOrAddNewTab(String tabName, String text)
    {
    	if (!show.isShowing())
		{
   			show.load();
			show.renameFirstTab(tabName);
			show.setText(text);
		}
		else
		{
			show.addTab(tabName, text);
			show.toFront();
		}
    }
    
    public List<CoPhieu> readInput()
    {
    	List<CoPhieu> listcp = new ArrayList<>();
    	try {
			ReadInput.readInput("src/main/resources/org.miranchuk/data/cophieuData.txt", listcp);
			ReadInput.setNgay_Thang(listcp, 1, 1);
		} catch (IOException e) 
    	{
			Alert alert = new Alert(AlertType.ERROR, "File not found!");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
    	return listcp;
    }
    
    public void clearTuyChonPane()
    {
    	int size = tuyChonPane.getChildren().size();
    	if (size > 1)
    	{
    		for (int i = 1; i < size; i++)
    			tuyChonPane.getChildren().remove(i);
    	}
    }
      
    public void addVBoxToPane(String name)
    {
    	if (tuyChonPane.getChildren().size() == 1)
		{
			VBox newVBox = new VBox();
	    	try
			{
	    		newVBox = FXMLLoader.load(getClass().getResource("/org.miranchuk/fxml/" + name));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	    	tuyChonPane.getChildren().add(newVBox);
		}
    }
    
    public FXMLLoader loadFXML(String name)
    {
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/org.miranchuk/fxml/" + name));
		try
		{
			loader.load();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}    
		
		return loader;
    }
    
    public void initialize()
    {
    	clearTuyChonPane();
    	tagTongQuatList.addAll(tagTongQuat);
    	tagCPTangList.addAll(tagCPTang);
    	tagCPGiamList.addAll(tagCPGiam);
    	klGiaoDichList.addAll(klGiaoDich);
    	theoDoiCPList.addAll(theoDoiCP);
    	
    	tagTongQuatListView.setItems(tagTongQuatList);
    	
    	tagTongQuatListView.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) ->
    	{
    		if(newValue != null && !newValue.isEmpty())
			{
				if (newValue.equals(tagTongQuat[0]))
				{
					clearTuyChonPane();	    					
					tagChiTietListView.setItems(tagCPTangList);
					
					tagChiTietListView.getSelectionModel().selectedItemProperty().addListener
					(
						new ChangeListener<String>()
						{
							@Override
							public void changed(ObservableValue<? extends String>ov, String oldValue, String newValue)
							{
								if(newValue != null && !newValue.isEmpty())
								{
    								if (newValue.equals(tagCPTang[0]))
    								{
    									clearTuyChonPane();
    								}
    								else if (newValue.equals(tagCPTang[1]))
    								{
    									clearTuyChonPane();
    								}
    								else if (newValue.equals(tagCPTang[2]))
    								{
    									clearTuyChonPane();
    									addVBoxToPane("NhapSoNgayLienTuc.fxml");
    								}
								}
								
							}
						}
					);
				}
				else if (newValue.equals(tagTongQuat[1]))
				{
					clearTuyChonPane();	    					
					tagChiTietListView.setItems(tagCPGiamList);
					
					tagChiTietListView.getSelectionModel().selectedItemProperty().addListener
					(
						new ChangeListener<String>()
						{
							@Override
							public void changed(ObservableValue<? extends String>ov, String oldValue, String newValue)
							{
								if(newValue != null && !newValue.isEmpty())
								{
    								if (newValue.equals(tagCPGiam[0]))
    								{
    									clearTuyChonPane();
    								}
    								else if (newValue.equals(tagCPGiam[1]))
    								{
    									clearTuyChonPane();
    								}
    								else if (newValue.equals(tagCPGiam[2]))
    								{
    									clearTuyChonPane();
    									addVBoxToPane("NhapSoNgayLienTuc.fxml");
    								}
								}
							}
						}
					);
				}
				else if (newValue.equals(tagTongQuat[2]))
				{
					clearTuyChonPane();	    					
					tagChiTietListView.setItems(theoDoiCPList);
					
					tagChiTietListView.getSelectionModel().selectedItemProperty().addListener
					(
						new ChangeListener<String>()
						{
							@Override
							public void changed(ObservableValue<? extends String>ov, String oldValue, String newValue)
							{
								if(newValue != null && !newValue.isEmpty())
								{
    								if (newValue.equals(theoDoiCP[0]))
    								{
    									clearTuyChonPane();
    									addVBoxToPane("NhapNgay.fxml");
    								}
								}
							}
						}
					);
				}
				else if (newValue.equals(tagTongQuat[3]))
				{
					clearTuyChonPane();
					tagChiTietListView.setItems(klGiaoDichList);
					
					tagChiTietListView.getSelectionModel().selectedItemProperty().addListener
					(
						new ChangeListener<String>()
						{
							@Override
							public void changed(ObservableValue<? extends String>ov, String oldValue, String newValue)
							{
								if(newValue != null && !newValue.isEmpty())
								{
    								if (newValue.equals(klGiaoDich[0]))
    								{
    									clearTuyChonPane();
    									addVBoxToPane("NhapKLSoSanh.fxml");
    								}
    								else if (newValue.equals(klGiaoDich[1]))
    								{
    									clearTuyChonPane();
    									addVBoxToPane("NhapKLSoSanh.fxml");
    								}
								}
							}
						}
					);
				}
			}
	    });
    }
}

