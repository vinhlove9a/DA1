/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.HoaDon;
import model.HoaDonChiTiet;
import utility.DBConnect;

public class ThongKeRepo {

    /**
     *
     * @return
     */
    public List<HoaDon> getAll() {
        List<HoaDon> hoaDonList = new ArrayList<>();
        try {
            String sql = """
                    SELECT 
                        hd.MaHoaDon,
                        hd.MaNhanVien,
                        hd.NgayLap,
                        hd.TongTien,
                        hd.PhuongThucThanhToan,
                        hdc.MaSanPham,
                        hdc.SoLuong,
                        hdc.DoanhThuSanPham
                    FROM 
                        HoaDon hd
                    INNER JOIN 
                        HoaDonChiTiet hdc ON hd.MaHoaDon = hdc.MaHoaDon
                    """;

            Connection con = DBConnect.getConnection(); // Kết nối với database
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int maHoaDon = rs.getInt("MaHoaDon");

                // Kiểm tra xem hóa đơn đã tồn tại trong danh sách chưa
                HoaDon hoaDon = hoaDonList.stream()
                        .filter(hd -> hd.getMaHoaDon() == maHoaDon)
                        .findFirst()
                        .orElse(null);

                if (hoaDon == null) {
                    // Tạo mới hóa đơn nếu chưa tồn tại
                    int maNhanVien = rs.getInt("MaNhanVien");
                    Date ngayLap = rs.getDate("NgayLap");
                    BigDecimal tongTien = rs.getBigDecimal("TongTien");
                    String phuongThucThanhToan = rs.getString("PhuongThucThanhToan");

//                    hoaDon = new HoaDon(maHoaDon, maNhanVien, ngayLap, tongTien, phuongThucThanhToan);
                    hoaDonList.add(hoaDon);
                }

                // Thêm chi tiết hóa đơn vào danh sách chi tiết của hóa đơn
                HoaDonChiTiet chiTiet = new HoaDonChiTiet(
                        rs.getInt("MaSanPham"),
                        rs.getInt("SoLuong"),
                        rs.getBigDecimal("DoanhThuSanPham")
                );
//                hoaDon.addChiTiet(chiTiet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDonList;
    }
}
