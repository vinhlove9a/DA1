package model;

public class DonHangChiTiet {

    private String maSanPham;   // Mã sản phẩm
    private String tenSanPham; // Tên sản phẩm
    private int soLuong;        // Số lượng
    private double donGia;      // Đơn giá
    private double thanhTien;   // Thành tiền (Số lượng * Đơn giá)

    // Constructor không tham số
    public DonHangChiTiet() {
    }

    // Constructor đầy đủ tham số
    public DonHangChiTiet(String maSanPham, String tenSanPham, int soLuong, double donGia, double thanhTien) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }
    public DonHangChiTiet(String tenSanPham, int soLuong, double thanhTien) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;

        this.thanhTien = thanhTien;
    }
    // Getter và Setter
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    // Phương thức hiển thị thông tin (tuỳ chọn)
    @Override
    public String toString() {
        return "ChiTietDonHang{"
                + "maSanPham='" + maSanPham + '\''
                + ", tenSanPham='" + tenSanPham + '\''
                + ", soLuong=" + soLuong
                + ", donGia=" + donGia
                + ", thanhTien=" + thanhTien
                + '}';
    }
}
