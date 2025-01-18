/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.QuanLyNhanVien;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.NhanVien;
import repository.LoadAnhFromDB;
import repository.LoadAnhToDB;
import repository.QLNVrepo;
import view.TrangChu.*;
import view.QuanLyNhanVien.FormQuanLyNhanVien;

/**
 *
 * @author Admin
 */
public class FormQuanLyNhanVien extends javax.swing.JFrame {

    /**
     * Creates new form FormTrangChu
     */
    int index;

    public FormQuanLyNhanVien() {
        initComponents();
        setLocationRelativeTo(null);
        loadNhanVienToTable();
        if (tblNhanVien.getRowCount() > 0) {
            showDetail();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        rdoNu = new javax.swing.JRadioButton();
        rdoNam = new javax.swing.JRadioButton();
        jLabel38 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtHoten = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lblAnh = new javax.swing.JLabel();
        btnChonAnh = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Giới tính", "Ngày sinh", "SĐT", "Địa chỉ"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user_groups_25px.png"))); // NOI18N
        jLabel4.setText("Quản lý nhân viên");

        jLabel34.setText("Địa chỉ:");

        jLabel35.setText("Số điện thoại:");

        jLabel36.setText("Ngày sinh:");

        jLabel3.setText("Họ tên:");

        jLabel37.setText("Mã nhân viên:");

        rdoNu.setText("Nữ");

        rdoNam.setText("Nam");

        jLabel38.setText("Giới tính:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.two/search-line-icon.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên nhân viên");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add_25px.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit_25px.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete_25px.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnChonAnh.setText("Chọn ảnh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel37)
                                .addComponent(jLabel3)
                                .addComponent(jLabel36))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel35)
                                .addComponent(jLabel34)
                                .addComponent(jLabel38))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(32, 32, 32)
                                    .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel4))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(75, 75, 75)
                                        .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(152, 152, 152)
                                        .addComponent(btnChonAnh)))))))
                .addContainerGap(258, Short.MAX_VALUE))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtDiaChi, txtHoten, txtMaNV, txtNgaySinh, txtSDT});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel34)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(jButton4)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap(102, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChonAnh)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(135, 135, 135))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtDiaChi, txtHoten, txtMaNV, txtNgaySinh, txtSDT});

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1440, 660));

        jPanel3.setBackground(new java.awt.Color(255, 153, 51));

        jLabel5.setFont(new java.awt.Font("Dialog", 3, 35)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Three Restaurant FPOLY");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.two/home50px.png"))); // NOI18N
        jLabel6.setText("jLabel4");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        lblRole.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblRole.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/male_user_50px.png"))); // NOI18N
        lblRole.setText("admin");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 829, Short.MAX_VALUE)
                .addComponent(lblRole)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblRole))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(22, 22, 22))))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1440, 100));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        showDetail();
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        searchNhanVien();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        addNhanVien();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        updateNhanVien();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        deleteNhanVien();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        FormTrangChu tc = new FormTrangChu();
        tc.show();
        dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "jpeg", "png"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();

            // Hiển thị ảnh trên JLabel
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), Image.SCALE_SMOOTH));
            lblAnh.setIcon(imageIcon);

            try {
                // Lấy mã sản phẩm từ text field
                String maNV = txtMaNV.getText();
                if (maNV.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn ảnh trước khi lưu ảnh.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int maNhanVien = Integer.parseInt(maNV);

                // Gọi phương thức trong repository để lưu ảnh vào cơ sở dữ liệu
                LoadAnhToDB.saveImageToDatabase(maNhanVien, imagePath);

                JOptionPane.showMessageDialog(this, "Ảnh đã được lưu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Mã không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi lưu ảnh: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnChonAnhActionPerformed

    private void tblNhanVienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblNhanVienMouseEntered

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
            java.util.logging.Logger.getLogger(FormQuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormQuanLyNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblRole;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    // Phương thức hiển thị danh sách nhân viên vào bảng
    private void loadNhanVienToTable() {
        QLNVrepo repo = new QLNVrepo();
        List<NhanVien> listNV = repo.getAll();
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0); // Xóa các dòng cũ
        for (NhanVien nv : listNV) {
            Object[] row = {
                nv.getMaNhanVien(),
                nv.getTenNhanVien(),
                nv.isGioiTinh() ? "Nam" : "Nữ",
                nv.getNgaySinh(),
                nv.getSoDienThoai(),
                nv.getDiaChi(),
                // Kiểm tra xem ảnh có tồn tại hay không trước khi tạo ImageIcon
                (nv.getAnh() != null) ? new ImageIcon(nv.getAnh()) : null
            };
            model.addRow(row);
        }
        if (model.getRowCount() > 0) {
            tblNhanVien.setRowSelectionInterval(0, 0); // Chọn hàng đầu tiên
            showDetail(); // Hiển thị thông tin chi tiết của hàng đầu tiên
        }
    }

    // Phương thức xóa nhân viên
    private void deleteNhanVien() {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String maNhanVienStr = txtMaNV.getText();
            if (maNhanVienStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên cần xóa.");
                return;
            }
            int maNhanVien = Integer.parseInt(maNhanVienStr);
            QLNVrepo repo = new QLNVrepo();
            int result = repo.deleteNV(maNhanVien);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công.");
                loadNhanVienToTable(); // Cập nhật lại bảng sau khi xóa
            } else {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại.");
            }
        }
    }

    // Phương thức thêm nhân viên
    private void addNhanVien() {
        // Confirm addition
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn thêm nhân viên này?",
                "Xác nhận thêm",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Get and validate employee ID
                int maNhanVien = Integer.parseInt(txtMaNV.getText().trim());

                // Get and validate employee name
                String tenNhanVien = txtHoten.getText().trim();
                if (tenNhanVien.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Họ tên không được để trống.");
                    return;
                }

                // Get gender
                boolean gioiTinh = rdoNam.isSelected();

                // Get and validate date of birth
                java.sql.Date ngaySinh;
                try {
                    ngaySinh = java.sql.Date.valueOf(txtNgaySinh.getText().trim()); // Format: yyyy-MM-dd
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd.");
                    return;
                }

                // Get and validate phone number
                String soDienThoai = txtSDT.getText().trim();
                if (!soDienThoai.matches("^0\\d{9}$")) { // Matches numbers starting with 0 and exactly 10 digits
                    JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu bằng 0 và có đúng 10 chữ số.");
                    return;
                }

                // Get and validate address
                String diaChi = txtDiaChi.getText().trim();
                if (diaChi.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống.");
                    return;
                }

                // Create employee object
                NhanVien nv = new NhanVien(maNhanVien, maNhanVien, tenNhanVien, gioiTinh, ngaySinh, soDienThoai, diaChi, null);

                // Save to database
                QLNVrepo repo = new QLNVrepo();
                int result = repo.addNV(nv);

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công.");
                    loadNhanVienToTable(); // Update table
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên phải là số.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
            }
        }
    }

    // Phương thức cập nhật nhân viên
    private void updateNhanVien() {
        // Confirm update
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn cập nhật nhân viên này?",
                "Xác nhận cập nhật",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Validate employee ID
                String maNhanVienStr = txtMaNV.getText().trim();
                if (maNhanVienStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên cần cập nhật.");
                    return;
                }
                int maNhanVien = Integer.parseInt(maNhanVienStr);

                // Validate name
                String tenNhanVien = txtHoten.getText().trim();
                if (tenNhanVien.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Họ tên không được để trống.");
                    return;
                }

                // Get gender
                boolean gioiTinh = rdoNam.isSelected();

                // Validate date of birth
                java.sql.Date ngaySinh;
                try {
                    ngaySinh = java.sql.Date.valueOf(txtNgaySinh.getText().trim()); // Format: yyyy-MM-dd
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd.");
                    return;
                }

                // Validate phone number
                String soDienThoai = txtSDT.getText().trim();
                if (!soDienThoai.matches("^0\\d{9}$")) { // Matches numbers starting with 0 and exactly 10 digits
                    JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu bằng 0 và có đúng 10 chữ số.");
                    return;
                }

                // Validate address
                String diaChi = txtDiaChi.getText().trim();
                if (diaChi.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống.");
                    return;
                }

                // Create employee object without the image field
                NhanVien nv = new NhanVien(maNhanVien, maNhanVien, tenNhanVien, gioiTinh, ngaySinh, soDienThoai, diaChi, null);

                // Update in the database
                QLNVrepo repo = new QLNVrepo();
                int result = repo.updateNV(maNhanVien, nv);

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công.");
                    loadNhanVienToTable(); // Refresh table
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thất bại.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên phải là số.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
            }
        }
    }

    // Phương thức hiển thị chi tiết nhân viên
    private void showDetail() {
        int index = tblNhanVien.getSelectedRow();
        if (index >= 0) {
            // Lấy mã nhân viên từ cột đầu tiên
            DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
            String maNVStr = model.getValueAt(index, 0).toString();
            int maNV = Integer.parseInt(maNVStr);

            // Lấy thông tin nhân viên từ database
            QLNVrepo repo = new QLNVrepo();
            NhanVien nv = repo.getNhanVienById(maNV);

            if (nv != null) {
                // Hiển thị dữ liệu lên các trường
                txtMaNV.setText(String.valueOf(nv.getMaNhanVien()));
                txtHoten.setText(nv.getTenNhanVien());
                txtSDT.setText(nv.getSoDienThoai());
                txtNgaySinh.setText(nv.getNgaySinh().toString());
                txtDiaChi.setText(nv.getDiaChi());

                // Hiển thị giới tính
                if (nv.isGioiTinh()) {
                    rdoNam.setSelected(true);
                    rdoNu.setSelected(false);
                } else {
                    rdoNu.setSelected(true);
                    rdoNam.setSelected(false);
                }

                // Hiển thị ảnh (nếu có)
                if (nv.getAnh() != null && nv.getAnh().length > 0) {
                    ImageIcon icon = new ImageIcon(nv.getAnh());
                    lblAnh.setIcon(icon);
                    lblAnh.setText(""); // Xóa thông báo nếu có ảnh
                } else {
                    lblAnh.setIcon(null);
                    lblAnh.setText("Không có ảnh");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên với mã: " + maNV, "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // Phương thức lấy ảnh từ file
    private byte[] getImageBytesFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "png", "gif"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try ( FileInputStream fis = new FileInputStream(file)) {
                byte[] imageBytes = new byte[(int) file.length()];
                fis.read(imageBytes);
                return imageBytes;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Không thể đọc ảnh.");
            }
        }
        return null;
    }

    // Phương thức tìm kiếm nhân viên
    private void searchNhanVien() {
        String tenNhanVien = txtSearch.getText();
        if (tenNhanVien.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên cần tìm kiếm.");
            return;
        }

        QLNVrepo repo = new QLNVrepo();
        List<NhanVien> listNV = repo.timNV(tenNhanVien);
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);  // Clear previous rows

        if (listNV.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên.");
        } else {
            for (NhanVien nv : listNV) {
                model.addRow(new Object[]{
                    nv.getMaNhanVien(), // Employee ID
                    nv.getTenNhanVien(), // Employee Name
                    nv.isGioiTinh() ? "Nam" : "Nữ", // Gender
                    nv.getNgaySinh(), // Birth Date
                    nv.getSoDienThoai(), // Phone
                    nv.getDiaChi() // Address
                });
            }
        }
    }

    private void showEmployeeImage(int maNhanVien) {
        ImageIcon imageIcon = LoadAnhFromDB.getImageFromDatabase(maNhanVien);

        if (imageIcon != null) {
            Image image = imageIcon.getImage(); // Chuyển đổi ImageIcon thành Image
            ImageIcon scaledImageIcon = new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_SMOOTH)); // Điều chỉnh kích thước ảnh
            lblAnh.setIcon(scaledImageIcon); // Gán ảnh vào JLabel
        } else {
            lblAnh.setText("Không có ảnh");
        }
    }
}
