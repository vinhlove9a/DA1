package model;

import java.sql.Timestamp;

public class DatBan {

    private int maDatBan;
    private String tenKhachHang;
    private String soDienThoai;
    private int maBan;
    private Timestamp ngayGioDat;
    private String thongTinLienHe;
    private String tinhTrang;

    public DatBan(int maDatBan, int maKhachHang, String tenKhachHang, String soDienThoai, int maBan, Timestamp ngayGioDat, String thongTinLienHe, String tinhTrang) {
        this.maDatBan = maDatBan;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.maBan = maBan;
        this.ngayGioDat = ngayGioDat;
        this.thongTinLienHe = thongTinLienHe;
        this.tinhTrang = tinhTrang;
    }

    public DatBan(int maDatBan, String tenKhachHang, String soDienThoai, int maBan, String tinhTrang) {
        this.maDatBan = maDatBan;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.maBan = maBan;
        this.tinhTrang = tinhTrang;
    }

    public DatBan() {
    }

    public DatBan(int maDB, int maBan, String tenKhachHang, String soDienThoai, Timestamp ngayGioDat, String tinhTrang) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters and Setters
    public int getMaDatBan() {
        return maDatBan;
    }

    public void setMaDatBan(int maDatBan) {
        this.maDatBan = maDatBan;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public Timestamp getNgayGioDat() {
        return ngayGioDat;
    }

    public void setNgayGioDat(Timestamp ngayGioDat) {
        this.ngayGioDat = ngayGioDat;
    }

    public String getThongTinLienHe() {
        return thongTinLienHe;
    }

    public void setThongTinLienHe(String thongTinLienHe) {
        this.thongTinLienHe = thongTinLienHe;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
