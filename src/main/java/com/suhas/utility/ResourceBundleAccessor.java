package com.suhas.utility;

import java.util.ResourceBundle;

public class ResourceBundleAccessor {

    private static String validationBundle = "validationmessages";

    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle(validationBundle);
    }

    public static String getMessage(String key) {
        return getResourceBundle().getString(key);
    }

}

