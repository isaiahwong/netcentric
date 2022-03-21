package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class Rfc865UdpServer {
    static DatagramSocket socket;

    public static void main(String[] args) {
        byte buf[] = new byte[100];
        try {
            InetAddress address = InetAddress.getByName("localhost");
            int port = 3000;
            socket = new DatagramSocket(port, address);
            while (true) {
                //
                // 2. Listen for UDP request from client
                //
                DatagramPacket request = new DatagramPacket(buf, buf.length);
                socket.receive(request);
                System.out.println(new String(request.getData(), StandardCharsets.UTF_8));

                //
                // 3. Send UDP reply to client
                //
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
                socket.send(reply);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
