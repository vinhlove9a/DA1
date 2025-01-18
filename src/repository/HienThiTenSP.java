/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author Admin
 */
import utility.DBConnect;
import javax.swing.JLabel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HienThiTenSP {

    private JLabel label;

    public HienThiTenSP(JLabel label) {
        this.label = label;
    }

    // Phương thức lấy tên của sản phẩm dựa vào mã sản phẩm
    public void hienThiTenSanPham(int maSanPham) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnect.getConnection();
            String sql = "SELECT TenSanPham FROM SanPham WHERE MaSanPham = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, maSanPham);
            rs = ps.executeQuery();

            if (rs.next()) {
                String tenSanPham = rs.getString("TenSanPham");
                label.setText(tenSanPham);
            } else {
                label.setText("Sản phẩm không tồn tại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            label.setText("Lỗi khi truy xuất dữ liệu.");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
