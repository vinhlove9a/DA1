/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.TrangChu;

import view.BanHang.FormOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import view.SanPham.FormQuanLySanPham;
import view.ThongKe.FormThongKe;
import view.ban.FormDatBan;
import utility.DBConnect;
import view.login.FormDangNhap;
import view.voucher.QuanLyVoucher;

/**
 *
 * @author Admin
 */
public class FormTrangChu extends javax.swing.JFrame {

    /**
     * Creates new form FormChucNang
     */
    public FormTrangChu() {
        initComponents();
        // showPieChart();
        setLocationRelativeTo(null);

//        showLineChart();
        lblRole.setText(FormDangNhap.userRole);
        cboMonth.removeAllItems();
        cboMonth.addItem("1 - Tháng 1");
        cboMonth.addItem("2 - Tháng 2");
        cboMonth.addItem("3 - Tháng 3");
        cboMonth.addItem("4 - Tháng 4");
        cboMonth.addItem("5 - Tháng 5");
        cboMonth.addItem("6 - Tháng 6");
        cboMonth.addItem("7 - Tháng 7");
        cboMonth.addItem("8 - Tháng 8");
        cboMonth.addItem("9 - Tháng 9");
        cboMonth.addItem("10 - Tháng 10");
        cboMonth.addItem("11 - Tháng 11");
        cboMonth.addItem("12 - Tháng 12");
        // Thêm sự kiện cho cboMonth
        //cboMonth.addActionListener(e -> showHistogram((String) cboMonth.getSelectedItem()));

// Gọi hàm showHistogram ngay khi khởi tạo (cho tháng mặc định)
        //showHistogram(cboMonth.getSelectedItem().toString());
        cboMonth.addActionListener(e -> {
            String selectedMonth = (String) cboMonth.getSelectedItem();
            updateSummaryLabels(selectedMonth);
            showLineChart(selectedMonth);
        });
        cboMonth.setSelectedIndex(0);
        showLineChart("1 - Tháng 1");

    }

    public void showLineChart(String selectedMonth) {
        double[] dailyRevenues = fetchRevenueDataFromDatabase(selectedMonth);

        // Create a dataset for the line chart
        XYSeries series = new XYSeries("Thu nhập theo ngày");
        for (int i = 0; i < dailyRevenues.length; i++) {
            series.add(i + 1, dailyRevenues[i]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        // Create the line chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Thống kê thu nhập theo ngày trong tháng " + selectedMonth.split(" - ")[0],
                "Ngày trong tháng",
                "Thu nhập (triệu đồng)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        // Customize the plot appearance
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE); // Set plot background to white
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY); // Set domain gridline color to light gray
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);  // Set range gridline color to light gray

        // Set axis properties
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setRange(1, dailyRevenues.length);  // Ensure the domain covers all days
        domainAxis.setTickUnit(new NumberTickUnit(1)); // Tick interval of 1 for day labels

        // Customize the line renderer
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false); // Lines only, no shapes
        renderer.setSeriesPaint(0, Color.BLACK); // Set line color to black
        plot.setRenderer(renderer);

        // Create and configure the chart panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // Update the display panel
        panelLineChart3.removeAll();
        panelLineChart3.add(chartPanel, BorderLayout.CENTER);
        panelLineChart3.validate();
        panelLineChart3.repaint();
    }

    private double[] fetchRevenueDataFromDatabase(String selectedMonth) {
        int month = Integer.parseInt(selectedMonth.split(" - ")[0]);
        double[] dailyRevenues = new double[30];  // Giả sử dữ liệu cho 30 ngày

        // Kết nối và truy vấn dữ liệu từ cơ sở dữ liệu
        try ( Connection con = DBConnect.getConnection()) {
            String sql = "SELECT DAY(NgayLap) AS Ngay, SUM(TongTien) AS TongThuNhap "
                    + "FROM HoaDon "
                    + "WHERE MONTH(NgayLap) = ? "
                    + "GROUP BY DAY(NgayLap) "
                    + "ORDER BY Ngay ASC";

            try ( PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, month);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int day = rs.getInt("Ngay");
                    if (day > 0 && day <= 30) {  // Kiểm tra ngày trong phạm vi hợp lệ
                        double totalRevenue = rs.getDouble("TongThuNhap");
                        dailyRevenues[day - 1] = totalRevenue;
                    } else {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // In ra giá trị của mảng dailyRevenues để kiểm tra
        for (int i = 0; i < dailyRevenues.length; i++) {
        }

        return dailyRevenues;
    }

    public Object[] fetchInvoiceSummary(String selectedMonth) {
        Object[] summaryData = new Object[2]; // Chứa tổng hóa đơn và tổng thu nhập
        int month = Integer.parseInt(selectedMonth.split(" - ")[0]);

        try ( Connection con = DBConnect.getConnection()) {
            String sql = "SELECT COUNT(*) AS TotalInvoices, SUM(TongTien) AS TotalIncome "
                    + "FROM HoaDon "
                    + "WHERE MONTH(NgayLap) = ?";
            try ( PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, month); // Gán tháng vào câu truy vấn
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    summaryData[0] = rs.getInt("TotalInvoices"); // Tổng hóa đơn
                    summaryData[1] = rs.getDouble("TotalIncome"); // Tổng thu nhập
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return summaryData;
    }

    public void updateSummaryLabels(String selectedMonth) {
        // Lấy dữ liệu tổng hóa đơn và thu nhập
        Object[] summaryData = fetchInvoiceSummary(selectedMonth);
        int totalInvoices = (int) summaryData[0];
        double totalIncome = (double) summaryData[1];

        // Cập nhật JLabel
        lblSoHoaDon.setText("" + totalInvoices);
        lblTongThuNhap.setText("" + String.format("%.2f", totalIncome));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        panelLineChart3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblTongThuNhap = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblSoHoaDon = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboMonth = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(233, 233, 252));

        jPanel3.setBackground(new java.awt.Color(255, 153, 51));

        jLabel2.setFont(new java.awt.Font("Dialog", 3, 35)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Three Restaurant FPOLY");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.two/home50px.png"))); // NOI18N
        jLabel4.setText("jLabel4");

        lblRole.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblRole.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/male_user_50px.png"))); // NOI18N
        lblRole.setText("admin");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRole)
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblRole)
                        .addGap(9, 9, 9)))
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(34, 34, 34))
        );

        jButton2.setBackground(new java.awt.Color(233, 233, 252));
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/food_25px.png"))); // NOI18N
        jButton2.setText("Quản lý sản phẩm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(233, 233, 252));
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shopping_cart_24px.png"))); // NOI18N
        jButton3.setText("Bán hàng");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(233, 233, 252));
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/table_25px.png"))); // NOI18N
        jButton4.setText("Đặt bàn");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(233, 233, 252));
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user_groups_25px.png"))); // NOI18N
        jButton5.setText("Quản lý nhân viên");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(233, 233, 252));
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/account_24px.png"))); // NOI18N
        jButton6.setText("Thống kê");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(233, 233, 252));
        jButton7.setForeground(new java.awt.Color(0, 0, 0));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.two/logout-line-icon (2).png"))); // NOI18N
        jButton7.setText("Đăng xuất");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(233, 233, 252));
        jButton8.setForeground(new java.awt.Color(0, 0, 0));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home_24px.png"))); // NOI18N
        jButton8.setText("Trang chủ");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        panelLineChart3.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(233, 233, 252));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 111, 111)));

        lblTongThuNhap.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblTongThuNhap.setForeground(new java.awt.Color(0, 0, 0));

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tổng thu nhập");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.two/money62px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel15)
                .addContainerGap(113, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTongThuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTongThuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(24, 24, 24))
        );

        jPanel6.setBackground(new java.awt.Color(233, 233, 252));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 111, 111)));

        lblSoHoaDon.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblSoHoaDon.setForeground(new java.awt.Color(0, 0, 0));
        lblSoHoaDon.setText("2");

        jLabel18.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Số lượng hóa đơn");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/money_50px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel18)
                .addContainerGap(91, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(24, 24, 24))
        );

        cboMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton9.setBackground(new java.awt.Color(233, 233, 252));
        jButton9.setForeground(new java.awt.Color(0, 0, 0));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/grid_24px.png"))); // NOI18N
        jButton9.setText("Voucher");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLineChart3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMonth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(jButton7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(cboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelLineChart3, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(70, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel5, jPanel6});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        FormTrangChu cnql = new FormTrangChu();
        cnql.show();
        dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:       
        if (FormDangNhap.userRole != null && FormDangNhap.userRole.equals("Admin")) {
            // Nếu vai trò là Admin, cho phép mở FormQuanLySanPham
            FormQuanLySanPham qlsp = new FormQuanLySanPham();
            qlsp.setVisible(true);  // Thay show() bằng setVisible(true) vì show() đã bị deprecated
            dispose();
        } else {
            // Nếu vai trò không phải là Admin, hiển thị thông báo không có quyền
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        FormOrder bh = new FormOrder();
        bh.show();
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        FormDatBan db = new FormDatBan();
        db.show();
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (FormDangNhap.userRole != null && FormDangNhap.userRole.equals("Admin")) {
            // Nếu vai trò là Admin, cho phép mở FormQuanLyNhanVien
            view.QuanLyNhanVien.FormQuanLyNhanVien qlnv = new view.QuanLyNhanVien.FormQuanLyNhanVien();
            qlnv.setVisible(true);  // Sử dụng setVisible(true) thay vì show() vì show() đã bị deprecated
            dispose();
        } else {
            // Nếu vai trò không phải là Admin, hiển thị thông báo không có quyền
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này!");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        // Kiểm tra vai trò người dùng trước khi cho phép mở form
        if (FormDangNhap.userRole != null && FormDangNhap.userRole.equals("Admin")) {
            // Nếu vai trò là Admin, cho phép mở FormThongKe
            FormThongKe tk = new FormThongKe();
            tk.setVisible(true);  // Sử dụng setVisible(true) thay vì show() vì show() đã bị deprecated
            dispose();
        } else {
            // Nếu vai trò không phải là Admin, hiển thị thông báo không có quyền
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này!");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        FormDangNhap dn = new FormDangNhap();
        dn.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        QuanLyVoucher voucher = new QuanLyVoucher();
        voucher.show();
        dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(FormTrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTrangChu().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboMonth;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblSoHoaDon;
    private javax.swing.JLabel lblTongThuNhap;
    private javax.swing.JPanel panelLineChart3;
    // End of variables declaration//GEN-END:variables
}
