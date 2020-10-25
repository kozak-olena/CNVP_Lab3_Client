package CNVP_Lab3_Client;

public class OperationDispatch {
    public static String operation(int input) {
        if (input == 1) {
            return "GetTodayDate";
        } else if (input == 2) {
            return "GetCurrentTime";
        } else if (input == 3) {
            return "GetNumberOfMyBrigadeAndSurnames";
        } else {
            throw new UnsupportedOperationException("operation is not found");
        }
    }
}
