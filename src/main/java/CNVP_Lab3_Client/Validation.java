package CNVP_Lab3_Client;

import java.util.Arrays;

public class Validation {
    public static boolean isValidInetAddress(String ipAddress) {
        String[] groups = ipAddress.split("\\.");

        if (groups.length != 4)
            return false;

        try {
            return Arrays.stream(groups)
                    .filter(s -> s.length() >= 1)
                    .map(Integer::parseInt)
                    .filter(i -> (i >= 0 && i <= 255))
                    .count() == 4;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void Validate(String inputedIp) {
        if (isValidInetAddress(inputedIp)) {
            System.out.print("The IP address " + inputedIp + " is valid");
        } else {
            System.out.print("The IP address " + inputedIp + " isn't valid");
        }
    }


}
