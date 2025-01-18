/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author Admin
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import utility.DBConnect;

public class HienThiSoDonHang {

    private JLabel label;

    public HienThiSoDonHang(JLabel label) {
        this.label = label;
    }

    // Phương thức lấy tổng số đơn hàng và hiển thị lên JLabel
    public void hienThiSoDonHang() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT COUNT(*) AS TongSoDonHang FROM DonHang";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int tongSoDonHang = rs.getInt("TongSoDonHang");
                label.setText("Tổng số đơn hàng: " + tongSoDonHang);
            } else {
                label.setText("Không có đơn hàng nào.");
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
