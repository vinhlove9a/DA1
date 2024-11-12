/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class HoaDon {

    private int maHoaDon;
    private int maNhanVien;
    private Date ngayLap;
    private BigDecimal tongTien;
    private String phuongThucThanhToan;
    private ArrayList<HoaDonChiTiet> chiTietList;

    // Constructor
    public HoaDon(int maHoaDon, int maNhanVien, Date ngayLap, BigDecimal tongTien, String phuongThucThanhToan) {
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.chiTietList = new ArrayList<>();
    }

    // Getters and Setters
    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public ArrayList<HoaDonChiTiet> getChiTietList() {
        return chiTietList;
    }

    public void addHoaDonChiTiet(HoaDonChiTiet chiTiet) {
        this.chiTietList.add(chiTiet);
    }

    public Object[] toDataRow() {
        return new Object[]{
            getMaHoaDon(),
            getMaNhanVien(),
            getNgayLap(),
            getTongTien(),
            getPhuongThucThanhToan()
        };
    }

    public void addChiTiet(HoaDonChiTiet chiTiet) {
        this.chiTietList.add(chiTiet);
    }
}
