/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author Admin
 */
import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QRCodeWebSocketServer extends WebSocketServer {

    // Danh sách kết nối WebSocket (các thiết bị đang kết nối)
    private List<WebSocket> clients = new ArrayList<>();

    public QRCodeWebSocketServer(int port) {
//        Integer port = Integer.valueOf(8080);  // Khai báo port dưới dạng Integer
//        super(port);  // Truyền tham số Integer
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        // Khi một kết nối mới được mở, thêm kết nối vào danh sách
        clients.add(conn);
        System.out.println("New connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        // Khi kết nối đóng, xóa kết nối khỏi danh sách
        clients.remove(conn);
        System.out.println("Closed connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        // Khi nhận được tin nhắn từ khách hàng, bạn có thể xử lý tin nhắn này nếu cần
        System.out.println("Message from client: " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("Server started successfully");
    }

    // Phương thức gửi mã QR cho tất cả khách hàng đã kết nối
    public void sendQRCode(String qrCodeData) {
        for (WebSocket client : clients) {
            client.send(qrCodeData);  // Gửi mã QR cho từng client
        }
    }

    public static void main(String[] args) {
        int port = 8887;  // Chọn port cho máy chủ WebSocket
        QRCodeWebSocketServer server = new QRCodeWebSocketServer(port);
        server.start();
        System.out.println("WebSocket server started on port " + port);
    }
}
