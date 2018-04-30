package ua.training.util.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizeMessage {

    private static String BUNDLE_NAME_MESSAGES = "messages";
    private static String BUNDLE_NAME_EXCEPTIONS = "exceptions";
    private static ResourceBundle resourceBundleMess = ResourceBundle.getBundle(BUNDLE_NAME_MESSAGES, new Locale("en"));
    private static ResourceBundle resourceBundleExcept = ResourceBundle.getBundle(BUNDLE_NAME_EXCEPTIONS, new Locale("en"));

    public static void setLocale(Locale locale) {
        resourceBundleMess = ResourceBundle.getBundle(BUNDLE_NAME_MESSAGES, locale);
        resourceBundleExcept = ResourceBundle.getBundle(BUNDLE_NAME_EXCEPTIONS, locale);
    }

    public static String getMessage(String key) {
        return resourceBundleMess.getString(key);
    }
    public static String getException(String key) {
        return resourceBundleExcept.getString(key);
    }

}
