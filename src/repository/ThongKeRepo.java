/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.math.BigDecimal;
import model.ThongKe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.HoaDon;
import model.HoaDonChiTiet;
import utility.DBConnect;

/**
 *
 * @author Admin
 */
public class ThongKeRepo {

    public List<HoaDon> getAll() {
        List<HoaDon> hoaDonList = new ArrayList<>();
        try {
            String sql = """
                        SELECT h.MaHoaDon, h.MaNhanVien, h.NgayLap, h.TongTien, h.PhuongThucThanhToan,
                               hd.MaSanPham, hd.SoLuong, hd.DoanhThuSanPham
                        FROM HoaDon h
                        JOIN HoaDonChiTiet hd ON h.MaHoaDon = hd.MaHoaDon;
                        """;

            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int maHoaDon = rs.getInt("MaHoaDon");
                int maNhanVien = rs.getInt("MaNhanVien");
                Date ngayLap = rs.getDate("NgayLap");
                BigDecimal tongTien = rs.getBigDecimal("TongTien");
                String phuongThucThanhToan = rs.getString("PhuongThucThanhToan");

                HoaDon hoaDon = new HoaDon(maHoaDon, maNhanVien, ngayLap, tongTien, phuongThucThanhToan);

                HoaDonChiTiet chiTiet = new HoaDonChiTiet(
                        rs.getInt("MaSanPham"),
                        rs.getInt("SoLuong"),
                        rs.getBigDecimal("DoanhThuSanPham")
                );

                hoaDon.addChiTiet(chiTiet);
                hoaDonList.add(hoaDon);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDonList;
    }
}
