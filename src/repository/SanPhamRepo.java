/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import model.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utility.DBConnect;


public class SanPhamRepo {
    public ArrayList<SanPham> getAll(){
        ArrayList<SanPham> list = new ArrayList<>();
        try {
            String sql = """
                         SELECT MaSanPham, TenSanPham, DanhMuc, GiaBan, SoLuongTon, MoTa FROM SanPham
                         """;
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int maSanPham = rs.getInt("MaSanPham");
                String tenSanPham = rs.getString("TenSanPham");
                String danhMuc = rs.getString("DanhMuc");
                double giaBan = rs.getDouble("GiaBan");
                int soLuongTon = rs.getInt("SoLuongTon");
                String moTa = rs.getString("MoTa");
                SanPham sanPham = new SanPham(maSanPham, tenSanPham, danhMuc, giaBan, soLuongTon, moTa);
                list.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
