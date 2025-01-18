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

public class HienThiGiaSP {

    private JLabel label;

    public HienThiGiaSP(JLabel label) {
        this.label = label;
    }

    // Phương thức lấy giá của sản phẩm dựa vào tên sản phẩm
    public void hienThiGiaSanPham(String tenSanPham) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnect.getConnection();
            String sql = "SELECT DonGia FROM SanPham WHERE TenSanPham = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, tenSanPham);
            rs = ps.executeQuery();

            if (rs.next()) {
                double giaSanPham = rs.getDouble("DonGia");
                label.setText( giaSanPham + "");
            } else {
                label.setText("Sản phẩm đã ngừng bán.");
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
