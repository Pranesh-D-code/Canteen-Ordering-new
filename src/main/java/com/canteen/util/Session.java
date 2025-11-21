package com.canteen.util;

/**
 * Simple session holder for small runtime state (username, userId, last QR text/uri).
 * Lightweight — only static getters/setters used by controllers.
 */
public class Session {
    private static Integer userId;
    private static String username;
    private static String lastQrText;
    private static String lastQrUri;

    public static Integer getUserId() {
        return userId;
    }

    public static void setUserId(Integer id) {
        userId = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String u) {
        username = u;
    }

    public static String getLastQrText() {
        return lastQrText;
    }

    public static void setLastQrText(String lastQrText) {
        Session.lastQrText = lastQrText;
    }

    public static String getLastQrUri() {
        return lastQrUri;
    }

    public static void setLastQrUri(String lastQrUri) {
        Session.lastQrUri = lastQrUri;
    }

    public static void clear() {
        userId = null;
        username = null;
        lastQrText = null;
        lastQrUri = null;
    }
}
