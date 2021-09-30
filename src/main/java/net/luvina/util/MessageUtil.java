package net.luvina.util;

import java.util.ResourceBundle;

public class MessageUtil {
    private static ResourceBundle errorMessageResource;

    static {
        errorMessageResource = ResourceBundle.getBundle("error_massage");
    }

    public static String getErrorMessage(String key) {
        return key == null ? null : errorMessageResource.getString(key);
    }
}
