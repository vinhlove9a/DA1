/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.io.*;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class LoadAnhToDB {

    public static void saveImageToDatabase(int maNhanVien, String imagePath) throws IOException {
        try {
            // Kết nối đến cơ sở dữ liệu
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=DB_NhaHangThree", "sa", "123");

            // Đọc ảnh từ file
            FileInputStream fis = new FileInputStream(new File(imagePath));

            // Câu lệnh SQL để cập nhật ảnh vào cơ sở dữ liệu
            String sql = "UPDATE NhanVien SET Anh = ? WHERE MaNhanVien = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            // Thêm ảnh vào PreparedStatement
            ps.setBinaryStream(1, fis, (int) new File(imagePath).length());
            ps.setInt(2, maNhanVien);

            // Thực thi câu lệnh
            ps.executeUpdate();

            // Đóng kết nối
            ps.close();
            fis.close();
            conn.close();
            System.out.println("Ảnh đã được lưu thành công.");
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveImageToDatabase2(int maSanPham, String imagePath) throws IOException {
        try ( Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=DB_NhaHangThree", "sa", "123")) {

            // Đọc ảnh từ file
            FileInputStream fis = new FileInputStream(new File(imagePath));

            // Câu lệnh SQL để cập nhật ảnh vào cơ sở dữ liệu
            String sql = "UPDATE SanPham SET Anh = ? WHERE MaSanPham = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            // Thêm ảnh vào PreparedStatement
            ps.setBinaryStream(1, fis, (int) new File(imagePath).length());
            ps.setInt(2, maSanPham);

            // Thực thi câu lệnh
            ps.executeUpdate();

            // Đóng kết nối
            ps.close();
            fis.close();
            conn.close();
            System.out.println("Ảnh đã được lưu thành công.");
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // Gọi phương thức để lưu ảnh vào cơ sở dữ liệu
        String imagePaths1 = "D:/DuAn1/newPRJ/5aesieunhan/src/icon/vinhria.jpg";
        String imagePaths2 = "D:/DuAn1/newPRJ/5aesieunhan/src/icon/minhrau.jpg";
        String imagePaths3 = "D:/DuAn1/newPRJ/5aesieunhan/src/icon.two/food50px.png";

        // Lưu ảnh cho từng nhân viên
        saveImageToDatabase(2, imagePaths1); // Lưu ảnh cho nhân viên 1
        saveImageToDatabase(1, imagePaths2); // Lưu ảnh cho nhân viên 2
        saveImageToDatabase(3, imagePaths3); // Lưu ảnh cho nhân viên 3
        saveImageToDatabase(3, imagePaths3); // Lưu ảnh cho nhân viên 4
        saveImageToDatabase(3, imagePaths3); // Lưu ảnh cho nhân viên 5
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
        String imageSP1 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/banhmi.jpeg";
        String imageSP2 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/cafe_4_11zon.png";
        String imageSP3 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/nuoccam_11_11zon.jpg";
        String imageSP4 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/pho_12_11zon.jpg";
        String imageSP5 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/banhbao_2_11zon.jpg";
        String imageSP6 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/soda_16_11zon.jpg";
        String imageSP7 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/trasua_19_11zon.jpeg";
        String imageSP8 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/goicuon_6_11zon.jpg";
        String imageSP9 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/comtam_5_11zon.jpg";
        String imageSP10 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/xoi_20_11zon.jpeg";
        String imageSP11 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/sinhto_15_11zon.jpg";
        String imageSP12 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/images (1)_8_11zon.jpeg";
        String imageSP13 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/banhcuon_3_11zon.jpg";
        String imageSP14 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/pizza_14_11zon.jpeg";
        String imageSP15 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/miy_10_11zon.jpeg";
        String imageSP16 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/sushi_17_11zon.jpg";
        String imageSP17 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/lau_9_11zon.jpg";
        String imageSP18 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/hotdog_7_11zon.jpg";
        String imageSP19 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/hotdog_7_11zon.jpg";
        String imageSP20 = "D:/DuAn1/newPRJ/5aesieunhan/src/imgBanHang/tradao_18_11zon.jpg";
        saveImageToDatabase2(1, imageSP1); // Lưu ảnh cho sản phẩm 1
        saveImageToDatabase2(2, imageSP2); // Lưu ảnh cho sp 2
        saveImageToDatabase2(3, imageSP3); // Lưu ảnh cho sp 3
        saveImageToDatabase2(4, imageSP4); // Lưu ảnh cho sp 3
        saveImageToDatabase2(5, imageSP5); // Lưu ảnh cho sp 3
        saveImageToDatabase2(6, imageSP6); // Lưu ảnh cho sản phẩm 1
        saveImageToDatabase2(7, imageSP7); // Lưu ảnh cho sp 2
        saveImageToDatabase2(8, imageSP8); // Lưu ảnh cho sp 3
        saveImageToDatabase2(9, imageSP9); // Lưu ảnh cho sp 3
        saveImageToDatabase2(10, imageSP10); // Lưu ảnh cho sp 3
        saveImageToDatabase2(11, imageSP11); // Lưu ảnh cho sp 3
        saveImageToDatabase2(12, imageSP12); // Lưu ảnh cho sp 3
        saveImageToDatabase2(13, imageSP13); // Lưu ảnh cho sp 3
        saveImageToDatabase2(14, imageSP14); // Lưu ảnh cho sp 3
        saveImageToDatabase2(15, imageSP15); // Lưu ảnh cho sp 3
        saveImageToDatabase2(16, imageSP16); // Lưu ảnh cho sp 3
        saveImageToDatabase2(17, imageSP17); // Lưu ảnh cho sp 3
        saveImageToDatabase2(18, imageSP18); // Lưu ảnh cho sp 3
        saveImageToDatabase2(19, imageSP19); // Lưu ảnh cho sp 3
        saveImageToDatabase2(20, imageSP20); // Lưu ảnh cho sp 3
    }

}
