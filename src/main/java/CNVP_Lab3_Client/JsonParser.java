package CNVP_Lab3_Client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonParser {
    public static String ConvertToJson(int input) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ServerRequest serverRequest = new ServerRequest();
        serverRequest.setOperation(OperationDispatch.operation(input));
        return objectMapper.writeValueAsString(serverRequest);
    }


    public static String convertFromJson(String received) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ServerRequest serverRequest = new ServerRequest();
        serverRequest = objectMapper.readValue(received, ServerRequest.class);
        String result = serverRequest.getOperation();
        return result;
    }
}

