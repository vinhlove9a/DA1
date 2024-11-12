/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
import java.math.BigDecimal;

import java.math.BigDecimal;

public class HoaDonChiTiet {

    private int maSanPham;
    private int soLuong;
    private BigDecimal doanhThuSanPham;

    // Constructor
    public HoaDonChiTiet(int maSanPham, int soLuong, BigDecimal doanhThuSanPham) {
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.doanhThuSanPham = doanhThuSanPham;
    }

    // Getters and Setters
    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDoanhThuSanPham() {
        return doanhThuSanPham;
    }

    public void setDoanhThuSanPham(BigDecimal doanhThuSanPham) {
        this.doanhThuSanPham = doanhThuSanPham;
    }
}
