package com.ecommerce.ecommerpizzas.utils;


import com.ecommerce.ecommerpizzas.BuildConfig;

public class URLs {
    private static final String BASE_URL = BuildConfig.URI;
    private static final String DETAIL_URL = "/detail/";
    private static final String MENU_URL = "/api/v1/menu/";
    private static final String MENU_STORAGE = "/storage/";

    public static String getMainURL() {
        return BASE_URL;
    }

    public static String getDetailURL(String id) {
        return BASE_URL + DETAIL_URL + id;
    }

    public static String getMenuUrl() {
        return BASE_URL+MENU_URL;
    }

    public static String getStorageUrl() {
        return BASE_URL+MENU_STORAGE;
    }
}
