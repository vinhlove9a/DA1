/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author Admin
 */
import java.net.*;

public class UDPServer {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Khởi tạo DatagramSocket để lắng nghe yêu cầu từ điện thoại (máy chủ UDP)
            socket = new DatagramSocket(9876); // Cổng UDP
            byte[] receiveData = new byte[1024];

            System.out.println("Server is listening on port 9876...");
            while (true) {
                // Nhận dữ liệu từ điện thoại
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                // Chuyển đổi dữ liệu nhận được thành chuỗi
                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received order code: " + receivedData);

                // Xử lý mã đơn hàng
                processPayment(receivedData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }

    private static void processPayment(String orderCode) {
        // Xử lý thanh toán cho đơn hàng dựa trên orderCode
        System.out.println("Processing payment for order: " + orderCode);
    }
}
