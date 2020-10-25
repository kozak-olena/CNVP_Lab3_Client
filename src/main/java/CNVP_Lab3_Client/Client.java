package CNVP_Lab3_Client;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void send(DatagramSocket socket, InetAddress address, byte[] dataToSend) throws IOException {
        DatagramPacket sendPacket = new DatagramPacket(dataToSend, dataToSend.length, address, 5548);
        socket.send(sendPacket);
    }

    public static String receive(DatagramSocket socket) throws IOException {
        byte[] dataToReceive = new byte[256];
        DatagramPacket receivePacket = new DatagramPacket(dataToReceive, dataToReceive.length);
        socket.receive(receivePacket);
        String received = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);
        String stringFromJson = JsonParser.convertFromJson(received);

        return stringFromJson;
    }
}
