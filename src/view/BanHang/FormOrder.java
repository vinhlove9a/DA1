/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.BanHang;

import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import model.DonHang;
import repository.DonHangRepo;
import repository.HienThiGiaSP;
import repository.HienThiTenSP;
import repository.LoadAnhFromDB;
import repository.ShowChiTietDonHang;
import repository.TimKiemBanHangTheoLoai;
import utility.DBConnect;
import view.TrangChu.FormTrangChu;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import model.DonHangChiTiet;
import javax.swing.event.ChangeListener;
import repository.QRCodeGenerator;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.*;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import repository.VoucherRepo;
import view.login.FormDangNhap;

/**
 *
 * @author Admin
 */
public class FormOrder extends javax.swing.JFrame {

    /**
     * Creates new form FormTrangChu
     */
    DefaultTableModel model = new DefaultTableModel();
    TimKiemBanHangTheoLoai rptl = new TimKiemBanHangTheoLoai();
    DefaultTableModel model2 = new DefaultTableModel();
    DonHangRepo rp = new DonHangRepo();
    private Webcam webcam;
    private WebcamPanel panel;
    private Connection conn;

    public FormOrder(Connection conn) {
        this.conn = conn;
        initComponents();
    }

    public FormOrder() {
        initComponents();
        setLocationRelativeTo(null);

        lblRole.setText("");
        lblRole.setText(FormDangNhap.userRole);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        cboLoaiSanPham.addActionListener(e -> {
            // Kiểm tra nếu cboLoaiSanPham không phải là null và có giá trị hợp lệ
            String selectedCategory = cboLoaiSanPham.getSelectedItem() != null ? cboLoaiSanPham.getSelectedItem().toString() : "";

            // Gọi searchProduct với tên sản phẩm và loại sản phẩm đã chọn
            searchProduct(selectedCategory);
        });
        txtTenSanPham.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Khi người dùng nhập văn bản vào ô text, gọi phương thức tìm kiếm
                String selectedCategory = cboLoaiSanPham.getSelectedItem() != null ? cboLoaiSanPham.getSelectedItem().toString() : "";
                searchProduct(selectedCategory);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Khi người dùng xóa văn bản, gọi phương thức tìm kiếm
                String selectedCategory = cboLoaiSanPham.getSelectedItem() != null ? cboLoaiSanPham.getSelectedItem().toString() : "";
                searchProduct(selectedCategory);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Thường không dùng đến trong JTextField, nhưng có thể để tránh lỗi
                String selectedCategory = cboLoaiSanPham.getSelectedItem() != null ? cboLoaiSanPham.getSelectedItem().toString() : "";
                searchProduct(selectedCategory);
            }
        });
        // Đặt chế độ đóng ứng dụng
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HienThiTenSP showTen1 = new HienThiTenSP(lblTen1);
        HienThiTenSP showTen2 = new HienThiTenSP(lblTen2);
        HienThiTenSP showTen3 = new HienThiTenSP(lblTen3);
        HienThiTenSP showTen4 = new HienThiTenSP(lblTen4);
        HienThiTenSP showTen5 = new HienThiTenSP(lblTen5);
        HienThiTenSP showTen6 = new HienThiTenSP(lblTen6);
        HienThiTenSP showTen7 = new HienThiTenSP(lblTen7);
        HienThiTenSP showTen8 = new HienThiTenSP(lblTen8);
        HienThiTenSP showTen9 = new HienThiTenSP(lblTen9);
        HienThiTenSP showTen10 = new HienThiTenSP(lblTen10);
        HienThiTenSP showTen11 = new HienThiTenSP(lblTen11);
        HienThiTenSP showTen12 = new HienThiTenSP(lblTen12);
//        HienThiTenSP showTen13 = new HienThiTenSP(lblTen13);
//        HienThiTenSP showTen14 = new HienThiTenSP(lblTen14);
//        HienThiTenSP showTen15 = new HienThiTenSP(lblTen15);
//        HienThiTenSP showTen16 = new HienThiTenSP(lblTen16);
//        HienThiTenSP showTen17 = new HienThiTenSP(lblTen17);
//        HienThiTenSP showTen18 = new HienThiTenSP(lblTen18);
//        HienThiTenSP showTen19 = new HienThiTenSP(lblTen19);
//        HienThiTenSP showTen20 = new HienThiTenSP(lblTen20);
        showTen1.hienThiTenSanPham(1);
        showTen2.hienThiTenSanPham(2);
        showTen3.hienThiTenSanPham(3);
        showTen4.hienThiTenSanPham(4);
        showTen5.hienThiTenSanPham(5);
        showTen6.hienThiTenSanPham(6);
        showTen7.hienThiTenSanPham(7);
        showTen8.hienThiTenSanPham(8);
        showTen9.hienThiTenSanPham(9);
        showTen10.hienThiTenSanPham(10);
        showTen11.hienThiTenSanPham(11);
        showTen12.hienThiTenSanPham(12);
//        showTen13.hienThiTenSanPham(13);
//        showTen14.hienThiTenSanPham(14);
//        showTen15.hienThiTenSanPham(15);
//        showTen16.hienThiTenSanPham(16);
//        showTen17.hienThiTenSanPham(17);
//        showTen18.hienThiTenSanPham(18);
//        showTen19.hienThiTenSanPham(19);
//        showTen20.hienThiTenSanPham(20);

        HienThiGiaSP showGia1 = new HienThiGiaSP(lblGia1);
        HienThiGiaSP showGia2 = new HienThiGiaSP(lblGia2);
        HienThiGiaSP showGia3 = new HienThiGiaSP(lblGia3);
        HienThiGiaSP showGia4 = new HienThiGiaSP(lblGia4);
        HienThiGiaSP showGia5 = new HienThiGiaSP(lblGia5);
        HienThiGiaSP showGia6 = new HienThiGiaSP(lblGia6);
        HienThiGiaSP showGia7 = new HienThiGiaSP(lblGia7);
        HienThiGiaSP showGia8 = new HienThiGiaSP(lblGia8);
        HienThiGiaSP showGia9 = new HienThiGiaSP(lblGia9);
        HienThiGiaSP showGia10 = new HienThiGiaSP(lblGia10);
        HienThiGiaSP showGia11 = new HienThiGiaSP(lblGia11);
        HienThiGiaSP showGia12 = new HienThiGiaSP(lblGia12);
//        HienThiGiaSP showGia13 = new HienThiGiaSP(lblGia13);
//        HienThiGiaSP showGia14 = new HienThiGiaSP(lblGia14);
//        HienThiGiaSP showGia15 = new HienThiGiaSP(lblGia15);
//        HienThiGiaSP showGia16 = new HienThiGiaSP(lblGia16);
//        HienThiGiaSP showGia17 = new HienThiGiaSP(lblGia17);
//        HienThiGiaSP showGia18 = new HienThiGiaSP(lblGia18);
//        HienThiGiaSP showGia19 = new HienThiGiaSP(lblGia19);
//        HienThiGiaSP showGia20 = new HienThiGiaSP(lblGia20);
        showGia1.hienThiGiaSanPham(lblTen1.getText());
        showGia2.hienThiGiaSanPham(lblTen2.getText());
        showGia3.hienThiGiaSanPham(lblTen3.getText());
        showGia4.hienThiGiaSanPham(lblTen4.getText());
        showGia5.hienThiGiaSanPham(lblTen5.getText());
        showGia6.hienThiGiaSanPham(lblTen6.getText());
        showGia7.hienThiGiaSanPham(lblTen7.getText());
        showGia8.hienThiGiaSanPham(lblTen8.getText());
        showGia9.hienThiGiaSanPham(lblTen9.getText());
        showGia10.hienThiGiaSanPham(lblTen10.getText());
        showGia11.hienThiGiaSanPham(lblTen11.getText());
        showGia12.hienThiGiaSanPham(lblTen12.getText());
//        showGia13.hienThiGiaSanPham(lblTen13.getText());
//        showGia14.hienThiGiaSanPham(lblTen14.getText());
//        showGia15.hienThiGiaSanPham(lblTen15.getText());
//        showGia16.hienThiGiaSanPham(lblTen16.getText());
//        showGia17.hienThiGiaSanPham(lblTen17.getText());
//        showGia18.hienThiGiaSanPham(lblTen18.getText());
//        showGia19.hienThiGiaSanPham(lblTen19.getText());
//        showGia20.hienThiGiaSanPham(lblTen20.getText());

        updateProductImages();

//        cboLoaiSPTimkiem.removeAllItems();
//        cboLoaiSPTimkiem.addItem("Đồ ăn");
//        cboLoaiSPTimkiem.addItem("Đồ uống");
//        cboLoaiSPTimkiem.addItem("All");
        cboPTTT.removeAllItems();
        cboPTTT.addItem("Tiền mặt");
        cboPTTT.addItem("Chuyển khoản");
        SpinnerNumberModel modelSpin1 = new SpinnerNumberModel(1, 1, 100, 1);
        SpinnerNumberModel modelSpin2 = new SpinnerNumberModel(1, 1, 100, 1);
        SpinnerNumberModel modelSpin3 = new SpinnerNumberModel(1, 1, 100, 1);
        SpinnerNumberModel modelSpin4 = new SpinnerNumberModel(1, 1, 100, 1);
        SpinnerNumberModel modelSpin5 = new SpinnerNumberModel(1, 1, 100, 1);
        SpinnerNumberModel modelSpin6 = new SpinnerNumberModel(1, 1, 100, 1);
        SpinnerNumberModel modelSpin7 = new SpinnerNumberModel(1, 1, 100, 1);
        SpinnerNumberModel modelSpin8 = new SpinnerNumberModel(1, 1, 100, 1);
        SpinnerNumberModel modelSpin9 = new SpinnerNumberModel(1, 1, 100, 1);
        SpinnerNumberModel modelSpin10 = new SpinnerNumberModel(1, 1, 100, 1);
        SpinnerNumberModel modelSpin11 = new SpinnerNumberModel(1, 1, 100, 1);
        SpinnerNumberModel modelSpin12 = new SpinnerNumberModel(1, 1, 100, 1);
        spn1.setModel(modelSpin1);
        spn2.setModel(modelSpin2);
        spn3.setModel(modelSpin3);
        spn4.setModel(modelSpin4);
        spn5.setModel(modelSpin5);
        spn6.setModel(modelSpin6);
        spn7.setModel(modelSpin7);
        spn8.setModel(modelSpin8);
        spn9.setModel(modelSpin9);
        spn10.setModel(modelSpin10);
        spn11.setModel(modelSpin11);
        spn12.setModel(modelSpin12);
        cboLoaiSanPham.removeAllItems();

        cboLoaiSanPham.addItem("đồ ăn");
        cboLoaiSanPham.addItem("đồ uống");
        cboBan.removeAllItems();  // Clear previous items

// Loop through tables and add them to the ComboBox if they are not reserved
        for (int i = 1; i <= 10; i++) {  // Assuming you have 10 tables with MaBan from 1 to 10
            if (!isTableReserved(i)) {  // Only add tables that are not reserved
                cboBan.addItem("Bàn " + i);  // Add non-reserved table to the ComboBox
            }
        }

        // Tạo JTable và DefaultTableModel
        model = (DefaultTableModel) tblOrder.getModel();
        model2 = (DefaultTableModel) tblDonHangCho.getModel();
        fillToTableDonHangCho();
        tblOrder.setRowHeight(30); // Thay đổi chiều cao dòng thành 40
        tblOrder.getColumnModel().getColumn(1).setCellEditor(new SpinnerCellEditor(1));
        tblOrder.getColumnModel().getColumn(1).setCellRenderer(new SpinnerCellRenderer());
        tblOrder.getColumnModel().getColumn(1).setCellRenderer(new FontSizeCellRenderer());
        tblOrder.getColumnModel().getColumn(1).setCellEditor(new SpinnerCellEditor(1));
        tblOrder.getColumnModel().getColumn(1).setCellEditor(new SpinnerEditor());

        txtMaKhachHang.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateDiscountSafely();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateDiscountSafely();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateDiscountSafely();
            }
        });
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateDiscountSafely() {
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isTableReserved(int maBan) {
        String query = "SELECT COUNT(*) FROM DatBan WHERE MaBan = ?";  // Check if MaBan exists in DatBan table

        try ( Connection conn = DBConnect.getConnection();  PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, maBan);  // Pass the MaBan to the query
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;  // Return true if the MaBan exists in DatBan (i.e., the table is reserved)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtKhachDua = new javax.swing.JTextField();
        cboPTTT = new javax.swing.JComboBox<>();
        jLabel148 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblTraLai = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lblThanhTien = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDonHangCho = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cboBan = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        productListPanel = new javax.swing.JPanel();
        lblGiaSP1 = new javax.swing.JLabel();
        lblGiaSP2 = new javax.swing.JLabel();
        lblGiaSP3 = new javax.swing.JLabel();
        lblGiaSP4 = new javax.swing.JLabel();
        lblGiaSP5 = new javax.swing.JLabel();
        lblGiaSP6 = new javax.swing.JLabel();
        lblGiaSP7 = new javax.swing.JLabel();
        lblGiaSP8 = new javax.swing.JLabel();
        lblGiaSP9 = new javax.swing.JLabel();
        lblGiaSP10 = new javax.swing.JLabel();
        lblGiaSP11 = new javax.swing.JLabel();
        lblGiaSP18 = new javax.swing.JLabel();
        lblGiaSP19 = new javax.swing.JLabel();
        lblGiaSP12 = new javax.swing.JLabel();
        lblGiaSP13 = new javax.swing.JLabel();
        lblGiaSP20 = new javax.swing.JLabel();
        lblGiaSP14 = new javax.swing.JLabel();
        lblGiaSP15 = new javax.swing.JLabel();
        lblGiaSP16 = new javax.swing.JLabel();
        lblGiaSP17 = new javax.swing.JLabel();
        panelSP1 = new javax.swing.JPanel();
        lblAnh1 = new javax.swing.JLabel();
        lblTen1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblGia1 = new javax.swing.JLabel();
        spn1 = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        panelSP2 = new javax.swing.JPanel();
        lblAnh2 = new javax.swing.JLabel();
        lblTen2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblGia2 = new javax.swing.JLabel();
        spn2 = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        panelSP3 = new javax.swing.JPanel();
        lblTen3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblGia3 = new javax.swing.JLabel();
        spn3 = new javax.swing.JSpinner();
        jLabel26 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        lblAnh3 = new javax.swing.JLabel();
        panelSP4 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        lblTen4 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblGia4 = new javax.swing.JLabel();
        spn4 = new javax.swing.JSpinner();
        jLabel31 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        lblAnh4 = new javax.swing.JLabel();
        panelSP5 = new javax.swing.JPanel();
        lblAnh5 = new javax.swing.JLabel();
        lblTen5 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lblGia5 = new javax.swing.JLabel();
        spn5 = new javax.swing.JSpinner();
        jLabel36 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        panelSP6 = new javax.swing.JPanel();
        lblAnh6 = new javax.swing.JLabel();
        lblTen6 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lblGia6 = new javax.swing.JLabel();
        spn6 = new javax.swing.JSpinner();
        jLabel41 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        panelSP7 = new javax.swing.JPanel();
        lblAnh7 = new javax.swing.JLabel();
        lblTen7 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        lblGia7 = new javax.swing.JLabel();
        spn7 = new javax.swing.JSpinner();
        jLabel46 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        panelSP9 = new javax.swing.JPanel();
        lblAnh9 = new javax.swing.JLabel();
        lblTen9 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        lblGia9 = new javax.swing.JLabel();
        spn9 = new javax.swing.JSpinner();
        jLabel56 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        panelSP10 = new javax.swing.JPanel();
        lblAnh10 = new javax.swing.JLabel();
        lblTen10 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        lblGia10 = new javax.swing.JLabel();
        spn10 = new javax.swing.JSpinner();
        jLabel61 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        panelSP11 = new javax.swing.JPanel();
        lblAnh11 = new javax.swing.JLabel();
        lblTen11 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        lblGia11 = new javax.swing.JLabel();
        spn11 = new javax.swing.JSpinner();
        jLabel66 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        panelSP8 = new javax.swing.JPanel();
        lblAnh8 = new javax.swing.JLabel();
        lblTen8 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        lblGia8 = new javax.swing.JLabel();
        spn8 = new javax.swing.JSpinner();
        jLabel71 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        panelSP12 = new javax.swing.JPanel();
        lblAnh12 = new javax.swing.JLabel();
        lblTen12 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        lblGia12 = new javax.swing.JLabel();
        spn12 = new javax.swing.JSpinner();
        jLabel91 = new javax.swing.JLabel();
        jButton24 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        cboLoaiSanPham = new javax.swing.JComboBox<>();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));

        jPanel9.setBackground(new java.awt.Color(255, 102, 0));

        jLabel18.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.two/buyer-pay-icon.png"))); // NOI18N
        jLabel18.setText("ORDER");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 153, 51));
        jPanel10.setForeground(new java.awt.Color(0, 0, 0));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shopping_cart_24px.png"))); // NOI18N
        jLabel21.setText("ĐƠN HÀNG");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel21)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 153, 51));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.two/hoadon.png"))); // NOI18N
        jLabel28.setText("HÓA ĐƠN");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel28)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblRole.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblRole.setForeground(new java.awt.Color(0, 0, 0));
        lblRole.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/male_user_50px.png"))); // NOI18N
        lblRole.setText("admin");

        jLabel38.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.two/logout-line-icon (2).png"))); // NOI18N
        jLabel38.setText("Thoát");
        jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel38MouseClicked(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(255, 153, 51));
        jPanel11.setForeground(new java.awt.Color(0, 0, 0));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home_24px.png"))); // NOI18N
        jLabel25.setText("HOME");
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel25)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 700, Short.MAX_VALUE)
                .addComponent(lblRole)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel38)
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1550, 70));

        tblOrder.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên món", "Số lượng", "Đơn giá"
            }
        ));
        tblOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrderMouseClicked(evt);
            }
        });
        tblOrder.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tblOrderInputMethodTextChanged(evt);
            }
        });
        jScrollPane7.setViewportView(tblOrder);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.two/buyer-pay-icon.png"))); // NOI18N
        jLabel8.setText("Khách đưa :");

        txtKhachDua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtKhachDuaMouseClicked(evt);
            }
        });

        cboPTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel148.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel148.setText("PTTT :");

        jLabel30.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.two/buyer-pay-icon.png"))); // NOI18N
        jLabel30.setText("Trả lại :");

        lblTraLai.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblTraLai.setText("0");

        jButton2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit_25px.png"))); // NOI18N
        jButton2.setText("XÓA MÓN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shopping_cart_24px.png"))); // NOI18N
        jButton3.setText("Thêm đơn hàng");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.two/buyer-pay-icon.png"))); // NOI18N
        jButton4.setText("Thanh toán");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lblThanhTien.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblThanhTien.setText("0");

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.two/buyer-pay-icon.png"))); // NOI18N
        jLabel16.setText("Thành tiền :");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.two/buyer-pay-icon.png"))); // NOI18N
        jLabel6.setText("Tổng tiền : ");

        lblTongTien.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblTongTien.setText("0");

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.two/buyer-pay-icon.png"))); // NOI18N
        jLabel10.setText("Giảm giá :");

        lblGiamGia.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblGiamGia.setText("0");

        jLabel2.setText("Mã KH:");

        txtMaKhachHang.setText("0");

        jLabel3.setText("Tên khách hàng:");

        txtTenKhachHang.setText("Tên khách hàng");

        tblDonHangCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đơn hàng", "Mã bàn", "Tên khách hàng", "Thời gian", "Thành tiền"
            }
        ));
        tblDonHangCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDonHangChoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDonHangCho);

        jLabel4.setText("Đơn hàng chờ :");

        jButton5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete_25px.png"))); // NOI18N
        jButton5.setText("Hủy đơn hàng");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setText("Chọn");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/google_forms_24px.png"))); // NOI18N
        jButton8.setText("Lưu đơn hàng");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/template_26px.png"))); // NOI18N
        jButton6.setText("Tạo QR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/template_26px.png"))); // NOI18N
        jButton10.setText("Quét QR");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel5.setText("Bàn:");

        cboBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboBan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel148))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(27, 27, 27)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel30)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblTraLai))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtKhachDua)
                                    .addComponent(cboPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblThanhTien))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblGiamGia))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblTongTien))))
                        .addGap(34, 34, 34))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(40, 40, 40))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton3, jButton4, jButton5, jButton8});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cboBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lblTongTien))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lblGiamGia))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(lblThanhTien))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel148))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTraLai)
                            .addComponent(jLabel30)))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton2, jButton3, jButton4, jButton5, jButton8});

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 70, 700, 800));

        productListPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblGiaSP1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP7.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP8.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP9.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP10.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP11.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP18.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP19.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP12.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP13.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP20.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP14.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP15.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP16.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblGiaSP17.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        lblTen1.setText("Tên");

        jLabel12.setText("Giá:");

        lblGia1.setText("jLabel13");

        jLabel14.setText("Số lượng:");

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSP1Layout = new javax.swing.GroupLayout(panelSP1);
        panelSP1.setLayout(panelSP1Layout);
        panelSP1Layout.setHorizontalGroup(
            panelSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSP1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTen1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGia1, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
                    .addGroup(panelSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton1)
                        .addGroup(panelSP1Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(spn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblAnh1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
        );
        panelSP1Layout.setVerticalGroup(
            panelSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP1Layout.createSequentialGroup()
                .addComponent(lblAnh1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTen1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblGia1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(spn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        lblTen2.setText("Tên");

        jLabel17.setText("Giá:");

        lblGia2.setText("jLabel13");

        jLabel19.setText("Số lượng:");

        jButton9.setText("Thêm");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSP2Layout = new javax.swing.GroupLayout(panelSP2);
        panelSP2.setLayout(panelSP2Layout);
        panelSP2Layout.setHorizontalGroup(
            panelSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSP2Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTen2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGia2, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
                    .addGroup(panelSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton9)
                        .addGroup(panelSP2Layout.createSequentialGroup()
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(spn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblAnh2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelSP2Layout.setVerticalGroup(
            panelSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP2Layout.createSequentialGroup()
                .addComponent(lblAnh2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTen2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(lblGia2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(spn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton9)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        lblTen3.setText("Tên");

        jLabel22.setText("Giá:");

        lblGia3.setText("jLabel13");

        jLabel26.setText("Số lượng:");

        jButton11.setText("Thêm");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSP3Layout = new javax.swing.GroupLayout(panelSP3);
        panelSP3.setLayout(panelSP3Layout);
        panelSP3Layout.setHorizontalGroup(
            panelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSP3Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTen3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGia3, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
                    .addGroup(panelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton11)
                        .addGroup(panelSP3Layout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(spn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelSP3Layout.createSequentialGroup()
                    .addComponent(lblAnh3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelSP3Layout.setVerticalGroup(
            panelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP3Layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(lblTen3)
                .addGap(18, 18, 18)
                .addGroup(panelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(lblGia3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(spn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11)
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(panelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelSP3Layout.createSequentialGroup()
                    .addComponent(lblAnh3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 155, Short.MAX_VALUE)))
        );

        lblTen4.setText("Tên");

        jLabel29.setText("Giá:");

        lblGia4.setText("jLabel13");

        jLabel31.setText("Số lượng:");

        jButton12.setText("Thêm");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSP4Layout = new javax.swing.GroupLayout(panelSP4);
        panelSP4.setLayout(panelSP4Layout);
        panelSP4Layout.setHorizontalGroup(
            panelSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP4Layout.createSequentialGroup()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAnh4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelSP4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSP4Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTen4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGia4, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelSP4Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(panelSP4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jButton12)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelSP4Layout.setVerticalGroup(
            panelSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP4Layout.createSequentialGroup()
                .addGroup(panelSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAnh4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTen4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(lblGia4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(spn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton12)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        lblTen5.setText("Tên");

        jLabel34.setText("Giá:");

        lblGia5.setText("jLabel13");

        jLabel36.setText("Số lượng:");

        jButton13.setText("Thêm");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSP5Layout = new javax.swing.GroupLayout(panelSP5);
        panelSP5.setLayout(panelSP5Layout);
        panelSP5Layout.setHorizontalGroup(
            panelSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSP5Layout.createSequentialGroup()
                        .addGroup(panelSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSP5Layout.createSequentialGroup()
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTen5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblGia5, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
                            .addGroup(panelSP5Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSP5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton13)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(lblAnh5, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
        );
        panelSP5Layout.setVerticalGroup(
            panelSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP5Layout.createSequentialGroup()
                .addComponent(lblAnh5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTen5)
                .addGap(18, 18, 18)
                .addGroup(panelSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(lblGia5))
                .addGap(18, 18, 18)
                .addGroup(panelSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(spn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton13)
                .addContainerGap())
        );

        lblTen6.setText("Tên");

        jLabel39.setText("Giá:");

        lblGia6.setText("jLabel13");

        jLabel41.setText("Số lượng:");

        jButton14.setText("Thêm");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSP6Layout = new javax.swing.GroupLayout(panelSP6);
        panelSP6.setLayout(panelSP6Layout);
        panelSP6Layout.setHorizontalGroup(
            panelSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSP6Layout.createSequentialGroup()
                        .addGroup(panelSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSP6Layout.createSequentialGroup()
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTen6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblGia6, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
                            .addGroup(panelSP6Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSP6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton14)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(lblAnh6, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
        );
        panelSP6Layout.setVerticalGroup(
            panelSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP6Layout.createSequentialGroup()
                .addComponent(lblAnh6, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTen6)
                .addGap(18, 18, 18)
                .addGroup(panelSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(lblGia6))
                .addGap(18, 18, 18)
                .addGroup(panelSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(spn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton14)
                .addContainerGap())
        );

        lblTen7.setText("Tên");

        jLabel44.setText("Giá:");

        lblGia7.setText("jLabel13");

        jLabel46.setText("Số lượng:");

        jButton15.setText("Thêm");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSP7Layout = new javax.swing.GroupLayout(panelSP7);
        panelSP7.setLayout(panelSP7Layout);
        panelSP7Layout.setHorizontalGroup(
            panelSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSP7Layout.createSequentialGroup()
                        .addGroup(panelSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSP7Layout.createSequentialGroup()
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTen7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblGia7, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
                            .addGroup(panelSP7Layout.createSequentialGroup()
                                .addComponent(jLabel46)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spn7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSP7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton15)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(lblAnh7, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
        );
        panelSP7Layout.setVerticalGroup(
            panelSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP7Layout.createSequentialGroup()
                .addComponent(lblAnh7, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTen7)
                .addGap(18, 18, 18)
                .addGroup(panelSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(lblGia7))
                .addGap(18, 18, 18)
                .addGroup(panelSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(spn7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton15)
                .addContainerGap())
        );

        lblTen9.setText("Tên");

        jLabel54.setText("Giá:");

        lblGia9.setText("jLabel13");

        jLabel56.setText("Số lượng:");

        jButton17.setText("Thêm");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSP9Layout = new javax.swing.GroupLayout(panelSP9);
        panelSP9.setLayout(panelSP9Layout);
        panelSP9Layout.setHorizontalGroup(
            panelSP9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSP9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSP9Layout.createSequentialGroup()
                        .addGroup(panelSP9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSP9Layout.createSequentialGroup()
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelSP9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGia9, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTen9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelSP9Layout.createSequentialGroup()
                                .addComponent(jLabel56)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSP9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton17)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(lblAnh9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelSP9Layout.setVerticalGroup(
            panelSP9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP9Layout.createSequentialGroup()
                .addComponent(lblAnh9, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTen9)
                .addGap(18, 18, 18)
                .addGroup(panelSP9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(lblGia9))
                .addGap(18, 18, 18)
                .addGroup(panelSP9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(spn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton17)
                .addContainerGap())
        );

        lblTen10.setText("Tên");

        jLabel59.setText("Giá:");

        lblGia10.setText("jLabel13");

        jLabel61.setText("Số lượng:");

        jButton18.setText("Thêm");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSP10Layout = new javax.swing.GroupLayout(panelSP10);
        panelSP10.setLayout(panelSP10Layout);
        panelSP10Layout.setHorizontalGroup(
            panelSP10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSP10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSP10Layout.createSequentialGroup()
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelSP10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTen10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGia10, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
                    .addGroup(panelSP10Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spn10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblAnh10, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSP10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton18)
                .addGap(51, 51, 51))
        );
        panelSP10Layout.setVerticalGroup(
            panelSP10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP10Layout.createSequentialGroup()
                .addComponent(lblAnh10, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTen10)
                .addGap(18, 18, 18)
                .addGroup(panelSP10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(lblGia10))
                .addGap(18, 18, 18)
                .addGroup(panelSP10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(spn10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton18)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        lblTen11.setText("Tên");

        jLabel64.setText("Giá:");

        lblGia11.setText("jLabel13");

        jLabel66.setText("Số lượng:");

        jButton19.setText("Thêm");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSP11Layout = new javax.swing.GroupLayout(panelSP11);
        panelSP11.setLayout(panelSP11Layout);
        panelSP11Layout.setHorizontalGroup(
            panelSP11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSP11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSP11Layout.createSequentialGroup()
                        .addGroup(panelSP11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSP11Layout.createSequentialGroup()
                                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelSP11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTen11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblGia11, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
                            .addGroup(panelSP11Layout.createSequentialGroup()
                                .addComponent(jLabel66)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spn11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSP11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton19)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(lblAnh11, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
        );
        panelSP11Layout.setVerticalGroup(
            panelSP11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP11Layout.createSequentialGroup()
                .addComponent(lblAnh11, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTen11)
                .addGap(18, 18, 18)
                .addGroup(panelSP11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(lblGia11))
                .addGap(18, 18, 18)
                .addGroup(panelSP11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(spn11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton19)
                .addContainerGap())
        );

        lblTen8.setText("Tên");

        jLabel69.setText("Giá:");

        lblGia8.setText("jLabel13");

        jLabel71.setText("Số lượng:");

        jButton20.setText("Thêm");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSP8Layout = new javax.swing.GroupLayout(panelSP8);
        panelSP8.setLayout(panelSP8Layout);
        panelSP8Layout.setHorizontalGroup(
            panelSP8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSP8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSP8Layout.createSequentialGroup()
                        .addGroup(panelSP8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSP8Layout.createSequentialGroup()
                                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelSP8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTen8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblGia8, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
                            .addGroup(panelSP8Layout.createSequentialGroup()
                                .addComponent(jLabel71)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSP8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton20)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(lblAnh8, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
        );
        panelSP8Layout.setVerticalGroup(
            panelSP8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP8Layout.createSequentialGroup()
                .addComponent(lblAnh8, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTen8)
                .addGap(18, 18, 18)
                .addGroup(panelSP8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(lblGia8))
                .addGap(18, 18, 18)
                .addGroup(panelSP8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(spn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton20)
                .addContainerGap())
        );

        lblTen12.setText("Tên");

        jLabel89.setText("Giá:");

        lblGia12.setText("jLabel13");

        jLabel91.setText("Số lượng:");

        jButton24.setText("Thêm");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSP12Layout = new javax.swing.GroupLayout(panelSP12);
        panelSP12.setLayout(panelSP12Layout);
        panelSP12Layout.setHorizontalGroup(
            panelSP12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSP12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSP12Layout.createSequentialGroup()
                        .addGroup(panelSP12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSP12Layout.createSequentialGroup()
                                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelSP12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTen12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblGia12, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
                            .addGroup(panelSP12Layout.createSequentialGroup()
                                .addComponent(jLabel91)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spn12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSP12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton24)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(lblAnh12, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
        );
        panelSP12Layout.setVerticalGroup(
            panelSP12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSP12Layout.createSequentialGroup()
                .addComponent(lblAnh12, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTen12)
                .addGap(18, 18, 18)
                .addGroup(panelSP12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(lblGia12))
                .addGap(18, 18, 18)
                .addGroup(panelSP12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(spn12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton24)
                .addContainerGap())
        );

        javax.swing.GroupLayout productListPanelLayout = new javax.swing.GroupLayout(productListPanel);
        productListPanel.setLayout(productListPanelLayout);
        productListPanelLayout.setHorizontalGroup(
            productListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productListPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(productListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productListPanelLayout.createSequentialGroup()
                        .addComponent(panelSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(panelSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(panelSP3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(panelSP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(productListPanelLayout.createSequentialGroup()
                        .addGroup(productListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelSP5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(productListPanelLayout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(lblGiaSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49)
                        .addGroup(productListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(productListPanelLayout.createSequentialGroup()
                                .addGap(222, 222, 222)
                                .addComponent(panelSP7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(productListPanelLayout.createSequentialGroup()
                                .addGap(169, 169, 169)
                                .addComponent(lblGiaSP3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(productListPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(panelSP6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblGiaSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelSP8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGiaSP4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(lblGiaSP5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(productListPanelLayout.createSequentialGroup()
                        .addComponent(panelSP9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(panelSP10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(panelSP11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(panelSP12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(productListPanelLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(productListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(productListPanelLayout.createSequentialGroup()
                                .addComponent(lblGiaSP6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(116, 116, 116)
                                .addComponent(lblGiaSP7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97)
                                .addGroup(productListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGiaSP8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(productListPanelLayout.createSequentialGroup()
                                        .addGap(322, 322, 322)
                                        .addComponent(lblGiaSP10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(productListPanelLayout.createSequentialGroup()
                                        .addGap(163, 163, 163)
                                        .addComponent(lblGiaSP9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(productListPanelLayout.createSequentialGroup()
                                .addComponent(lblGiaSP11, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(116, 116, 116)
                                .addComponent(lblGiaSP12, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97)
                                .addGroup(productListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(productListPanelLayout.createSequentialGroup()
                                        .addGap(156, 156, 156)
                                        .addComponent(lblGiaSP14, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(productListPanelLayout.createSequentialGroup()
                                        .addGap(275, 275, 275)
                                        .addComponent(lblGiaSP15, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblGiaSP13, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
        );
        productListPanelLayout.setVerticalGroup(
            productListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(productListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelSP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelSP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelSP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSP5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelSP7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelSP6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelSP8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGiaSP1)
                    .addComponent(lblGiaSP3)
                    .addComponent(lblGiaSP2)
                    .addComponent(lblGiaSP4)
                    .addComponent(lblGiaSP5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSP9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelSP10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelSP11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelSP12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGiaSP6)
                    .addComponent(lblGiaSP7)
                    .addComponent(lblGiaSP8)
                    .addComponent(lblGiaSP10)
                    .addComponent(lblGiaSP9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGiaSP11)
                    .addComponent(lblGiaSP12)
                    .addComponent(lblGiaSP14)
                    .addComponent(lblGiaSP15)
                    .addComponent(lblGiaSP13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(productListPanel);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 860, 720));

        jLabel1.setText("Tên sản phẩm:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, 30));
        getContentPane().add(txtTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 130, -1));

        cboLoaiSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "đồ ăn", "đồ uống" }));
        getContentPane().add(cboLoaiSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 220, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Custom CellRenderer để hiển thị cột "Số Lượng"
    // Custom TableCellRenderer để thay đổi font chữ cho cột "Số Lượng"

    class FontSizeCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // Lấy component mặc định
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Kiểm tra nếu đây là cột số lượng (cột số lượng thường là cột thứ 1)
            if (column == 1) { // Sửa chỉ mục cột nếu cần
                // Đặt font chữ lớn hơn cho cột số lượng
                c.setFont(new Font("Arial", Font.BOLD, 16)); // Font chữ, kiểu chữ, và kích thước
            }
            return c;
        }
    }

    public void setThongTinKhachHang(String tenKhachHang, String maKhachHang) {
        txtTenKhachHang.setText(tenKhachHang);
        txtMaKhachHang.setText(maKhachHang);
    }

    private void jLabel38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseClicked
        // TODO add your handling code here:
        FormTrangChu tc = new FormTrangChu();
        tc.show();
        dispose();
    }//GEN-LAST:event_jLabel38MouseClicked

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        // TODO add your handling code here:
        FormDonHang dh = new FormDonHang();
        dh.show();
        dispose();
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
        // TODO add your handling code here:
        FormHoaDon hd = new FormHoaDon();
        hd.show();
        dispose();
    }//GEN-LAST:event_jPanel12MouseClicked

    private void txtKhachDuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtKhachDuaMouseClicked
        // TODO add your handling code here:
        String input = JOptionPane.showInputDialog(this, "Nhập số tiền khách đưa: ");
        try {
            // Chuyển đổi chuỗi người dùng nhập thành double
            double khachDua = Double.parseDouble(input);

            // Hiển thị số tiền khách đưa lên JTextField
            txtKhachDua.setText(input);

            // Lấy giá trị tổng tiền từ JLabel, cần chắc chắn là số
            String tongTienText = lblThanhTien.getText().replaceAll("[^\\d.]", ""); // Loại bỏ tất cả ký tự không phải số
            double tongTien = Double.parseDouble(tongTienText);  // Chuyển đổi thành double

            // Kiểm tra nếu số tiền khách đưa ít hơn tổng tiền cần trả
            if (khachDua < tongTien) {
                JOptionPane.showMessageDialog(this, "Số tiền khách đưa không đủ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                // Tính tiền trả lại nếu đủ tiền
                double traLai = khachDua - tongTien;

                // Cập nhật kết quả trả lại lên JLabel
                lblTraLai.setText(String.valueOf(traLai));
            }

        } catch (NumberFormatException e) {
            // Nếu xảy ra lỗi khi chuyển đổi, hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(this, "Vui lòng nhập một số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtKhachDuaMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        double total = 0;
        int selectedRow = tblOrder.getSelectedRow();

        // Kiểm tra nếu có dòng nào được chọn
        if (selectedRow != -1) {
            // Xóa dòng đã chọn từ model
            model.removeRow(selectedRow);
        } else {
            // Nếu không có dòng nào được chọn, hiển thị thông báo
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Lấy giá trị của cột số lượng và giá ở mỗi hàng
            double price = Double.parseDouble(tblOrder.getValueAt(row, 2).toString()); // Cột giá
            double quantity = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());  // Cột số lượng

            // Tính tổng tiền cho mỗi hàng và cộng vào tổng
            total += quantity * price;
        }
        lblTongTien.setText("" + total);
        lblThanhTien.setText("");
        lblTraLai.setText("");
        txtKhachDua.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ThemDonHang();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        ThanhToan();
        fillToTableDonHangCho();
        fillToTableOrder();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        FormDonHang dh = new FormDonHang();
        dh.show();
        dispose();
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        // TODO add your handling code here:
        FormTrangChu tc = new FormTrangChu();
        tc.show();
        dispose();
    }//GEN-LAST:event_jLabel25MouseClicked

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        double total = 0;
        String ten = lblTen12.getText();
        String donGia = lblGia12.getText();
        int soLuong = (Integer) spn12.getValue();
        boolean found = false;

// Kiểm tra xem món ăn đã có trong bảng tblOrder chưa
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Kiểm tra tên món ăn trong cột đầu tiên
            String existingTen = tblOrder.getValueAt(row, 0).toString();
            if (existingTen.equals(ten)) {
                // Nếu món ăn đã có, cộng dồn số lượng
                int existingSoLuong = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());
                tblOrder.setValueAt(existingSoLuong + soLuong, row, 1);
                found = true;
                break;  // Nếu đã tìm thấy món ăn, không cần tiếp tục tìm
            }
        }

        if (!found) {
            // Nếu món ăn chưa có, thêm một hàng mới vào bảng
            model.addRow(new Object[]{ten, soLuong, donGia});
        }

// Tính tổng tiền
        total = 0;
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Lấy giá trị của cột số lượng và giá ở mỗi hàng
            double price = Double.parseDouble(tblOrder.getValueAt(row, 2).toString()); // Cột Price
            int quantity = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());  // Cột Quantity

            // Tính tổng tiền cho mỗi hàng và cộng vào tổng
            total += quantity * price;
        }

        lblTongTien.setText("" + total);
        lblThanhTien.setText("" + total);
        lblGiamGia.setText("0");

// Cập nhật giảm giá
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        double total = 0;
        String ten = lblTen8.getText();
        String donGia = lblGia8.getText();
        int soLuong = (Integer) spn8.getValue();
        boolean found = false;

// Kiểm tra xem món ăn đã có trong bảng tblOrder chưa
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Kiểm tra tên món ăn trong cột đầu tiên
            String existingTen = tblOrder.getValueAt(row, 0).toString();
            if (existingTen.equals(ten)) {
                // Nếu món ăn đã có, cộng dồn số lượng
                int existingSoLuong = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());
                tblOrder.setValueAt(existingSoLuong + soLuong, row, 1);
                found = true;
                break;  // Nếu đã tìm thấy món ăn, không cần tiếp tục tìm
            }
        }

        if (!found) {
            // Nếu món ăn chưa có, thêm một hàng mới vào bảng
            model.addRow(new Object[]{ten, soLuong, donGia});
        }

// Tính tổng tiền
        total = 0;
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Lấy giá trị của cột số lượng và giá ở mỗi hàng
            double price = Double.parseDouble(tblOrder.getValueAt(row, 2).toString()); // Cột Price
            int quantity = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());  // Cột Quantity

            // Tính tổng tiền cho mỗi hàng và cộng vào tổng
            total += quantity * price;
        }

        lblTongTien.setText("" + total);
        lblThanhTien.setText("" + total);
        lblGiamGia.setText("0");

// Cập nhật giảm giá
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        double total = 0;
        String ten = lblTen11.getText();
        String donGia = lblGia11.getText();
        int soLuong = (Integer) spn11.getValue();
        boolean found = false;

// Kiểm tra xem món ăn đã có trong bảng tblOrder chưa
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Kiểm tra tên món ăn trong cột đầu tiên
            String existingTen = tblOrder.getValueAt(row, 0).toString();
            if (existingTen.equals(ten)) {
                // Nếu món ăn đã có, cộng dồn số lượng
                int existingSoLuong = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());
                tblOrder.setValueAt(existingSoLuong + soLuong, row, 1);
                found = true;
                break;  // Nếu đã tìm thấy món ăn, không cần tiếp tục tìm
            }
        }

        if (!found) {
            // Nếu món ăn chưa có, thêm một hàng mới vào bảng
            model.addRow(new Object[]{ten, soLuong, donGia});
        }

// Tính tổng tiền
        total = 0;
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Lấy giá trị của cột số lượng và giá ở mỗi hàng
            double price = Double.parseDouble(tblOrder.getValueAt(row, 2).toString()); // Cột Price
            int quantity = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());  // Cột Quantity

            // Tính tổng tiền cho mỗi hàng và cộng vào tổng
            total += quantity * price;
        }

        lblTongTien.setText("" + total);
        lblThanhTien.setText("" + total);
        lblGiamGia.setText("0");

// Cập nhật giảm giá
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        double total = 0;
        String ten = lblTen10.getText();
        String donGia = lblGia10.getText();
        int soLuong = (Integer) spn10.getValue();
        boolean found = false;

// Kiểm tra xem món ăn đã có trong bảng tblOrder chưa
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Kiểm tra tên món ăn trong cột đầu tiên
            String existingTen = tblOrder.getValueAt(row, 0).toString();
            if (existingTen.equals(ten)) {
                // Nếu món ăn đã có, cộng dồn số lượng
                int existingSoLuong = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());
                tblOrder.setValueAt(existingSoLuong + soLuong, row, 1);
                found = true;
                break;  // Nếu đã tìm thấy món ăn, không cần tiếp tục tìm
            }
        }

        if (!found) {
            // Nếu món ăn chưa có, thêm một hàng mới vào bảng
            model.addRow(new Object[]{ten, soLuong, donGia});
        }

// Tính tổng tiền
        total = 0;
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Lấy giá trị của cột số lượng và giá ở mỗi hàng
            double price = Double.parseDouble(tblOrder.getValueAt(row, 2).toString()); // Cột Price
            int quantity = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());  // Cột Quantity

            // Tính tổng tiền cho mỗi hàng và cộng vào tổng
            total += quantity * price;
        }

        lblTongTien.setText("" + total);
        lblThanhTien.setText("" + total);
        lblGiamGia.setText("0");

// Cập nhật giảm giá
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        double total = 0;
        String ten = lblTen9.getText();
        String donGia = lblGia9.getText();
        int soLuong = (Integer) spn9.getValue();
        boolean found = false;

// Kiểm tra xem món ăn đã có trong bảng tblOrder chưa
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Kiểm tra tên món ăn trong cột đầu tiên
            String existingTen = tblOrder.getValueAt(row, 0).toString();
            if (existingTen.equals(ten)) {
                // Nếu món ăn đã có, cộng dồn số lượng
                int existingSoLuong = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());
                tblOrder.setValueAt(existingSoLuong + soLuong, row, 1);
                found = true;
                break;  // Nếu đã tìm thấy món ăn, không cần tiếp tục tìm
            }
        }

        if (!found) {
            // Nếu món ăn chưa có, thêm một hàng mới vào bảng
            model.addRow(new Object[]{ten, soLuong, donGia});
        }

// Tính tổng tiền
        total = 0;
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Lấy giá trị của cột số lượng và giá ở mỗi hàng
            double price = Double.parseDouble(tblOrder.getValueAt(row, 2).toString()); // Cột Price
            int quantity = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());  // Cột Quantity

            // Tính tổng tiền cho mỗi hàng và cộng vào tổng
            total += quantity * price;
        }

        lblTongTien.setText("" + total);
        lblThanhTien.setText("" + total);
        lblGiamGia.setText("0");

// Cập nhật giảm giá
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        double total = 0;
        String ten = lblTen7.getText();
        String donGia = lblGia7.getText();
        int soLuong = (Integer) spn7.getValue();
        boolean found = false;

// Kiểm tra xem món ăn đã có trong bảng tblOrder chưa
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Kiểm tra tên món ăn trong cột đầu tiên
            String existingTen = tblOrder.getValueAt(row, 0).toString();
            if (existingTen.equals(ten)) {
                // Nếu món ăn đã có, cộng dồn số lượng
                int existingSoLuong = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());
                tblOrder.setValueAt(existingSoLuong + soLuong, row, 1);
                found = true;
                break;  // Nếu đã tìm thấy món ăn, không cần tiếp tục tìm
            }
        }

        if (!found) {
            // Nếu món ăn chưa có, thêm một hàng mới vào bảng
            model.addRow(new Object[]{ten, soLuong, donGia});
        }

// Tính tổng tiền
        total = 0;
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Lấy giá trị của cột số lượng và giá ở mỗi hàng
            double price = Double.parseDouble(tblOrder.getValueAt(row, 2).toString()); // Cột Price
            int quantity = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());  // Cột Quantity

            // Tính tổng tiền cho mỗi hàng và cộng vào tổng
            total += quantity * price;
        }

        lblTongTien.setText("" + total);
        lblThanhTien.setText("" + total);
        lblGiamGia.setText("0");

// Cập nhật giảm giá
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        double total = 0;
        String ten = lblTen6.getText();
        String donGia = lblGia6.getText();
        int soLuong = (Integer) spn6.getValue();
        boolean found = false;

// Kiểm tra xem món ăn đã có trong bảng tblOrder chưa
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Kiểm tra tên món ăn trong cột đầu tiên
            String existingTen = tblOrder.getValueAt(row, 0).toString();
            if (existingTen.equals(ten)) {
                // Nếu món ăn đã có, cộng dồn số lượng
                int existingSoLuong = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());
                tblOrder.setValueAt(existingSoLuong + soLuong, row, 1);
                found = true;
                break;  // Nếu đã tìm thấy món ăn, không cần tiếp tục tìm
            }
        }

        if (!found) {
            // Nếu món ăn chưa có, thêm một hàng mới vào bảng
            model.addRow(new Object[]{ten, soLuong, donGia});
        }

// Tính tổng tiền
        total = 0;
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Lấy giá trị của cột số lượng và giá ở mỗi hàng
            double price = Double.parseDouble(tblOrder.getValueAt(row, 2).toString()); // Cột Price
            int quantity = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());  // Cột Quantity

            // Tính tổng tiền cho mỗi hàng và cộng vào tổng
            total += quantity * price;
        }

        lblTongTien.setText("" + total);
        lblThanhTien.setText("" + total);
        lblGiamGia.setText("0");

// Cập nhật giảm giá
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        double total = 0;
        String ten = lblTen5.getText();
        String donGia = lblGia5.getText();
        int soLuong = (Integer) spn5.getValue();
        boolean found = false;

// Kiểm tra xem món ăn đã có trong bảng tblOrder chưa
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Kiểm tra tên món ăn trong cột đầu tiên
            String existingTen = tblOrder.getValueAt(row, 0).toString();
            if (existingTen.equals(ten)) {
                // Nếu món ăn đã có, cộng dồn số lượng
                int existingSoLuong = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());
                tblOrder.setValueAt(existingSoLuong + soLuong, row, 1);
                found = true;
                break;  // Nếu đã tìm thấy món ăn, không cần tiếp tục tìm
            }
        }

        if (!found) {
            // Nếu món ăn chưa có, thêm một hàng mới vào bảng
            model.addRow(new Object[]{ten, soLuong, donGia});
        }

// Tính tổng tiền
        total = 0;
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Lấy giá trị của cột số lượng và giá ở mỗi hàng
            double price = Double.parseDouble(tblOrder.getValueAt(row, 2).toString()); // Cột Price
            int quantity = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());  // Cột Quantity

            // Tính tổng tiền cho mỗi hàng và cộng vào tổng
            total += quantity * price;
        }

        lblTongTien.setText("" + total);
        lblThanhTien.setText("" + total);
        lblGiamGia.setText("0");

// Cập nhật giảm giá
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        double total = 0;
        String ten = lblTen4.getText();
        String donGia = lblGia4.getText();
        int soLuong = (Integer) spn4.getValue();
        boolean found = false;

// Kiểm tra xem món ăn đã có trong bảng tblOrder chưa
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Kiểm tra tên món ăn trong cột đầu tiên
            String existingTen = tblOrder.getValueAt(row, 0).toString();
            if (existingTen.equals(ten)) {
                // Nếu món ăn đã có, cộng dồn số lượng
                int existingSoLuong = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());
                tblOrder.setValueAt(existingSoLuong + soLuong, row, 1);
                found = true;
                break;  // Nếu đã tìm thấy món ăn, không cần tiếp tục tìm
            }
        }

        if (!found) {
            // Nếu món ăn chưa có, thêm một hàng mới vào bảng
            model.addRow(new Object[]{ten, soLuong, donGia});
        }

// Tính tổng tiền
        total = 0;
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Lấy giá trị của cột số lượng và giá ở mỗi hàng
            double price = Double.parseDouble(tblOrder.getValueAt(row, 2).toString()); // Cột Price
            int quantity = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());  // Cột Quantity

            // Tính tổng tiền cho mỗi hàng và cộng vào tổng
            total += quantity * price;
        }

        lblTongTien.setText("" + total);
        lblThanhTien.setText("" + total);
        lblGiamGia.setText("0");

// Cập nhật giảm giá
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        double total = 0;
        String ten = lblTen3.getText();
        String donGia = lblGia3.getText();
        int soLuong = (Integer) spn3.getValue();
        boolean found = false;

// Kiểm tra xem món ăn đã có trong bảng tblOrder chưa
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Kiểm tra tên món ăn trong cột đầu tiên
            String existingTen = tblOrder.getValueAt(row, 0).toString();
            if (existingTen.equals(ten)) {
                // Nếu món ăn đã có, cộng dồn số lượng
                int existingSoLuong = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());
                tblOrder.setValueAt(existingSoLuong + soLuong, row, 1);
                found = true;
                break;  // Nếu đã tìm thấy món ăn, không cần tiếp tục tìm
            }
        }

        if (!found) {
            // Nếu món ăn chưa có, thêm một hàng mới vào bảng
            model.addRow(new Object[]{ten, soLuong, donGia});
        }

// Tính tổng tiền
        total = 0;
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Lấy giá trị của cột số lượng và giá ở mỗi hàng
            double price = Double.parseDouble(tblOrder.getValueAt(row, 2).toString()); // Cột Price
            int quantity = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());  // Cột Quantity

            // Tính tổng tiền cho mỗi hàng và cộng vào tổng
            total += quantity * price;
        }

        lblTongTien.setText("" + total);
        lblThanhTien.setText("" + total);
        lblGiamGia.setText("0");

// Cập nhật giảm giá
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        double total = 0;
        String ten = lblTen2.getText();
        String donGia = lblGia2.getText();
        int soLuong = (Integer) spn2.getValue();
        boolean found = false;

// Kiểm tra xem món ăn đã có trong bảng tblOrder chưa
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Kiểm tra tên món ăn trong cột đầu tiên
            String existingTen = tblOrder.getValueAt(row, 0).toString();
            if (existingTen.equals(ten)) {
                // Nếu món ăn đã có, cộng dồn số lượng
                int existingSoLuong = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());
                tblOrder.setValueAt(existingSoLuong + soLuong, row, 1);
                found = true;
                break;  // Nếu đã tìm thấy món ăn, không cần tiếp tục tìm
            }
        }

        if (!found) {
            // Nếu món ăn chưa có, thêm một hàng mới vào bảng
            model.addRow(new Object[]{ten, soLuong, donGia});
        }

// Tính tổng tiền
        total = 0;
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Lấy giá trị của cột số lượng và giá ở mỗi hàng
            double price = Double.parseDouble(tblOrder.getValueAt(row, 2).toString()); // Cột Price
            int quantity = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());  // Cột Quantity

            // Tính tổng tiền cho mỗi hàng và cộng vào tổng
            total += quantity * price;
        }

        lblTongTien.setText("" + total);
        lblThanhTien.setText("" + total);
        lblGiamGia.setText("0");

// Cập nhật giảm giá
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        double total = 0;
        String ten = lblTen1.getText();
        String donGia = lblGia1.getText();
        int soLuong = (Integer) spn1.getValue();
        boolean found = false;

// Kiểm tra xem món ăn đã có trong bảng tblOrder chưa
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Kiểm tra tên món ăn trong cột đầu tiên
            String existingTen = tblOrder.getValueAt(row, 0).toString();
            if (existingTen.equals(ten)) {
                // Nếu món ăn đã có, cộng dồn số lượng
                int existingSoLuong = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());
                tblOrder.setValueAt(existingSoLuong + soLuong, row, 1);
                found = true;
                break;  // Nếu đã tìm thấy món ăn, không cần tiếp tục tìm
            }
        }

        if (!found) {
            // Nếu món ăn chưa có, thêm một hàng mới vào bảng
            model.addRow(new Object[]{ten, soLuong, donGia});
        }

// Tính tổng tiền
        total = 0;
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            // Lấy giá trị của cột số lượng và giá ở mỗi hàng
            double price = Double.parseDouble(tblOrder.getValueAt(row, 2).toString()); // Cột Price
            int quantity = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());  // Cột Quantity

            // Tính tổng tiền cho mỗi hàng và cộng vào tổng
            total += quantity * price;
        }

        lblTongTien.setText("" + total);
        lblThanhTien.setText("" + total);
        lblGiamGia.setText("0");

// Cập nhật giảm giá
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Custom Spinner Editor cho cột số lượng
    class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {

        private JSpinner spinner;

        public SpinnerEditor() {
            spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
            spinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    // Cập nhật tổng tiền mỗi khi giá trị thay đổi
                    stopCellEditing();
                    updateTotal();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value instanceof Integer) {
                spinner.setValue(value);
            } else {
                spinner.setValue(1); // Giá trị mặc định
            }
            return spinner;
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }
    }

    private void updateTotal() {
        double total = 0;
        for (int row = 0; row < tblOrder.getRowCount(); row++) {
            double price = Double.parseDouble(tblOrder.getValueAt(row, 2).toString()); // Cột giá
            int quantity = Integer.parseInt(tblOrder.getValueAt(row, 1).toString());  // Cột số lượng
            total += price * quantity; // Tính tổng tiền
        }
        // Cập nhật tổng tiền vào label
        lblTongTien.setText(String.valueOf(total));
        lblThanhTien.setText(String.valueOf(total));
    }
// Custom CellEditor để chỉnh sửa số lượng bằng JSpinner

    static class SpinnerCellEditor extends AbstractCellEditor implements TableCellEditor {

        private JSpinner spinner;
        private int editableColumn;

        public SpinnerCellEditor(int editableColumn) {
            this.editableColumn = editableColumn;
            spinner = new JSpinner(new SpinnerNumberModel(1, 0, Integer.MAX_VALUE, 1));
            JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinner.getEditor();
            editor.getTextField().setEditable(false);  // Đảm bảo không thể nhập văn bản trực tiếp
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            // Chỉ xử lý nếu là cột spinner
            if (column == editableColumn && value instanceof Number) {
                spinner.setValue(((Number) value).intValue()); // Chuyển giá trị hợp lệ
            } else {
                // Không thay đổi giá trị spinner nếu click nhầm cột khác
                spinner.setValue(spinner.getValue());
            }
            return spinner;
        }
    }

// Custom CellRenderer để hiển thị JSpinner trong cột "Số Lượng"
    static class SpinnerCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // Sử dụng SpinnerNumberModel với kiểu int
            JSpinner spinner = new JSpinner(new SpinnerNumberModel((int) value, 0, Integer.MAX_VALUE, 1)); // Sử dụng int
            return spinner;
        }
    }
    private void tblOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderMouseClicked
        // TODO add your handling code here:
        int row = tblOrder.rowAtPoint(evt.getPoint());
        int column = tblOrder.columnAtPoint(evt.getPoint());

// Ensure a valid row is clicked
        if (row >= 0) {
            // Ensure the click is on the "Số lượng" column (index 1)
            if (column == 1) {
                // Retrieve the value at the clicked cell
                Object soLuongValue = tblOrder.getValueAt(row, column);

                // Check if the value is an instance of Integer (for spinner data)
                if (soLuongValue instanceof Integer) {
                    int soLuong = (Integer) soLuongValue;

                    // Perform additional validation for the spinner value
                    if (soLuong > 0) { // Ensure quantity is greater than 0
                        // Valid quantity
                        // You can add additional logic here, if needed.
                    } else {
                        // Invalid quantity: Less than or equal to 0
                        JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0.");
                        tblOrder.setValueAt(1, row, column); // Reset invalid value to 1 (default value)
                    }
                } else {
                    // Invalid data: Value is not an integer
                    JOptionPane.showMessageDialog(this, "Dữ liệu trong cột số lượng không hợp lệ.");
                    tblOrder.setValueAt(1, row, column); // Reset invalid value to 1 (default value)
                }
            } else {
                // Handle clicks on other columns
                JOptionPane.showMessageDialog(this, "Vui lòng chỉnh sửa số lượng tại cột số lượng.");
            }
        } else {
            // Handle clicks outside of table rows
            JOptionPane.showMessageDialog(this, "Hãy chọn một hàng hợp lệ trong bảng.");
        }
    }//GEN-LAST:event_tblOrderMouseClicked

    private void tblDonHangChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonHangChoMouseClicked
        //TODO add your handling code here:
        int index = tblDonHangCho.getSelectedRow();
        if (index >= 0) { // Kiểm tra nếu có dòng nào được chọn
            // Lấy mã đơn hàng từ cột đầu tiên
            Object value = tblDonHangCho.getValueAt(index, 0);
            if (value != null) {
                int maDonHang = Integer.parseInt(value.toString()); // Ép kiểu thành int
                loadOrderDetails(maDonHang); // Gọi phương thức để tải chi tiết đơn hàng
                try {
                    updateDiscount();
                } catch (SQLException ex) {
                    Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không có mã đơn hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xem chi tiết đơn hàng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        // Kiểm tra nếu dòng đã được chọn hợp lệ


    }//GEN-LAST:event_tblDonHangChoMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        huyDonHang();
        resetOrder();
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        FormThongTinKhachHang ttkh = new FormThongTinKhachHang(this);
        ttkh.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);  // Đảm bảo form này đóng khi không còn cần thiết
        ttkh.show();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tblOrderInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tblOrderInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tblOrderInputMethodTextChanged

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        int index = tblDonHangCho.getSelectedRow();

        // Lấy mã đơn hàng từ cột đầu tiên
        Object value = tblDonHangCho.getValueAt(index, 0);
        CapNhatDonHang((int) value);
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        createQRCodeForOrder();
        try {
            updateDiscount();
        } catch (SQLException ex) {
            Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        QuetQR formQuetQR = new QuetQR(); // Truyền FormOrder này
        formQuetQR.setVisible(true);
        
        
    }//GEN-LAST:event_jButton10ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboBan;
    private javax.swing.JComboBox<String> cboLoaiSanPham;
    private javax.swing.JComboBox<String> cboPTTT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblAnh1;
    private javax.swing.JLabel lblAnh10;
    private javax.swing.JLabel lblAnh11;
    private javax.swing.JLabel lblAnh12;
    private javax.swing.JLabel lblAnh2;
    private javax.swing.JLabel lblAnh3;
    private javax.swing.JLabel lblAnh4;
    private javax.swing.JLabel lblAnh5;
    private javax.swing.JLabel lblAnh6;
    private javax.swing.JLabel lblAnh7;
    private javax.swing.JLabel lblAnh8;
    private javax.swing.JLabel lblAnh9;
    private javax.swing.JLabel lblGia1;
    private javax.swing.JLabel lblGia10;
    private javax.swing.JLabel lblGia11;
    private javax.swing.JLabel lblGia12;
    private javax.swing.JLabel lblGia2;
    private javax.swing.JLabel lblGia3;
    private javax.swing.JLabel lblGia4;
    private javax.swing.JLabel lblGia5;
    private javax.swing.JLabel lblGia6;
    private javax.swing.JLabel lblGia7;
    private javax.swing.JLabel lblGia8;
    private javax.swing.JLabel lblGia9;
    private javax.swing.JLabel lblGiaSP1;
    private javax.swing.JLabel lblGiaSP10;
    private javax.swing.JLabel lblGiaSP11;
    private javax.swing.JLabel lblGiaSP12;
    private javax.swing.JLabel lblGiaSP13;
    private javax.swing.JLabel lblGiaSP14;
    private javax.swing.JLabel lblGiaSP15;
    private javax.swing.JLabel lblGiaSP16;
    private javax.swing.JLabel lblGiaSP17;
    private javax.swing.JLabel lblGiaSP18;
    private javax.swing.JLabel lblGiaSP19;
    private javax.swing.JLabel lblGiaSP2;
    private javax.swing.JLabel lblGiaSP20;
    private javax.swing.JLabel lblGiaSP3;
    private javax.swing.JLabel lblGiaSP4;
    private javax.swing.JLabel lblGiaSP5;
    private javax.swing.JLabel lblGiaSP6;
    private javax.swing.JLabel lblGiaSP7;
    private javax.swing.JLabel lblGiaSP8;
    private javax.swing.JLabel lblGiaSP9;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblTen1;
    private javax.swing.JLabel lblTen10;
    private javax.swing.JLabel lblTen11;
    private javax.swing.JLabel lblTen12;
    private javax.swing.JLabel lblTen2;
    private javax.swing.JLabel lblTen3;
    private javax.swing.JLabel lblTen4;
    private javax.swing.JLabel lblTen5;
    private javax.swing.JLabel lblTen6;
    private javax.swing.JLabel lblTen7;
    private javax.swing.JLabel lblTen8;
    private javax.swing.JLabel lblTen9;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTraLai;
    private javax.swing.JPanel panelSP1;
    private javax.swing.JPanel panelSP10;
    private javax.swing.JPanel panelSP11;
    private javax.swing.JPanel panelSP12;
    private javax.swing.JPanel panelSP2;
    private javax.swing.JPanel panelSP3;
    private javax.swing.JPanel panelSP4;
    private javax.swing.JPanel panelSP5;
    private javax.swing.JPanel panelSP6;
    private javax.swing.JPanel panelSP7;
    private javax.swing.JPanel panelSP8;
    private javax.swing.JPanel panelSP9;
    private javax.swing.JPanel productListPanel;
    private javax.swing.JSpinner spn1;
    private javax.swing.JSpinner spn10;
    private javax.swing.JSpinner spn11;
    private javax.swing.JSpinner spn12;
    private javax.swing.JSpinner spn2;
    private javax.swing.JSpinner spn3;
    private javax.swing.JSpinner spn4;
    private javax.swing.JSpinner spn5;
    private javax.swing.JSpinner spn6;
    private javax.swing.JSpinner spn7;
    private javax.swing.JSpinner spn8;
    private javax.swing.JSpinner spn9;
    private javax.swing.JTable tblDonHangCho;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTextField txtKhachDua;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables

//    public void getProductCodes(JTable tblOrder) {
//        Connection conn = null;
//        try {
//            conn = DBConnect.getConnection();  // Thiết lập kết nối cơ sở dữ liệu
//            List<Integer> productCodes = new ArrayList<>();  // Danh sách mã sản phẩm
//
//            for (int i = 0; i < tblOrder.getRowCount(); i++) {
//                String tenSanPham = String.valueOf(tblOrder.getValueAt(i, 0));  // Tên sản phẩm
//
//                // Truy vấn lấy MaSanPham từ TenSanPham
//                String queryMaSanPham = "SELECT MaSanPham FROM SanPham WHERE TenSanPham = ?";
//                PreparedStatement stmtMaSanPham = conn.prepareStatement(queryMaSanPham);
//                stmtMaSanPham.setString(1, tenSanPham);
//                ResultSet rsSanPham = stmtMaSanPham.executeQuery();
//
//                if (rsSanPham.next()) {
//                    int maSanPham = rsSanPham.getInt("MaSanPham");
//                    productCodes.add(maSanPham);  // Thêm mã sản phẩm vào danh sách
//                    // Giả sử bạn đã có một JLabel để đặt ảnh
//                    JLabel lbl = new JLabel();
//
//                    // Gọi phương thức để lấy ảnh từ cơ sở dữ liệu                   
//                    ImageIcon img = getImageFromDatabase2(maSanPham);
//                    // Kiểm tra và đặt ảnh cho JLabel
//                    if (anh1 != null) {
//                        lbl.setIcon(anh1);
//                    } else {
//                        lbl.setText("Không tìm thấy ảnh cho sản phẩm này");
//                    }
//                    LoadAnhFromDB anh1 = new LoadAnhFromDB();
//                    anh1.getImageFromDatabase2(maSanPham);
//                } else {
//                    JOptionPane.showMessageDialog(null, "Sản phẩm " + tenSanPham + " không tồn tại!");
//                    rsSanPham.close();
//                    stmtMaSanPham.close();
//                    return;
//                }
//                rsSanPham.close();
//                stmtMaSanPham.close();
//            }
//
//            // Hiển thị danh sách mã sản phẩm
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();  // Đóng kết nối cơ sở dữ liệu
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public void ThanhToan() {
        VoucherRepo vcrp = new VoucherRepo();
        if (txtKhachDua.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền khách đưa!");
            return;  // Dừng quá trình thanh toán nếu rỗng
        }

        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            conn.setAutoCommit(false);  // Bắt đầu giao dịch

            // Lấy mã đơn hàng từ bảng tblDonHangCho
            int index = tblDonHangCho.getSelectedRow();
            int maDonHang = 0;
            if (index >= 0) { // Nếu có dòng được chọn
                // Lấy MaDonHang từ cột đầu tiên
                Object value = tblDonHangCho.getValueAt(index, 0);
                maDonHang = (Integer) value;
            }

            // Kiểm tra nếu mã đơn hàng đã tồn tại
            String checkDonHangExist = "SELECT MaDonHang FROM DonHang WHERE MaDonHang = ?";
            PreparedStatement stmtCheck = conn.prepareStatement(checkDonHangExist);
            stmtCheck.setInt(1, maDonHang);
            ResultSet rsCheck = stmtCheck.executeQuery();

            if (rsCheck.next()) {  // Nếu đơn hàng đã tồn tại
                // Thực hiện thanh toán cho đơn hàng đã có
                processPaymentForExistingOrder(conn, maDonHang);
                updateDiscount();
            } else {
                // Nếu đơn hàng chưa tồn tại, tạo mới đơn hàng và thanh toán như bình thường
                processNewOrderPayment(conn);
                updateDiscount();
            }

            // Commit giao dịch nếu tất cả thành công
            conn.commit();

            // Chạy cấp voucher trong một luồng riêng biệt
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(() -> {
                try {
                    vcrp.refreshVouchersForAllCustomers(); // Gọi cấp voucher sau khi thanh toán thành công
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            //JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Thanh toán không thành công!");
            if (conn != null) {
                try {
                    conn.rollback();  // Rollback nếu có lỗi
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();  // Đóng kết nối
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

// Xử lý thanh toán cho đơn hàng đã có
    public void processPaymentForExistingOrder(Connection conn, int maDonHang) throws Exception {
        // Lấy thông tin chi tiết đơn hàng
        String maNhanVien = FormDangNhap.maNhanVien;
        List<Object[]> orderDetails = new ArrayList<>();
        for (int i = 0; i < tblOrder.getRowCount(); i++) {
            String tenSanPham = String.valueOf(tblOrder.getValueAt(i, 0));  // Tên sản phẩm
            String soLuong = String.valueOf(tblOrder.getValueAt(i, 1));    // Số lượng
            double donGia = Double.parseDouble(String.valueOf(tblOrder.getValueAt(i, 2)));  // Đơn giá
            double thanhTien = donGia * Integer.parseInt(soLuong);  // Thành tiền
            // Truy vấn lấy MaSanPham
            String queryMaSanPham = "SELECT MaSanPham FROM SanPham WHERE TenSanPham = ?";
            PreparedStatement stmtMaSanPham = conn.prepareStatement(queryMaSanPham);
            stmtMaSanPham.setString(1, tenSanPham);
            ResultSet rsSanPham = stmtMaSanPham.executeQuery();
            if (rsSanPham.next()) {
                int maSanPham = rsSanPham.getInt("MaSanPham");
                orderDetails.add(new Object[]{maSanPham, soLuong, thanhTien});
            } else {
                JOptionPane.showMessageDialog(this, "Sản phẩm " + tenSanPham + " không tồn tại!");
                return;
            }
        }

        // Insert vào bảng HoaDon
        String insertHoaDon = "INSERT INTO HoaDon (MaDonHang, MaNhanVien, NgayLap, TongTien, PhuongThucThanhToan) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmtInsertHoaDon = conn.prepareStatement(insertHoaDon, PreparedStatement.RETURN_GENERATED_KEYS);
        stmtInsertHoaDon.setInt(1, maDonHang);  // MaDonHang của đơn hàng hiện tại
        stmtInsertHoaDon.setInt(2, Integer.valueOf(maNhanVien));  // Ví dụ MaNhanVien (cần thay đổi nếu có logic lấy nhân viên hiện tại)
        stmtInsertHoaDon.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));  // Ngày giờ hiện tại
        stmtInsertHoaDon.setDouble(4, Double.parseDouble(lblThanhTien.getText()));  // Tổng tiền
        stmtInsertHoaDon.setString(5, cboPTTT.getSelectedItem().toString());  // Phương thức thanh toán
        stmtInsertHoaDon.executeUpdate();

        // Lấy MaHoaDon từ bảng HoaDon sau khi insert
        ResultSet rsHoaDon = stmtInsertHoaDon.getGeneratedKeys();
        int maHoaDon = 0;
        if (rsHoaDon.next()) {
            maHoaDon = rsHoaDon.getInt(1);  // Lấy Mã Hóa Đơn vừa tạo
        }

        // Insert vào bảng HoaDonChiTiet
        String insertHoaDonChiTiet = "INSERT INTO HoaDonChiTiet (MaHoaDon, MaSanPham, SoLuong, DoanhThuSanPham) VALUES (?, ?, ?, ?)";
        PreparedStatement stmtInsertHoaDonChiTiet = conn.prepareStatement(insertHoaDonChiTiet);
        for (Object[] orderDetail : orderDetails) {
            int maSanPham = (int) orderDetail[0];
            int soLuong = Integer.parseInt((String) orderDetail[1]);
            double thanhTien = (double) orderDetail[2];

            stmtInsertHoaDonChiTiet.setInt(1, maHoaDon);  // Mã Hóa Đơn vừa tạo
            stmtInsertHoaDonChiTiet.setInt(2, maSanPham);  // Mã Sản Phẩm
            stmtInsertHoaDonChiTiet.setInt(3, soLuong);  // Số Lượng
            stmtInsertHoaDonChiTiet.setDouble(4, thanhTien);  // Doanh Thu Sản Phẩm
            stmtInsertHoaDonChiTiet.executeUpdate();
        }

        // Cập nhật trạng thái đơn hàng
        String updateDonHang = "UPDATE DonHang SET TongTien = ?, PhuongThucThanhToan = ?, TrangThai = 'Đã Thanh Toán' WHERE MaDonHang = ?";
        PreparedStatement stmtUpdate = conn.prepareStatement(updateDonHang);
        stmtUpdate.setDouble(1, Double.parseDouble(lblTongTien.getText()));  // Tổng tiền
        stmtUpdate.setString(2, cboPTTT.getSelectedItem().toString());  // Phương thức thanh toán
        stmtUpdate.setInt(3, maDonHang);  // Mã đơn hàng
        stmtUpdate.executeUpdate();

        JOptionPane.showMessageDialog(this, "Đơn hàng " + maDonHang + " thanh toán thành công!");
        resetOrder();  // Reset bảng đơn hàng sau khi thanh toán
    }

// Xử lý thanh toán cho đơn hàng mới
    public void processNewOrderPayment(Connection conn) throws Exception {

        try {
            conn = DBConnect.getConnection();
            conn.setAutoCommit(false);  // Bắt đầu giao dịch

            // Lập danh sách chi tiết đơn hàng (MaSanPham, SoLuong, ThanhTien)
            String maNhanVien = FormDangNhap.maNhanVien;
            List<Object[]> orderDetails = new ArrayList<>();
            for (int i = 0; i < tblOrder.getRowCount(); i++) {
                String tenSanPham = String.valueOf(tblOrder.getValueAt(i, 0));  // Tên sản phẩm
                String soLuong = String.valueOf(tblOrder.getValueAt(i, 1));    // Số lượng
                double donGia = Double.parseDouble(String.valueOf(tblOrder.getValueAt(i, 2)));  // Đơn giá
                double thanhTien = donGia * Integer.parseInt(soLuong);  // Thành tiền

                // Truy vấn lấy MaSanPham
                String queryMaSanPham = "SELECT MaSanPham FROM SanPham WHERE TenSanPham = ?";
                PreparedStatement stmtMaSanPham = conn.prepareStatement(queryMaSanPham);
                stmtMaSanPham.setString(1, tenSanPham);
                ResultSet rsSanPham = stmtMaSanPham.executeQuery();

                if (rsSanPham.next()) {
                    int maSanPham = rsSanPham.getInt("MaSanPham");
                    orderDetails.add(new Object[]{maSanPham, soLuong, thanhTien});
                } else {
                    JOptionPane.showMessageDialog(this, "Sản phẩm " + tenSanPham + " không tồn tại!");
                    return;
                }
            }

            String queryDonHang = "INSERT INTO DonHang (MaBan, TenKhachHang, NgayGioDat, ThanhTien, TongTien, PhuongThucThanhToan, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmtDonHang = conn.prepareStatement(queryDonHang, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtDonHang.setInt(1, 1);  // Ví dụ: MaBan = 1 (bạn có thể thay bằng giá trị thực tế)
            stmtDonHang.setString(2, txtTenKhachHang.getText());  // Thay bằng giá trị thực tế
            stmtDonHang.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));  // Ngày giờ hiện tại
            stmtDonHang.setDouble(4, Double.parseDouble(lblTongTien.getText()));  // Tổng tiền
            stmtDonHang.setDouble(5, Double.parseDouble(lblThanhTien.getText()));  // Tổng tiền
            stmtDonHang.setString(6, cboPTTT.getSelectedItem().toString());  // Phương thức thanh toán
            stmtDonHang.setString(7, "Đã Thanh Toán");  // Trạng thái mặc định "Chưa Thanh Toán"
            stmtDonHang.executeUpdate();

            // Lấy MaDonHang từ câu lệnh insert
            ResultSet rsDonHang = stmtDonHang.getGeneratedKeys();
            int maDonHang = 0;
            if (rsDonHang.next()) {
                maDonHang = rsDonHang.getInt(1);
            }

            // Insert vào bảng DonHangChiTiet
            String queryDonHangChiTiet = "INSERT INTO DonHangChiTiet (MaDonHang, MaSanPham, SoLuong, ThanhTien) VALUES (?, ?, ?, ?)";
            PreparedStatement stmtDonHangChiTiet = conn.prepareStatement(queryDonHangChiTiet);

            for (Object[] orderDetail : orderDetails) {
                int maSanPham = (int) orderDetail[0];
                int soLuong = Integer.parseInt((String) orderDetail[1]);
                double thanhTien = Double.valueOf(lblTongTien.getText());

                stmtDonHangChiTiet.setInt(1, maDonHang);
                stmtDonHangChiTiet.setInt(2, maSanPham);
                stmtDonHangChiTiet.setInt(3, soLuong);
                stmtDonHangChiTiet.setDouble(4, thanhTien);
                stmtDonHangChiTiet.executeUpdate();
            }

            // Insert vào bảng HoaDon
            String queryHoaDon = "INSERT INTO HoaDon (MaDonHang, MaNhanVien, NgayLap, TongTien, PhuongThucThanhToan) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmtHoaDon = conn.prepareStatement(queryHoaDon, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtHoaDon.setInt(1, maDonHang);  // Liên kết với MaDonHang
            stmtHoaDon.setInt(2, Integer.valueOf(maNhanVien));
            stmtHoaDon.setDate(3, new java.sql.Date(System.currentTimeMillis()));  // Ngày lập
            stmtHoaDon.setDouble(4, Double.parseDouble(lblThanhTien.getText()));  // Tổng tiền
            stmtHoaDon.setString(5, cboPTTT.getSelectedItem().toString());  // Phương thức thanh toán
            stmtHoaDon.executeUpdate();

            // Lấy MaHoaDon từ câu lệnh insert
            ResultSet rsHoaDon = stmtHoaDon.getGeneratedKeys();
            int maHoaDon = 0;
            if (rsHoaDon.next()) {
                maHoaDon = rsHoaDon.getInt(1);
            }

            // Insert vào bảng HoaDonChiTiet
            String queryHoaDonChiTiet = "INSERT INTO HoaDonChiTiet (MaHoaDon, MaSanPham, SoLuong, DoanhThuSanPham) VALUES (?, ?, ?, ?)";
            PreparedStatement stmtHoaDonChiTiet = conn.prepareStatement(queryHoaDonChiTiet);

            for (Object[] orderDetail : orderDetails) {
                int maSanPham = (int) orderDetail[0];
                int soLuong = Integer.parseInt((String) orderDetail[1]);
                double thanhTien = Double.valueOf(lblTongTien.getText());

                stmtHoaDonChiTiet.setInt(1, maHoaDon);
                stmtHoaDonChiTiet.setInt(2, maSanPham);
                stmtHoaDonChiTiet.setInt(3, soLuong);
                stmtHoaDonChiTiet.setDouble(4, thanhTien);
                stmtHoaDonChiTiet.executeUpdate();
            }

            // Commit giao dịch nếu tất cả thành công
            conn.commit();
            JOptionPane.showMessageDialog(this, "Đơn hàng " + maDonHang + " thanh toán thành công\nMã hóa đơn: " + maHoaDon);
            resetOrder();  // Reset bảng đơn hàng sau khi thanh toán

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Thanh toán không thành công!");
            if (conn != null) {
                try {
                    conn.rollback();  // Rollback nếu có lỗi
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();  // Đóng kết nối
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void resetOrder() {
        model.setRowCount(0);  // Clear all data in the order table
        lblTongTien.setText("");
        lblThanhTien.setText("");
        txtKhachDua.setText("");
        lblTraLai.setText("");
    }

    private void ThemDonHang() {
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            conn.setAutoCommit(false);  // Bắt đầu giao dịch

            // Lập danh sách chi tiết đơn hàng (MaSanPham, SoLuong, ThanhTien)
            List<Object[]> orderDetails = new ArrayList<>();
            for (int i = 0; i < tblOrder.getRowCount(); i++) {
                String tenSanPham = String.valueOf(tblOrder.getValueAt(i, 0));  // Tên sản phẩm
                String soLuong = String.valueOf(tblOrder.getValueAt(i, 1));    // Số lượng
                double donGia = Double.parseDouble(String.valueOf(tblOrder.getValueAt(i, 2)));  // Đơn giá
                double thanhTien = donGia * Integer.parseInt(soLuong);  // Thành tiền

                // Truy vấn lấy MaSanPham
                String queryMaSanPham = "SELECT MaSanPham FROM SanPham WHERE TenSanPham = ?";
                PreparedStatement stmtMaSanPham = conn.prepareStatement(queryMaSanPham);
                stmtMaSanPham.setString(1, tenSanPham);
                ResultSet rsSanPham = stmtMaSanPham.executeQuery();
                if (rsSanPham.next()) {
                    int maSanPham = rsSanPham.getInt("MaSanPham");
                    orderDetails.add(new Object[]{maSanPham, soLuong, thanhTien});
                } else {
                    JOptionPane.showMessageDialog(this, "Sản phẩm " + tenSanPham + " không tồn tại!");
                    return;
                }
            }

            // Insert vào bảng DonHang với trạng thái "Chưa Thanh Toán"
            String queryDonHang = "INSERT INTO DonHang (MaBan, TenKhachHang, NgayGioDat, ThanhTien, TongTien, PhuongThucThanhToan, TrangThai, MaKhachHang) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmtDonHang = conn.prepareStatement(queryDonHang, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtDonHang.setInt(1, 1);  // Ví dụ: MaBan = 1 (bạn có thể thay bằng giá trị thực tế)
            stmtDonHang.setString(2, txtTenKhachHang.getText());  // Thay bằng giá trị thực tế
            stmtDonHang.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));  // Ngày giờ hiện tại
            stmtDonHang.setDouble(4, Double.parseDouble(lblTongTien.getText()));  // Tổng tiền
            stmtDonHang.setDouble(5, Double.parseDouble(lblThanhTien.getText()));  // Tổng tiền
            stmtDonHang.setString(6, cboPTTT.getSelectedItem().toString());  // Phương thức thanh toán
            stmtDonHang.setString(7, "Chưa Thanh Toán");  // Trạng thái mặc định "Chưa Thanh Toán"
            stmtDonHang.setInt(8, Integer.valueOf(txtMaKhachHang.getText()));
            stmtDonHang.executeUpdate();

            // Lấy MaDonHang từ câu lệnh insert
            ResultSet rsDonHang = stmtDonHang.getGeneratedKeys();
            int maDonHang = 0;
            if (rsDonHang.next()) {
                maDonHang = rsDonHang.getInt(1);
            }

            // Insert vào bảng DonHangChiTiet
            String queryDonHangChiTiet = "INSERT INTO DonHangChiTiet (MaDonHang, MaSanPham, SoLuong, ThanhTien) VALUES (?, ?, ?, ?)";
            PreparedStatement stmtDonHangChiTiet = conn.prepareStatement(queryDonHangChiTiet);
            for (Object[] orderDetail : orderDetails) {
                int maSanPham = (int) orderDetail[0];
                int soLuong = Integer.parseInt((String) orderDetail[1]);
                double thanhTien = (double) orderDetail[2];  // ThanhTien được tính cho từng sản phẩm
                stmtDonHangChiTiet.setInt(1, maDonHang);
                stmtDonHangChiTiet.setInt(2, maSanPham);
                stmtDonHangChiTiet.setInt(3, soLuong);
                stmtDonHangChiTiet.setDouble(4, thanhTien);
                stmtDonHangChiTiet.executeUpdate();
            }

            conn.commit();  // Commit giao dịch

            JOptionPane.showMessageDialog(this, "Thêm đơn hàng thành công, mã đơn hàng là : " + maDonHang);
            resetOrder();  // Reset bảng đơn hàng sau khi thanh toán
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();  // Rollback nếu có lỗi
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();  // Đóng kết nối
                } catch (Exception closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
        fillToTableDonHangCho();
    }

    private void CapNhatDonHang(int maDonHang) {
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            conn.setAutoCommit(false);  // Bắt đầu giao dịch

            // Lập danh sách chi tiết đơn hàng (MaSanPham, SoLuong, ThanhTien)
            List<Object[]> orderDetails = new ArrayList<>();
            for (int i = 0; i < tblOrder.getRowCount(); i++) {
                String tenSanPham = String.valueOf(tblOrder.getValueAt(i, 0));  // Tên sản phẩm
                String soLuong = String.valueOf(tblOrder.getValueAt(i, 1));    // Số lượng
                double donGia = Double.parseDouble(String.valueOf(tblOrder.getValueAt(i, 2)));  // Đơn giá
                double thanhTien = donGia * Integer.parseInt(soLuong);  // Thành tiền

                // Truy vấn lấy MaSanPham
                String queryMaSanPham = "SELECT MaSanPham FROM SanPham WHERE TenSanPham = ?";
                PreparedStatement stmtMaSanPham = conn.prepareStatement(queryMaSanPham);
                stmtMaSanPham.setString(1, tenSanPham);
                ResultSet rsSanPham = stmtMaSanPham.executeQuery();
                if (rsSanPham.next()) {
                    int maSanPham = rsSanPham.getInt("MaSanPham");
                    orderDetails.add(new Object[]{maSanPham, soLuong, thanhTien});
                } else {
                    JOptionPane.showMessageDialog(this, "Sản phẩm " + tenSanPham + " không tồn tại!");
                    return;
                }
            }

            // Cập nhật thông tin đơn hàng trong bảng DonHang
            String queryDonHang = "UPDATE DonHang SET MaKhachHang = ?, TenKhachHang = ?, NgayGioDat = ?, TongTien = ?, PhuongThucThanhToan = ?, TrangThai = ? WHERE MaDonHang = ?";
            PreparedStatement stmtDonHang = conn.prepareStatement(queryDonHang);
            stmtDonHang.setInt(1,Integer.valueOf(txtMaKhachHang.getText()));  // Thay bằng giá trị thực tế
            stmtDonHang.setString(2, txtTenKhachHang.getText());  // Thay bằng giá trị thực tế
            stmtDonHang.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));  // Ngày giờ hiện tại
            stmtDonHang.setDouble(4, Double.parseDouble(lblTongTien.getText()));  // Tổng tiền
            stmtDonHang.setString(5, cboPTTT.getSelectedItem().toString());  // Phương thức thanh toán
            stmtDonHang.setString(6, "Chưa Thanh Toán");  // Trạng thái (có thể thay đổi tùy yêu cầu)
            stmtDonHang.setInt(7, maDonHang);  // Cập nhật đơn hàng theo MaDonHang
            stmtDonHang.executeUpdate();

            // Xóa các chi tiết đơn hàng cũ trong DonHangChiTiet trước khi cập nhật
            String queryDeleteDetails = "DELETE FROM DonHangChiTiet WHERE MaDonHang = ?";
            PreparedStatement stmtDeleteDetails = conn.prepareStatement(queryDeleteDetails);
            stmtDeleteDetails.setInt(1, maDonHang);
            stmtDeleteDetails.executeUpdate();

            // Insert lại các chi tiết đơn hàng mới vào DonHangChiTiet
            String queryDonHangChiTiet = "INSERT INTO DonHangChiTiet (MaDonHang, MaSanPham, SoLuong, ThanhTien) VALUES (?, ?, ?, ?)";
            PreparedStatement stmtDonHangChiTiet = conn.prepareStatement(queryDonHangChiTiet);
            for (Object[] orderDetail : orderDetails) {
                int maSanPham = (int) orderDetail[0];
                int soLuong = Integer.parseInt((String) orderDetail[1]);
                double thanhTien = (double) orderDetail[2];  // ThanhTien được tính cho từng sản phẩm
                stmtDonHangChiTiet.setInt(1, maDonHang);
                stmtDonHangChiTiet.setInt(2, maSanPham);
                stmtDonHangChiTiet.setInt(3, soLuong);
                stmtDonHangChiTiet.setDouble(4, thanhTien);
                stmtDonHangChiTiet.executeUpdate();
            }

            conn.commit();  // Commit giao dịch
            JOptionPane.showMessageDialog(this, "Cập nhật đơn hàng thành công, mã đơn hàng là : " + maDonHang);
            resetOrder();  // Reset bảng đơn hàng sau khi thanh toán
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();  // Rollback nếu có lỗi
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();  // Đóng kết nối
                } catch (Exception closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
        fillToTableDonHangCho();  // Cập nhật lại bảng DonHangCho
    }

    public void fillToTableDonHangCho() {
        ArrayList<DonHang> list = rp.donHangCho();

        // Xóa dữ liệu cũ (nếu có)
        model2.setRowCount(0);

        // Thêm dữ liệu vào model
        for (DonHang dh : list) {
            model2.addRow(new Object[]{
                dh.getMaDonHang(), dh.getMaBan(), dh.getTenKhachHang(),
                dh.getNgayGioDat(), dh.getTongTien()
            });
        }
    }

    private void huyDonHang() {

        int selectedRow = tblDonHangCho.getSelectedRow();
        if (selectedRow != -1) {
            int maDonHang = (int) tblDonHangCho.getValueAt(selectedRow, 0); // Giả sử mã đơn hàng là cột đầu tiên
            boolean isHuyThanhCong = rp.huyDonHang(maDonHang); // Gọi phương thức huyDonHang

            if (isHuyThanhCong) {
                JOptionPane.showMessageDialog(this, "Đơn hàng đã được hủy thành công.");
                // Cập nhật lại bảng dữ liệu sau khi hủy đơn hàng
                ((DefaultTableModel) tblDonHangCho.getModel()).removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra. Vui lòng thử lại.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một đơn hàng để hủy.");
        }
        fillToTableDonHangCho();
    }

    public void fillToTableOrder() {
        ShowChiTietDonHang sctdh = new ShowChiTietDonHang();
        sctdh.loadChiTietDonHang2();

        // Xóa dữ liệu cũ (nếu có)
        ArrayList<DonHangChiTiet> dhct = new ArrayList<>();

        for (DonHangChiTiet dh : dhct) {
            model.addRow(new Object[]{
                dh.getTenSanPham(), dh.getSoLuong(),
                dh.getDonGia(), dh.getThanhTien()
            });
        }
        // Thêm dữ liệu vào model

    }

    private String orderDetails;  // Biến lưu thông tin đơn hàng

    private void loadOrderDetails(int maDonHang) {
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            // Truy vấn thông tin đơn hàng từ bảng DonHang
            String queryDonHang = "SELECT dh.MaDonHang, dh.TenKhachHang, dh.MaKhachHang, dh.TongTien, "
                    + "dh.PhuongThucThanhToan, dhc.MaSanPham, dhc.SoLuong, sp.TenSanPham, sp.DonGia, "
                    + "(dhc.SoLuong * sp.DonGia) AS ThanhTien "
                    + "FROM DonHang dh "
                    + "INNER JOIN DonHangChiTiet dhc ON dh.MaDonHang = dhc.MaDonHang "
                    + "INNER JOIN SanPham sp ON dhc.MaSanPham = sp.MaSanPham "
                    + "WHERE dh.MaDonHang = ?";
            PreparedStatement stmtDonHang = conn.prepareStatement(queryDonHang);
            stmtDonHang.setInt(1, maDonHang);
            ResultSet rsDonHang = stmtDonHang.executeQuery();

            StringBuilder orderDetailsBuilder = new StringBuilder();
            // Kiểm tra kết quả truy vấn
            if (rsDonHang.next()) {
                // Lưu thông tin đơn hàng vào các biến
                String tenKhachHang = rsDonHang.getString("TenKhachHang");
                int maKhachHang = rsDonHang.getInt("MaKhachHang");
                double tongTien = rsDonHang.getDouble("TongTien");
                String phuongThucThanhToan = rsDonHang.getString("PhuongThucThanhToan");

                txtTenKhachHang.setText(tenKhachHang);
                txtMaKhachHang.setText(String.valueOf(maKhachHang));
                lblTongTien.setText(String.valueOf(tongTien));
                cboPTTT.setSelectedItem(phuongThucThanhToan);

                // Lưu thông tin đơn hàng vào orderDetailsBuilder
                orderDetailsBuilder.append("MaDonHang=").append(maDonHang)
                        .append("&TenKhachHang=").append(tenKhachHang)
                        .append("&MaKhachHang=").append(maKhachHang)
                        .append("&TongTien=").append(tongTien)
                        .append("&PhuongThucThanhToan=").append(phuongThucThanhToan);

                // Hiển thị chi tiết sản phẩm vào bảng tblOrder
                DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
                model.setRowCount(0);  // Xóa dữ liệu cũ trước khi thêm dữ liệu mới
                double totalThanhTien = 0;
                do {
                    String tenSanPham = rsDonHang.getString("TenSanPham");
                    int soLuong = rsDonHang.getInt("SoLuong");
                    double donGia = rsDonHang.getDouble("DonGia");
                    double thanhTien = rsDonHang.getDouble("ThanhTien");
                    totalThanhTien += thanhTien;
                    model.addRow(new Object[]{
                        tenSanPham,
                        soLuong,
                        donGia,
                        thanhTien
                    });
                } while (rsDonHang.next());
                lblThanhTien.setText(String.valueOf(totalThanhTien));
            }

            // Lưu thông tin đơn hàng vào biến toàn cục orderDetails
            orderDetails = orderDetailsBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();  // Đóng kết nối
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

// Phương thức tạo mã QR cho đơn hàng
    private void createQRCodeForOrder() {
        // Check if a row is selected in the table
        int selectedRow = tblDonHangCho.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đơn hàng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return; // Exit if no row is selected
        }

        // Get the order ID from the selected row (assuming it's in the first column)
        Integer maDonHang = (Integer) tblDonHangCho.getValueAt(selectedRow, 0);

        if (maDonHang == null) {
            JOptionPane.showMessageDialog(this, "Không thể xác định mã đơn hàng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String maDonHangString = String.valueOf(maDonHang);

        // Fetch order details from the database
        String orderDetails = "";
        String query = "SELECT dh.MaDonHang, dh.TenKhachHang, dh.NgayGioDat, dh.TongTien, dh.PhuongThucThanhToan, dh.TrangThai, "
                + "dct.MaSanPham, sp.TenSanPham, dct.SoLuong, dct.ThanhTien "
                + "FROM DonHang dh "
                + "JOIN DonHangChiTiet dct ON dh.MaDonHang = dct.MaDonHang "
                + "JOIN SanPham sp ON dct.MaSanPham = sp.MaSanPham "
                + "WHERE dh.MaDonHang = ?";

        try ( Connection con = DBConnect.getConnection();  PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, maDonHang);  // Set the order ID as parameter

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Build the order details string to include in the QR code
                orderDetails += "Mã Đơn Hàng: " + maDonHangString + "\n";
                orderDetails += "Khách Hàng: " + rs.getString("TenKhachHang") + "\n";
                orderDetails += "Ngày Giờ Đặt: " + rs.getTimestamp("NgayGioDat") + "\n";
                String phuongThucThanhToan = "Chuyển khoản";  // Default value
                orderDetails += "Phương Thức Thanh Toán: " + phuongThucThanhToan + "\n";  // Use default method
                orderDetails += "Trạng Thái: " + rs.getString("TrangThai") + "\n";
                orderDetails += "Tổng Tiền: " + rs.getBigDecimal("TongTien") + "\n";
                orderDetails += "Sản Phẩm:\n";
            }

            // Loop through the remaining results to append product detailscvo
            do {
                orderDetails += "- " + rs.getString("TenSanPham") + " (Số lượng: " + rs.getInt("SoLuong") + ", Thành tiền: " + rs.getDouble("ThanhTien") + ")\n";
            } while (rs.next());

            // Path to save the QR code image
            String qrCodeFilePath = "D:\\DuAn1\\maQR\\MaDonHang_" + maDonHangString + ".png";

            // Generate the QR code
            QRCodeGenerator.generateQRCode(orderDetails, qrCodeFilePath);

            // Show a success message
            JOptionPane.showMessageDialog(this, "Mã QR đã được tạo thành công!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tạo mã QR: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void updateDiscount() throws SQLException {
        try {
            // Get the total amount text
            String tongTienText = lblTongTien.getText().trim();

            // Check if the total amount is valid and greater than 0
            if (tongTienText.isEmpty() || Double.parseDouble(tongTienText) <= 0) {
                lblGiamGia.setText("0");
                lblThanhTien.setText("0");
                return;
            }

            // Parse valid total amount
            double tongTien = Double.parseDouble(tongTienText);

            // Get the discount based on the customer ID
            int maKhachHang = Integer.parseInt(txtMaKhachHang.getText());
            double mucGiamGia = VoucherRepo.getVoucherDiscount(maKhachHang);

            // Calculate the final amount after discount
            double thanhTien = tongTien - mucGiamGia;

            // Update labels
            lblGiamGia.setText(String.format("%.2f", mucGiamGia));
            lblThanhTien.setText(String.format("%.2f", thanhTien));
        } catch (NumberFormatException e) {
            // Handle invalid inputs gracefully
            lblGiamGia.setText("0");
            lblThanhTien.setText("0");
        }
    }

    private void updateProductImages() {
        // Danh sách các JLabel chứa tên sản phẩm
        JLabel[] lblTenList = {
            lblTen1, lblTen2, lblTen3, lblTen4, lblTen5, lblTen6,
            lblTen7, lblTen8, lblTen9, lblTen10, lblTen11, lblTen12
        };

        // Danh sách các JLabel cần hiển thị ảnh
        JLabel[] lblAnhList = {
            lblAnh1, lblAnh2, lblAnh3, lblAnh4, lblAnh5, lblAnh6,
            lblAnh7, lblAnh8, lblAnh9, lblAnh10, lblAnh11, lblAnh12
        };

        // Kích thước của ảnh cho mỗi JLabel
        int width = 168;  // Chiều rộng của ảnh
        int height = 220; // Chiều cao của ảnh

        // Duyệt qua từng JLabel để lấy tên sản phẩm và hiển thị ảnh
        for (int i = 0; i < lblTenList.length; i++) {
            String tenSanPham = lblTenList[i].getText();  // Lấy tên sản phẩm từ JLabel

            // Lấy ảnh từ cơ sở dữ liệu dựa trên tên sản phẩm
            ImageIcon anh = LoadAnhFromDB.getImageFromDatabaseByName(tenSanPham);

            if (anh != null) {
                // Nếu có ảnh, thay đổi kích thước của ảnh theo kích thước của JLabel
                Image img = anh.getImage();
                Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Điều chỉnh kích thước
                lblAnhList[i].setIcon(new ImageIcon(newImg));
            } else {
                // Nếu không có ảnh, hiển thị thông báo
                lblAnhList[i].setText("Không có ảnh");
            }
        }
    }

    private void searchProduct(String selectedCategory) {
        String searchName = txtTenSanPham.getText().trim();  // Lấy tên sản phẩm từ JTextField

        // Nếu loại sản phẩm là "Tất cả" thì không cần lọc theo loại
        String sql = "SELECT * FROM SanPham WHERE TenSanPham LIKE ?";

        // Nếu loại không phải "Tất cả", thêm điều kiện lọc theo loại
        if (!"Tất cả".equals(selectedCategory) && !selectedCategory.isEmpty()) {
            sql += " AND Loai LIKE ?";
        }

        try ( Connection conn = DBConnect.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            // Set tham số cho câu lệnh SQL
            ps.setString(1, "%" + searchName + "%");  // Lọc theo tên sản phẩm

            // Nếu loại không phải "Tất cả", thêm tham số cho loại sản phẩm
            if (!"Tất cả".equals(selectedCategory) && !selectedCategory.isEmpty()) {
                ps.setString(2, "%" + selectedCategory + "%");  // Lọc theo loại sản phẩm
            }

            ResultSet rs = ps.executeQuery();

            // Làm sạch các JLabel trước khi cập nhật
            clearLabels();

            // Ẩn tất cả các panel ban đầu
            panelSP1.setVisible(false);
            panelSP2.setVisible(false);
            panelSP3.setVisible(false);
            panelSP4.setVisible(false);
            panelSP5.setVisible(false);
            panelSP6.setVisible(false);
            panelSP7.setVisible(false);
            panelSP8.setVisible(false);
            panelSP9.setVisible(false);
            panelSP10.setVisible(false);
            panelSP11.setVisible(false);
            panelSP12.setVisible(false);

            // Chỉ số để theo dõi các label hiện tại
            int index = 1;

            // Lặp qua kết quả truy vấn và hiển thị lên các JLabel
            while (rs.next()) {
                if (index > 12) {
                    break;  // Giới hạn số lượng sản phẩm hiển thị
                }
                String tenSanPham = rs.getString("TenSanPham");
                byte[] anh = rs.getBytes("Anh");
                double gia = rs.getDouble("DonGia");

                // Chuyển ảnh từ byte[] sang ImageIcon nếu có
                ImageIcon imageIcon = null;
                if (anh != null) {
                    imageIcon = new ImageIcon(anh);
                }

                // Hiển thị thông tin sản phẩm lên các JLabel tương ứng
                switch (index) {
                    case 1:
                        lblAnh1.setIcon(imageIcon);
                        lblTen1.setText(tenSanPham);
                        lblGia1.setText(String.valueOf(gia));
                        panelSP1.setVisible(true);  // Hiển thị panelSP1
                        break;
                    case 2:
                        lblAnh2.setIcon(imageIcon);
                        lblTen2.setText(tenSanPham);
                        lblGia2.setText(String.valueOf(gia));
                        panelSP2.setVisible(true);  // Hiển thị panelSP2
                        break;
                    case 3:
                        lblAnh3.setIcon(imageIcon);
                        lblTen3.setText(tenSanPham);
                        lblGia3.setText(String.valueOf(gia));
                        panelSP3.setVisible(true);  // Hiển thị panelSP3
                        break;
                    case 4:
                        lblAnh4.setIcon(imageIcon);
                        lblTen4.setText(tenSanPham);
                        lblGia4.setText(String.valueOf(gia));
                        panelSP4.setVisible(true);  // Hiển thị panelSP4
                        break;
                    case 5:
                        lblAnh5.setIcon(imageIcon);
                        lblTen5.setText(tenSanPham);
                        lblGia5.setText(String.valueOf(gia));
                        panelSP5.setVisible(true);  // Hiển thị panelSP5
                        break;
                    case 6:
                        lblAnh6.setIcon(imageIcon);
                        lblTen6.setText(tenSanPham);
                        lblGia6.setText(String.valueOf(gia));
                        panelSP6.setVisible(true);  // Hiển thị panelSP6
                        break;
                    case 7:
                        lblAnh7.setIcon(imageIcon);
                        lblTen7.setText(tenSanPham);
                        lblGia7.setText(String.valueOf(gia));
                        panelSP7.setVisible(true);  // Hiển thị panelSP7
                        break;
                    case 8:
                        lblAnh8.setIcon(imageIcon);
                        lblTen8.setText(tenSanPham);
                        lblGia8.setText(String.valueOf(gia));
                        panelSP8.setVisible(true);  // Hiển thị panelSP8
                        break;
                    case 9:
                        lblAnh9.setIcon(imageIcon);
                        lblTen9.setText(tenSanPham);
                        lblGia9.setText(String.valueOf(gia));
                        panelSP9.setVisible(true);  // Hiển thị panelSP9
                        break;
                    case 10:
                        lblAnh10.setIcon(imageIcon);
                        lblTen10.setText(tenSanPham);
                        lblGia10.setText(String.valueOf(gia));
                        panelSP10.setVisible(true);  // Hiển thị panelSP10
                        break;
                    case 11:
                        lblAnh11.setIcon(imageIcon);
                        lblTen11.setText(tenSanPham);
                        lblGia11.setText(String.valueOf(gia));
                        panelSP11.setVisible(true);  // Hiển thị panelSP11
                        break;
                    case 12:
                        lblAnh12.setIcon(imageIcon);
                        lblTen12.setText(tenSanPham);
                        lblGia12.setText(String.valueOf(gia));
                        panelSP12.setVisible(true);  // Hiển thị panelSP12
                        break;
                }
                index++;
            }

            // Đóng kết nối
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void clearLabels() {
        // Làm sạch các JLabel trước khi cập nhật
        lblAnh1.setIcon(null);
        lblAnh2.setIcon(null);
        lblAnh3.setIcon(null);
        lblAnh4.setIcon(null);
        lblAnh5.setIcon(null);
        lblAnh6.setIcon(null);
        lblAnh7.setIcon(null);
        lblAnh8.setIcon(null);
        lblAnh9.setIcon(null);
        lblAnh10.setIcon(null);
        lblAnh11.setIcon(null);
        lblAnh12.setIcon(null);

        lblTen1.setText("");
        lblTen2.setText("");
        lblTen3.setText("");
        lblTen4.setText("");
        lblTen5.setText("");
        lblTen6.setText("");
        lblTen7.setText("");
        lblTen8.setText("");
        lblTen9.setText("");
        lblTen10.setText("");
        lblTen11.setText("");
        lblTen12.setText("");

        lblGia1.setText("");
        lblGia2.setText("");
        lblGia3.setText("");
        lblGia4.setText("");
        lblGia5.setText("");
        lblGia6.setText("");
        lblGia7.setText("");
        lblGia8.setText("");
        lblGia9.setText("");
        lblGia10.setText("");
        lblGia11.setText("");
        lblGia12.setText("");
    }

}
