package net.luvina.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;

public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T readValue(BufferedReader data, Class<T> tClass) {
        String line = null;
        StringBuilder dataJson = new StringBuilder();
        T t = null;
        try {
            while ((line = data.readLine()) != null) {
                dataJson.append(line);
            }
            t = objectMapper.readValue(dataJson.toString(), tClass);
        } catch (IOException exception) {
            System.out.println("JsonUtil:readValue: " + exception.getMessage());
        }
        return t;
    }


    public static <T> void writeValue(OutputStream outputStream, T t) {
        try {
            objectMapper.writeValue(outputStream, t);
        } catch (IOException exception) {
            System.out.println("JsonUtil:writeValue: " + exception.getMessage());
        }
    }
}
