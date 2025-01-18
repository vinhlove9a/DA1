package model;

import javax.swing.ImageIcon;

public class SanPham {

    private int maSanPham;
    private String tenSanPham;
    private String danhMuc;
    private double giaBan;
    private int soLuongTon;
    private String moTa;
    private byte[] anh;  // Thêm thuộc tính ảnh (dạng mảng byte)

    // Constructor
    public SanPham(int maSanPham, String tenSanPham, String danhMuc, double giaBan, int soLuongTon, String moTa, byte[] anh) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.danhMuc = danhMuc;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
        this.moTa = moTa;
        this.anh = anh;
    }

    public SanPham(int maSanPham, String tenSanPham, String danhMuc, double giaBan, int soLuongTon, String moTa) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.danhMuc = danhMuc;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
        this.moTa = moTa;
        this.anh = anh;
    }

    // Getters and Setters
    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public Object[] toDataRow() {
        ImageIcon imageIcon = null;
        if (this.anh != null) {
            imageIcon = new ImageIcon(this.anh);
        }

        // Trả về mảng Object[] chứa dữ liệu của SanPham để hiển thị trong JTable
        return new Object[]{
            this.maSanPham,
            this.tenSanPham,
            this.danhMuc,
            this.giaBan,
            this.soLuongTon,
            this.moTa,
            imageIcon // Hiển thị ảnh nếu có
        };
    }
}
