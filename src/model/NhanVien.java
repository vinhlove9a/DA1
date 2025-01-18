package model;

import java.sql.Date;

public class NhanVien {

    private int maNhanVien;   // Mã nhân viên
    private int maNguoiDung;  // Mã người dùng liên kết với bảng Users
    private String tenNhanVien; // Tên nhân viên
    private boolean gioiTinh;  // Giới tính (true = Nam, false = Nữ)
    private Date ngaySinh;    // Ngày sinh
    private String soDienThoai; // Số điện thoại
    private String diaChi;    // Địa chỉ
    private byte[] anh;       // Ảnh nhân viên dưới dạng byte[]

    // Constructor
    public NhanVien(int maNhanVien, int maNguoiDung, String tenNhanVien, boolean gioiTinh, Date ngaySinh, String soDienThoai, String diaChi, byte[] anh) {
        this.maNhanVien = maNhanVien;
        this.maNguoiDung = maNguoiDung;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.anh = anh;
    }

    public NhanVien(int maNhanVien, String tenNhanVien, boolean gioiTinh, Date ngaySinh, String soDienThoai, String diaChi, Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getter and Setter methods
    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }
}
