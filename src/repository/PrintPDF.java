package repository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import com.itextpdf.text.Image;

public class PrintPDF {

    // Phương thức xuất JPanel ra file PDF
    public void exportJFrameToPDF(JPanel panel, String filePath) {
        try {
            // Tạo tài liệu PDF
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath + ".pdf"));
            document.open();

            // Tạo ảnh chụp của JPanel
            BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            panel.printAll(g2d);  // Vẽ toàn bộ nội dung của JPanel lên ảnh
            g2d.dispose();

            // Chuyển đổi ảnh thành byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();

            // Thêm ảnh vào tài liệu PDF
            Image pdfImage = Image.getInstance(imageInByte);
            pdfImage.scaleToFit(document.getPageSize().getWidth(), document.getPageSize().getHeight());  // Điều chỉnh ảnh để vừa với trang

            document.add(pdfImage);

            // Đóng tài liệu PDF
            document.close();
            writer.close();

            JOptionPane.showMessageDialog(null, "JPanel đã được xuất ra file PDF thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi khi xuất file PDF!");
        }
    }

//    public static void main(String[] args) {
//        // Tạo JFrame mẫu
//        JFrame frame = new JFrame("Export JPanel to PDF");
//        frame.setSize(500, 400);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Tạo JPanel mẫu để xuất ra PDF
//        JPanel jplHoaDon = new JPanel();
//        jplHoaDon.setSize(500, 400);
//        jplHoaDon.setBackground(Color.CYAN);
//        JLabel label = new JLabel("Hóa Đơn mẫu");
//        jplHoaDon.add(label);
//
//        // Thêm JPanel vào JFrame
//        frame.add(jplHoaDon);
//        frame.setVisible(true);
//
//        // Xuất JPanel ra PDF
//        PrintPDF pdf = new PrintPDF();
//        pdf.exportJFrameToPDF(jplHoaDon, "D:/DuAn1/Vinh_ProJect/HoaDon_\" + maHoaDon + \".pdf");
//    }
}
