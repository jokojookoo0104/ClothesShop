/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 *
 * @author 15520
 */
public class ChiTietPhieuNhap {
    private IntegerProperty machitietphieunhap;
    private StringProperty masanpham;
    private StringProperty maphieunhap;
    private IntegerProperty soluong;
    private IntegerProperty giavon;
    private IntegerProperty thanhtien;

    public ChiTietPhieuNhap(int machitietphieunhap, String masanpham, String maphieunhap, int soluong, int giavon, int thanhtien) {
        this.machitietphieunhap =new SimpleIntegerProperty (machitietphieunhap);
        this.masanpham =new SimpleStringProperty (masanpham);
        this.maphieunhap =new SimpleStringProperty (maphieunhap);
        this.soluong =new SimpleIntegerProperty (soluong);
        this.giavon =new SimpleIntegerProperty (giavon);
        this.thanhtien =new SimpleIntegerProperty (thanhtien);
    }

    public int getMachitietphieunhap() {
        return machitietphieunhap.getValue();
    }

    public String getMasanpham() {
        return masanpham.getValue();
    }

    public String getMaphieunhap() {
        return maphieunhap.getValue();
    }

    public int getSoluong() {
        return soluong.getValue();
    }

    public int getGiavon() {
        return giavon.getValue();
    }

    public int getThanhtien() {
        return thanhtien.getValue();
    }

    public void setMachitietphieunhap(IntegerProperty machitietphieunhap) {
        this.machitietphieunhap = machitietphieunhap;
    }

    public void setMasanpham(StringProperty masanpham) {
        this.masanpham = masanpham;
    }

    public void setMaphieunhap(StringProperty maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public void setSoluong(IntegerProperty soluong) {
        this.soluong = soluong;
    }

    public void setGiavon(IntegerProperty giavon) {
        this.giavon = giavon;
    }

    public void setThanhtien(IntegerProperty thanhtien) {
        this.thanhtien = thanhtien;
    }

    
    public ObservableList<ChiTietPhieuNhap> getTableChiTietPhieuNhap(int maphieunhap){      
        ObservableList<ChiTietPhieuNhap> list = FXCollections.observableArrayList(); 
        DBConnection db = new DBConnection();
        Connection con = db.getConnecttion();
        String sql = "SELECT * FROM chitietphieunhap where maphieunhap = ?;";
        
        if(con!=null){
            try{
                PreparedStatement ptm = con.prepareStatement(sql);
                ptm.setInt(1, maphieunhap);
                ResultSet rs = ptm.executeQuery();
                while (rs.next()) {
                    ChiTietPhieuNhap ctpn;
                    ctpn = new ChiTietPhieuNhap(rs.getInt("machitietphieunhap")
                            , rs.getString("masanpham"),rs.getString("maphieunhap"),rs.getInt("soluong"),rs.getInt("giavon"),rs.getInt("thanhtien"));
                    list.add(ctpn);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }  
        }
        return list;
    }
    
    public int getTongTien(int maphieunhap){
        DBConnection db = new DBConnection();
        Connection con = db.getConnecttion();
        int Tong=0;
        String sql = "select SUM(thanhtien) from chitietphieunhap where maphieunhap=?";
        if(con!=null){
            try{
                PreparedStatement ptm = con.prepareStatement(sql);
                ptm.setInt(1, maphieunhap);
                ResultSet rs = ptm.executeQuery();
                Tong=Integer.parseInt(rs.getString("SUM(thanhtien)"));
            }
            catch(Exception e){
                e.printStackTrace();
            }  
        }
        return Tong;
    }
    
    
    public boolean CapNhatChiTietPhieuNhap(int machitietphieunhap, String maphieunhap){
        DBConnection db = new DBConnection();
        Connection con = db.getConnecttion();
        String sql = "update chitietphieunhap set machitietphieunhap = ?, masanpham = ?,maphieunhap = ?,soluong = ?, giavon = ?,thanhtien=?  WHERE machitietphieunhap = ? AND maphieunhap=?;";
        if(con!=null){
            try{
                PreparedStatement ptm = con.prepareStatement(sql);
                ptm.setInt(1, this.machitietphieunhap.getValue());
                ptm.setString(2, this.masanpham.getValue());
                ptm.setString(3,this.maphieunhap.getValue());
                ptm.setInt(4, this.soluong.getValue());
                ptm.setInt(5, this.giavon.getValue());
                ptm.setInt(6, this.thanhtien.getValue());         
                ptm.setInt(7, machitietphieunhap);
                ptm.setString(8, maphieunhap);
                ptm.execute();  
            }
            catch(Exception e){
                e.printStackTrace();
                return false; 
            } 
        }
        return true; 
    }

    public ChiTietPhieuNhap() {
    }

    
    
    public boolean ThemChiTietPhieuNhap(){
        DBConnection db = new DBConnection();
        Connection con = db.getConnecttion();
        String sql = "insert into chitietphieunhap(machitietphieunhap, masanpham,maphieunhap,soluong,giavon,thanhtien)  values (?, ?, ?, ?, ?, ?);";
        if(con!=null){
            try{
                PreparedStatement ptm = con.prepareStatement(sql);
                ptm.setInt(1, this.machitietphieunhap.getValue());
                ptm.setString(2, this.masanpham.getValue());
                ptm.setString(3,this.maphieunhap.getValue());
                ptm.setInt(4, this.soluong.getValue());
                ptm.setInt(5, this.giavon.getValue());
                ptm.setInt(6, this.thanhtien.getValue());
                ptm.execute();  
            }
            catch(Exception e){
                e.printStackTrace();
                return false; 
            } 
        }
        return true; 
    }   
    
    public boolean XoaChiTietPhieuNhap(int machitietphieunhap,String maphieunhap){
        DBConnection db = new DBConnection();
        Connection con = db.getConnecttion();
        String sql = "delete from chitietphieunhap where machitietphieunhap = ? AND maphieunhap=?;";
        if(con!=null){
            try{
                PreparedStatement ptm = con.prepareStatement(sql);
                ptm.setInt(1, machitietphieunhap);
                ptm.setString(2,maphieunhap);
                ptm.execute();  
            }
            catch(Exception e){
                e.printStackTrace();
                return false; 
            } 
        }
        return true; 
    }  
    
        public void LoadSearchSanPhamTableView(TableView table) {
        DBConnection db = new DBConnection();
        Connection con = db.getConnecttion();
        ObservableList<ObservableList> data = FXCollections.observableArrayList();

        if (con != null) {
            try {
                Statement stmnt = con.createStatement();
                ResultSet rs = stmnt.executeQuery("SELECT sanpham.masanpham, sanpham.tensanpham, chitietphieunhap.soluong\n" +
                "FROM sanpham\n" +
                "LEFT JOIN chitietphieunhap ON sanpham.masanpham = chitietphieunhap.masanpham\n" +
                "where soluong is null;");

                for (int i = 0; i < rs.getMetaData().getColumnCount()-1; i++) {
                    final int j = i;
                    TableColumn col = new TableColumn(""+i);
                    
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new ReadOnlyObjectWrapper(param.getValue().get(j));
                        }
                    });

                    table.getColumns().addAll(col);
                }

                while (rs.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    int columnCount = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        row.add(rs.getString(i));
                        
                    }
                    data.add(row);
                }
                table.setItems(data);
            } catch (SQLException ex) {

            }

        }
    }
        
        public void LoadSearchSanPhamDaNhapTableView(TableView table) {
        DBConnection db = new DBConnection();
        Connection con = db.getConnecttion();
        ObservableList<ObservableList> data = FXCollections.observableArrayList();

        if (con != null) {
            try {
                Statement stmnt = con.createStatement();
                ResultSet rs = stmnt.executeQuery("SELECT DISTINCT sanpham.masanpham,sanpham.tensanpham\n" +
                "FROM sanpham\n" +
                "LEFT JOIN chitietphieunhap ON sanpham.masanpham = chitietphieunhap.masanpham\n" +
                "WHERE soluong>0\n" +
                "ORDER BY sanpham.masanpham,sanpham.tensanpham;");

                for (int i = 0; i < rs.getMetaData().getColumnCount()-1; i++) {
                    final int j = i;
                    TableColumn col = new TableColumn(""+i);
                    
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new ReadOnlyObjectWrapper(param.getValue().get(j));
                        }
                    });

                    table.getColumns().addAll(col);
                }

                while (rs.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    int columnCount = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        row.add(rs.getString(i));
                        
                    }
                    data.add(row);
                }
                table.setItems(data);
            } catch (SQLException ex) {

            }

        }
    }
        
        public void LoadSearchSanPhamChuaNhapTableView(TableView table) {
        DBConnection db = new DBConnection();
        Connection con = db.getConnecttion();
        ObservableList<ObservableList> data = FXCollections.observableArrayList();

        if (con != null) {
            try {
                Statement stmnt = con.createStatement();
                ResultSet rs = stmnt.executeQuery("SELECT sanpham.masanpham, sanpham.tensanpham, chitietphieunhap.soluong\n" +
                "FROM sanpham\n" +
                "LEFT JOIN chitietphieunhap ON sanpham.masanpham = chitietphieunhap.masanpham\n" +
                "where soluong is null;");

                for (int i = 0; i < rs.getMetaData().getColumnCount()-1; i++) {
                    final int j = i;
                    TableColumn col = new TableColumn(""+i);
                    
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new ReadOnlyObjectWrapper(param.getValue().get(j));
                        }
                    });

                    table.getColumns().addAll(col);
                }

                while (rs.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    int columnCount = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        row.add(rs.getString(i));
                        
                    }
                    data.add(row);
                }
                table.setItems(data);
            } catch (SQLException ex) {

            }

        }
    }
    
}
