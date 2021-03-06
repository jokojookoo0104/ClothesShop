/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import models.ChiTietPhieuNhap;
import models.PhieuNhap;
import static controller.FXML_PhieuNhapController.stageQuanLyCTPN;
import static controller.FXML_PhieuNhapController.mapn;

import java.io.IOException;
import java.util.Optional;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import controller.FXML_SearchSanPhamController;

/**
 * FXML Controller class
 *
 * @author 15520
 */
public class FXML_ChiTietPhieuNhapController implements Initializable {

    @FXML
    private TableView tablechitietsanpham;
    @FXML
    private TableColumn machitietphieunhap;
    @FXML
    private TableColumn sanpham;
    @FXML
    private TableColumn soluong;
    @FXML
    private TableColumn giavon;
    @FXML
    private TableColumn thanhtien;
    @FXML
    private AnchorPane paneINFO1;
    @FXML
    private JFXTextField txtfimachitiet;
    @FXML
    private JFXTextField txtfisanpham;
    @FXML
    private JFXTextField txtfisoluong;
    @FXML
    private JFXTextField txtfigiavon;
    @FXML
    private JFXTextField txtfithanhtien;
    @FXML
    private JFXButton btnthem;
    private PhieuNhap phieunhap;
    @FXML
    private JFXButton btnluu;
    @FXML
    private JFXButton btnthemsanpham;
    
    public static Stage stageQuanLySearchSanPham;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        InitTableView();
        
    }

    public void InitTableView() {
        ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap();
        ObservableList<ChiTietPhieuNhap> list = ctpn.getTableChiTietPhieuNhap(mapn);
        machitietphieunhap.setCellValueFactory(new PropertyValueFactory("machitietphieunhap"));
        sanpham.setCellValueFactory(new PropertyValueFactory("masanpham"));
        soluong.setCellValueFactory(new PropertyValueFactory("soluong"));
        giavon.setCellValueFactory(new PropertyValueFactory("giavon"));
        thanhtien.setCellValueFactory(new PropertyValueFactory("thanhtien"));
        tablechitietsanpham.setItems(list);
    }

    @FXML
    private void key_soluong(KeyEvent event) {
        if (!txtfigiavon.getText().equals("") && !txtfisoluong.getText().equals("")) {
            int a = Integer.parseInt((txtfigiavon.getText()));
            int b = Integer.parseInt((txtfisoluong.getText()));
            int c = a * b;
            txtfithanhtien.setText("" + c);
        }
    }

    @FXML
    private void key_giavon(KeyEvent event) {
        if (!txtfigiavon.getText().equals("") && !txtfisoluong.getText().equals("")) {
            int a = Integer.parseInt((txtfigiavon.getText()));
            int b = Integer.parseInt((txtfisoluong.getText()));
            int c = a * b;
            txtfithanhtien.setText("" + c);
        }
    }

    @FXML
    private void Handler_Btnthem(ActionEvent event) {
        int machitiet = Integer.parseInt(txtfimachitiet.getText().toString());
        String masp = txtfisanpham.getText();
        String maphieunhap = Integer.toString(mapn);
        int soluong = Integer.parseInt(txtfisoluong.getText().toString());
        int giavon = Integer.parseInt(txtfigiavon.getText().toString());
        int thanhtien = Integer.parseInt(txtfithanhtien.getText().toString());
        ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(machitiet, masp, maphieunhap, soluong, giavon, thanhtien);
        ctpn.ThemChiTietPhieuNhap();
        InitTableView();
    }

    private void handler_thoat(ActionEvent event) {

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
      
    }

    @FXML
    private void handler_luu(ActionEvent event) {
        int machitiet = Integer.parseInt(txtfimachitiet.getText().toString());
        String masp = txtfisanpham.getText();
        String maphieunhap = Integer.toString(mapn);
        int soluong = Integer.parseInt(txtfisoluong.getText().toString());
        int giavon = Integer.parseInt(txtfigiavon.getText().toString());
        int thanhtien = Integer.parseInt(txtfithanhtien.getText().toString());
        ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(machitiet, masp, maphieunhap, soluong, giavon, thanhtien);
        ctpn.CapNhatChiTietPhieuNhap(machitiet, maphieunhap);
        InitTableView();
        btnluu.setDisable(true);
        btnthem.setDisable(false);

    }

    @FXML
    private void handler_suachitietphieunhap(ActionEvent event) {
        ChiTietPhieuNhap getSelectedRow = (ChiTietPhieuNhap) tablechitietsanpham.getSelectionModel().getSelectedItem();
        txtfimachitiet.setText(Integer.toString(getSelectedRow.getMachitietphieunhap()));
        txtfisanpham.setText(getSelectedRow.getMasanpham());
        txtfisoluong.setText(Integer.toString(getSelectedRow.getSoluong()));
        txtfigiavon.setText(Integer.toString(getSelectedRow.getGiavon()));
        txtfithanhtien.setText(Integer.toString(getSelectedRow.getThanhtien()));
        btnluu.setDisable(false);
        btnthem.setDisable(true);

    }

    @FXML
    private void handler_xoachitietphieunhap(ActionEvent event) {
        ChiTietPhieuNhap selectedForDeletion = (ChiTietPhieuNhap) tablechitietsanpham.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Mời chọn chi tiết phiếu nhập");
            alert.setTitle("Nhắc nhở");
            alert.setHeaderText(null);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Nhắc nhở");
        alert.setHeaderText(null);
        alert.setContentText("Bạn có muốn xóa chi tiết phiếu nhập mã " + selectedForDeletion.getMachitietphieunhap() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap();
            String maphieunhap = Integer.toString(mapn);
            ctpn.XoaChiTietPhieuNhap(selectedForDeletion.getMachitietphieunhap(), maphieunhap);
            InitTableView();
            System.out.println("Xoa Thanh Cong");
        } else {
            System.out.println("Xoa That Bai");
        }
    }

    @FXML
    private void handler_themsanpham(ActionEvent event) throws IOException {
        try { 
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/FXML_SearchSanPham.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        FXML_SearchSanPhamController wc = loader.<FXML_SearchSanPhamController>getController();

        stage.setOnHidden( (WindowEvent event1)-> {
                txtfisanpham.setText(wc.getData());  
        });
        stage.show();
        stageQuanLySearchSanPham = stage;
        } catch(Exception e) {
           e.printStackTrace();
          }
    }

}
