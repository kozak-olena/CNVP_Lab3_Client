package CNVP_Lab3_Client;


import java.nio.charset.StandardCharsets;

public class DataToSend {
    public static byte[] getDataToSend(String message) {
        byte[] dataToSend = message.getBytes(StandardCharsets.UTF_8);
        return dataToSend;
    }
}
