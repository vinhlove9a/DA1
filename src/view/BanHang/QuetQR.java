/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.BanHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.DonHangChiTiet;
import utility.DBConnect;

/**
 *
 * @author Admin
 */
public class QuetQR extends javax.swing.JFrame implements Runnable, ThreadFactory {

    /**
     * Creates new form QuetQR
     */
    private Webcam webcam;
    private WebcamPanel panel;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private boolean isCameraRunning = false;
    private Connection conn;

    public QuetQR() {
        try {
            conn = DBConnect.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(QuetQR.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        initWebcam();
        FormOrder formOrder = new FormOrder(conn);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Gọi các phương thức trong FormOrder khi form QuetQR đóng
                formOrder.fillToTableDonHangCho();
                formOrder.fillToTableOrder();
                try {
                    formOrder.updateDiscount();
                } catch (SQLException ex) {
                    Logger.getLogger(FormOrder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCamera = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelCamera.setPreferredSize(new java.awt.Dimension(640, 480));

        javax.swing.GroupLayout panelCameraLayout = new javax.swing.GroupLayout(panelCamera);
        panelCamera.setLayout(panelCameraLayout);
        panelCameraLayout.setHorizontalGroup(
            panelCameraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        panelCameraLayout.setVerticalGroup(
            panelCameraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 576, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(panelCamera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCamera, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(QuetQR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuetQR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuetQR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuetQR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuetQR().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelCamera;
    // End of variables declaration//GEN-END:variables
    private void initWebcam() {
        // Lấy kích thước của duyemWebcam (JPanel chứa webcam)
        Dimension panelSize = this.getSize();
        if (panelSize.width == 0 || panelSize.height == 0) {
            panelSize = new Dimension(640, 480); // Kích thước mặc định nếu panel chưa render
        }

        // Lấy webcam đầu tiên
        webcam = Webcam.getWebcams().get(0);

        if (webcam.isOpen()) {
            webcam.close();
        }

        // Lấy danh sách kích thước được hỗ trợ và chọn kích thước gần nhất với kích thước panel
        List<Dimension> supportedSizes = Arrays.asList(webcam.getViewSizes());
        Dimension selectedSize = supportedSizes.get(0);
        for (Dimension size : supportedSizes) {
            if (size.width <= panelSize.width && size.height <= panelSize.height) {
                selectedSize = size; // Chọn kích thước phù hợp
            }
        }

        // Đặt kích thước webcam
        webcam.setViewSize(selectedSize);

        // Tạo WebcamPanel để hiển thị video
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(selectedSize);
        panel.setFPSDisplayed(true);

        // Thêm WebcamPanel vào panelCamera
        panelCamera.setLayout(new BorderLayout());
        panelCamera.removeAll(); // Xóa các thành phần cũ (nếu có)
        panelCamera.add(panel, BorderLayout.CENTER); // Đặt webcam panel vào giữa
        panelCamera.revalidate(); // Làm mới giao diện
        panelCamera.repaint(); // Vẽ lại giao diện

        // Thêm panelCamera vào JFrame
        this.setLayout(new BorderLayout());  // Sử dụng BorderLayout
        this.add(panelCamera, BorderLayout.CENTER);  // Thêm panel chứa webcam vào JFrame

        // Đảm bảo JFrame hiển thị
        this.revalidate();  // Làm mới giao diện JFrame
        this.repaint();  // Vẽ lại giao diện
        this.pack();  // Điều chỉnh kích thước JFrame để phù hợp với nội dung
        this.setVisible(true);  // Hiển thị JFrame

        // Chạy webcam trong một luồng riêng biệt
        executor.execute(this);  // Khởi chạy luồng quét mã QR
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);  // Tạm dừng 100ms mỗi lần quét
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                image = webcam.getImage();
                if (image == null) {
                    continue;
                }
            }

            try {
                // Xử lý mã QR
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                result = new MultiFormatReader().decode(bitmap);
            } catch (Exception e) {
                // Bỏ qua lỗi nếu không thể quét mã QR
            }

            if (result != null) {
                // Quét QR Code thành công
                handleQRCodeScan(result.getText());  // Gọi phương thức xử lý kết quả QR
            }
        } while (true);
    }

// Hàm xử lý khi quét thành công
    public void handleQRCodeScan(String qrCodeText) {
        try {
            // Kiểm tra nếu mã QR trống hoặc null
            if (qrCodeText == null || qrCodeText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "QR Code không hợp lệ hoặc trống!");
                return;
            }

            // In ra giá trị từ mã QR để kiểm tra
            System.out.println("QR Code Text: " + qrCodeText);

            // Tách thông tin các tham số từ chuỗi QR
            String[] qrInfo = qrCodeText.split("&");
            int maDonHang = -1;
            double tongTien = 0.0;

            // Tìm kiếm mã đơn hàng và tổng tiền trong các tham số
            for (String param : qrInfo) {
                String[] keyValue = param.split("=");  // Tách key và value
                if (keyValue.length == 2) {
                    if (keyValue[0].equals("MaDonHang")) {
                        maDonHang = Integer.parseInt(keyValue[1]);  // Lấy giá trị mã đơn hàng
                    } else if (keyValue[0].equals("TongTien")) {
                        tongTien = Double.parseDouble(keyValue[1]);  // Lấy giá trị tổng tiền thanh toán
                    }
                }
            }
            // Tiến hành gọi phương thức xử lý thanh toán từ mã QR
            processPaymentFromQRCode(qrCodeText);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ (Mã đơn hàng hoặc Tổng tiền)!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processPaymentFromQRCode(String qrCodeData) {
        try {
            // Kiểm tra dữ liệu mã QR trước khi xử lý
            if (qrCodeData == null || qrCodeData.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Dữ liệu mã QR không hợp lệ hoặc trống!");
                return;
            }
            // In dữ liệu QR Code ra console để kiểm tra
            System.out.println("Dữ liệu mã QR nhận được: " + qrCodeData);

            // Phân tích dữ liệu mã QR
            String[] qrDataLines = qrCodeData.split("\n");

            // Kiểm tra các dòng dữ liệu có đầy đủ không
            if (qrDataLines.length < 6) {
                JOptionPane.showMessageDialog(this, "Dữ liệu mã QR không đầy đủ!");
                return;
            }

            // Tiến hành phân tích các thông tin chi tiết
            String maDonHangString = qrDataLines[0].split(": ")[1];
            String tenKhachHang = qrDataLines[1].split(": ")[1];
            String ngayGioDat = qrDataLines[2].split(": ")[1];
            String phuongThucThanhToan = qrDataLines[3].split(": ")[1];
            String trangThai = qrDataLines[4].split(": ")[1];
            String tongTienString = qrDataLines[5].split(": ")[1];

            // Kiểm tra nếu thiếu thông tin quan trọng
            if (maDonHangString == null || tongTienString == null) {
                JOptionPane.showMessageDialog(this, "Dữ liệu mã QR không đầy đủ!");
                return;
            }

            // Parse tổng tiền
            double tongTien = Double.parseDouble(tongTienString);

            // Kết nối cơ sở dữ liệu và tiến hành xử lý thanh toán
            Connection conn = DBConnect.getConnection();

            // Cập nhật trạng thái thanh toán trong bảng DonHang
            String updateDonHang = "UPDATE DonHang SET TongTien = ?, PhuongThucThanhToan = ?, TrangThai = 'Đã Thanh Toán' WHERE MaDonHang = ?";
            PreparedStatement stmtUpdateDonHang = conn.prepareStatement(updateDonHang);
            stmtUpdateDonHang.setDouble(1, tongTien); // Tổng tiền
            stmtUpdateDonHang.setString(2, phuongThucThanhToan); // Phương thức thanh toán
            stmtUpdateDonHang.setString(3, maDonHangString); // Mã đơn hàng
            stmtUpdateDonHang.executeUpdate();

            // Tiến hành xử lý đơn hàng (thêm vào bảng HoaDon)
            String insertHoaDon = "INSERT INTO HoaDon (MaDonHang, MaNhanVien, NgayLap, TongTien, PhuongThucThanhToan) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmtInsertHoaDon = conn.prepareStatement(insertHoaDon, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtInsertHoaDon.setString(1, maDonHangString); // Mã đơn hàng
            stmtInsertHoaDon.setInt(2, 1); // Mã nhân viên (giả sử là 1, thay đổi nếu cần)
            stmtInsertHoaDon.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis())); // Ngày giờ hiện tại
            stmtInsertHoaDon.setDouble(4, tongTien); // Tổng tiền
            stmtInsertHoaDon.setString(5, phuongThucThanhToan); // Phương thức thanh toán
            stmtInsertHoaDon.executeUpdate();

            // Lấy Mã Hóa Đơn (MaHoaDon) vừa tạo
            ResultSet rsHoaDon = stmtInsertHoaDon.getGeneratedKeys();
            int maHoaDon = 0;
            if (rsHoaDon.next()) {
                maHoaDon = rsHoaDon.getInt(1); // Lấy Mã Hóa Đơn vừa tạo
            }

            // Lấy chi tiết đơn hàng từ phương thức selectDonHangChiTiet
            List<DonHangChiTiet> donHangChiTietList = selectDonHangChiTiet(Integer.parseInt(maDonHangString));

            // Tiến hành chèn vào bảng HoaDonChiTiet
            String insertHoaDonChiTiet = "INSERT INTO HoaDonChiTiet (MaHoaDon, MaSanPham, SoLuong, DoanhThuSanPham) VALUES (?, ?, ?, ?)";
            PreparedStatement stmtInsertHoaDonChiTiet = conn.prepareStatement(insertHoaDonChiTiet);

            // Duyệt qua các chi tiết đơn hàng và chèn vào HoaDonChiTiet
            for (DonHangChiTiet chiTiet : donHangChiTietList) {
                // Lấy MaSanPham từ cơ sở dữ liệu dựa trên TenSanPham
                String selectSanPham = "SELECT MaSanPham FROM SanPham WHERE TenSanPham = ?";
                PreparedStatement stmtSelectSanPham = conn.prepareStatement(selectSanPham);
                stmtSelectSanPham.setString(1, chiTiet.getTenSanPham());
                ResultSet rsSanPham = stmtSelectSanPham.executeQuery();
                if (rsSanPham.next()) {
                    int maSanPham = rsSanPham.getInt("MaSanPham");

                    // Chèn vào bảng HoaDonChiTiet
                    stmtInsertHoaDonChiTiet.setInt(1, maHoaDon); // Mã hóa đơn
                    stmtInsertHoaDonChiTiet.setInt(2, maSanPham); // Mã sản phẩm
                    stmtInsertHoaDonChiTiet.setInt(3, chiTiet.getSoLuong()); // Số lượng
                    stmtInsertHoaDonChiTiet.setDouble(4, chiTiet.getThanhTien()); // Doanh thu sản phẩm
                    stmtInsertHoaDonChiTiet.executeUpdate();
                }
            }

            // Thông báo thanh toán thành công
            JOptionPane.showMessageDialog(this, "Thanh toán cho đơn hàng " + maDonHangString + " thành công!");
            FormOrder order = new FormOrder();
            order.fillToTableDonHangCho();
            order.fillToTableOrder();
        } catch (Exception e) {
            // Hiển thị lỗi nếu có sự cố trong quá trình xử lý
            JOptionPane.showMessageDialog(this, "Lỗi khi xử lý mã QR: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private List<DonHangChiTiet> selectDonHangChiTiet(int maDonHang) throws SQLException {
        List<DonHangChiTiet> sanPhamList = new ArrayList<>();
        String selectQuery = "SELECT sp.TenSanPham, dhct.SoLuong, dhct.ThanhTien "
                + "FROM DonHangChiTiet dhct "
                + "JOIN SanPham sp ON dhct.MaSanPham = sp.MaSanPham "
                + "WHERE dhct.MaDonHang = ?";
        PreparedStatement stmt = conn.prepareStatement(selectQuery);
        stmt.setInt(1, maDonHang);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String tenSanPham = rs.getString("TenSanPham");
            int soLuong = rs.getInt("SoLuong");
            double thanhTien = rs.getDouble("ThanhTien");
            sanPhamList.add(new DonHangChiTiet(tenSanPham, soLuong, thanhTien));
        }
        return sanPhamList;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "WebcamThread");
        t.setDaemon(true);
        return t;
    }

}
