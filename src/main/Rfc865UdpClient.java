package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

public class Rfc865UdpClient {
    static DatagramSocket socket;
    final static  String ECHO = "Isaiah Wong Hao Jie, SSP4, 172.21.151.249";
    final static String ADDR = "swlab2-c.scse.ntu.edu.sg";
    final static int PORT = 17;

    public static void main(String[] argv) {

        byte []buf = new byte[Rfc865UdpClient.ECHO.length()];

        try {
            InetAddress address = InetAddress.getByName(Rfc865UdpClient.ADDR);
            socket = new DatagramSocket();

            // Send UDP request to server
            DatagramPacket request = new DatagramPacket(
                    Rfc865UdpClient.ECHO.getBytes(StandardCharsets.UTF_8),
                    Rfc865UdpClient.ECHO.length(),
                    address,
                    Rfc865UdpClient.PORT
            );
            socket.send(request);

            // Receive reply
            DatagramPacket reply = new DatagramPacket(buf, buf.length);
            socket.receive(reply);
            System.out.println(new String(reply.getData(), StandardCharsets.UTF_8));

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
