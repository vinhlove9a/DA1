package model;

public class SanPham {
    // Các thuộc tính (dựa trên cơ sở dữ liệu)
    private int maSanPham;
    private String tenSanPham;
    private String danhMuc;
    private double giaBan;
    private int soLuongTon;
    private String moTa;
    
    // Constructor không tham số
    public SanPham() {
    }
    
    // Constructor có tham số
    public SanPham(int maSanPham, String tenSanPham, String danhMuc, double giaBan, int soLuongTon, String moTa) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.danhMuc = danhMuc;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
        this.moTa = moTa;
    }

    // Getter và Setter
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
    public Object[] toDataRow(){
        return new Object[]{getMaSanPham(),getTenSanPham(),getDanhMuc(),getGiaBan(),getSoLuongTon(),getMoTa()};
    }
}
