package CNVP_Lab3_Client;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void displayOptions() {
        System.out.println("To today's date press 1");
        System.out.println("To get get current time press  2");
        System.out.println("To get number of my brigade and surnames press  3");
        System.out.println("To exit press 4");
        System.out.println();
    }

    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int readUserInput() {
        displayOptions();
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            if (!tryParseInt(input)) {
                System.out.println("Input is not correct: input should be int");
                displayOptions();
                continue;
            }
            int inputAsInt = Integer.parseInt(input);
            if (inputAsInt == 1 || inputAsInt == 2 || inputAsInt == 3 || inputAsInt == 4) {
                return inputAsInt;
            } else {
                System.out.println("Input is not correct: input should be 1, 2, 3 or 4");
                displayOptions();
            }
        }
    }

    public static boolean validation(String inputIp) {
        boolean validation = false;
        if (Validation.isValidInetAddress(inputIp)) {
            System.out.print("The IP address " + inputIp + " is valid");
            return true;
        } else {
            System.out.print("The IP address " + inputIp + " isn't valid");
            return false;
        }

    }

    public static InetAddress getInetAddress() throws UnknownHostException {
        System.out.println("Input server's IP address or use default 127.0.0.1");
        Scanner in = new Scanner(System.in);
        while (true) {
            String ipAddressOfServer = in.nextLine();
            boolean validation = validation(ipAddressOfServer);
            if (validation) {
                return InetAddress.getByName(ipAddressOfServer);
            } else {
                System.out.println("Input server's IP address or use default 127.0.0.1");
            }
        }
    }


    public static void main(String[] args) throws SocketException, UnknownHostException {

        int input = 0;
        boolean running = true;
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = getInetAddress();
        System.out.println("");
        while (running)
            try {
                System.out.println("Connected to server");
                Client client = new Client();
                input = readUserInput();
                if (input == 4) {
                    break;
                } else {
                    String dataToSendInJson = JsonParser.ConvertToJson(input);
                    byte[] dataToSend = DataToSend.getDataToSend(dataToSendInJson);
                    client.send(socket, address, dataToSend);
                    System.out.println("package was send");
                    String received = client.receive(socket);
                    System.out.println(received);
                }

            } catch (SocketException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (JsonProcessingException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
    }
}

