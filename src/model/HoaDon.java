package model;

import java.math.BigDecimal;
import java.sql.Date;

public class HoaDon {
    private int maHoaDon;
    private int maDonHang;
    private int maNhanVien;
    private Date ngayLap;
    private BigDecimal tongTien;
    private String phuongThucThanhToan;

    // Constructor không tham số
    public HoaDon() {
    }

    // Constructor đầy đủ tham số
    public HoaDon(int maHoaDon, int maDonHang, int maNhanVien, Date ngayLap, BigDecimal tongTien, String phuongThucThanhToan) {
        this.maHoaDon = maHoaDon;
        this.maDonHang = maDonHang;
        this.maNhanVien = maNhanVien;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    // Getter và Setter
    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
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

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHoaDon=" + maHoaDon +
                ", maDonHang=" + maDonHang +
                ", maNhanVien=" + maNhanVien +
                ", ngayLap=" + ngayLap +
                ", tongTien=" + tongTien +
                ", phuongThucThanhToan='" + phuongThucThanhToan + '\'' +
                '}';
    }
}
