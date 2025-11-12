package com.canteen.app;

import com.canteen.model.MenuItem;
import com.canteen.model.User;

import java.util.ArrayList;
import java.util.List;

public class AppSession {
    private static User user;
    private static List<MenuItem> cart = new ArrayList<>();
    private static String lastQrText;
    private static String lastQrUri;

    public static void setUser(User u){ user = u; }
    public static User getUser(){ return user; }
    public static List<MenuItem> getCart(){ return cart; }
    public static void clearCart(){ cart.clear(); }
    public static void addToCart(MenuItem m){ cart.add(m); }
    public static void setLastQrText(String s){ lastQrText = s; }
    public static String getLastQrText(){ return lastQrText; }
    public static void setLastQrUri(String s){ lastQrUri = s; }
    public static String getLastQrUri(){ return lastQrUri; }
}
