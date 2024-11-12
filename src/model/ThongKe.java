/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

public class ThongKe {

    private int maThongKe;
    private Date ngay;
    private int tongHoaDon;
    private BigDecimal tongDoanhThu;
    private ArrayList<HoaDonChiTiet> hoaDonChiTietList;

    // Constructor
    public ThongKe(int maThongKe, Date ngay, int tongHoaDon, BigDecimal tongDoanhThu) {
        this.maThongKe = maThongKe;
        this.ngay = ngay;
        this.tongHoaDon = tongHoaDon;
        this.tongDoanhThu = tongDoanhThu;
        this.hoaDonChiTietList = new ArrayList<>();
    }

    // Phương thức để thêm HoaDonChiTiet vào danh sách
    public void addHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        this.hoaDonChiTietList.add(hoaDonChiTiet);
    }

    // Getter và Setter
    public int getMaThongKe() {
        return maThongKe;
    }

    public void setMaThongKe(int maThongKe) {
        this.maThongKe = maThongKe;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getTongHoaDon() {
        return tongHoaDon;
    }

    public void setTongHoaDon(int tongHoaDon) {
        this.tongHoaDon = tongHoaDon;
    }

    public BigDecimal getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(BigDecimal tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }

    public ArrayList<HoaDonChiTiet> getHoaDonChiTietList() {
        return hoaDonChiTietList;
    }

    public void setHoaDonChiTietList(ArrayList<HoaDonChiTiet> hoaDonChiTietList) {
        this.hoaDonChiTietList = hoaDonChiTietList;
    }

    public Object[] toDataRow() {
        return new Object[]{getMaThongKe(), getNgay(), getTongHoaDon(), getTongDoanhThu()};
    }
;
}
