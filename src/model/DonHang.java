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
import java.sql.Timestamp;

public class DonHang {

    private int maDonHang;
    private int maBan;
    private String tenKhachHang;
    private Timestamp ngayGioDat;
    private BigDecimal tongTien;
    private int maSanPham;
    private int soLuong;
    private BigDecimal thanhTien;
    private String phuongThucThanhToan;
    private String trangThai;
    private int maVoucher;
    // Constructor không tham số
    public DonHang() {
    }

    // Constructor đầy đủ tham số
    public DonHang(int maDonHang, int maBan, String tenKhachHang, Timestamp ngayGioDat,
            BigDecimal tongTien, int maSanPham, int soLuong,
            BigDecimal thanhTien, String phuongThucThanhToan, String trangThai) {
        this.maDonHang = maDonHang;
        this.maBan = maBan;
        this.tenKhachHang = tenKhachHang;
        this.ngayGioDat = ngayGioDat;
        this.tongTien = tongTien;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThai = trangThai;
    }

    public DonHang(int maDonHang, int maBan, String tenKhachHang, Timestamp ngayGioDat, int maSanPham, int soLuong, BigDecimal thanhTien, String trangThai) {
        this.maDonHang = maDonHang;
        this.maBan = maBan;
        this.tenKhachHang = tenKhachHang;
        this.ngayGioDat = ngayGioDat;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
    }

    public DonHang(int maDonHang, int maBan, String tenKhachHang, Timestamp ngayGioDat, BigDecimal tongTien, String trangThai) {
        this.maDonHang = maDonHang;
        this.maBan = maBan;
        this.tenKhachHang = tenKhachHang;
        this.ngayGioDat = ngayGioDat;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public Timestamp getNgayGioDat() {
        return ngayGioDat;
    }

    public void setNgayGioDat(Timestamp ngayGioDat) {
        this.ngayGioDat = ngayGioDat;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

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

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    public int getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(int maVoucher) {
        this.maVoucher = maVoucher;
    }
    @Override
    public String toString() {
        return "DonHang{"
                + "maDonHang=" + maDonHang
                + ", maBan=" + maBan
                + ", tenKhachHang='" + tenKhachHang + '\''
                + ", ngayGioDat=" + ngayGioDat
                + ", tongTien=" + tongTien
                + ", maSanPham=" + maSanPham
                + ", soLuong=" + soLuong
                + ", thanhTien=" + thanhTien
                + ", thanhTien=" + phuongThucThanhToan
                + ", trangThai='" + trangThai + '\''
                + '}';
    }
}
