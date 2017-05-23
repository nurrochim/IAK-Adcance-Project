package com.ecommerce.ecommerpizzas.models.menu;

import com.ecommerce.ecommerpizzas.utils.URLs;

import java.util.List;

import okhttp3.Request;

/**
 * Created by Asus on 21/05/2017.
 */

public class MenuModelImp implements MenuModel {
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data{
        int id;
        String menuName;
        String menuDetail;
        String menuHarga;
        String menuSize;
        String image1;
        String image2;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }

        public String getMenuDetail() {
            return menuDetail;
        }

        public void setMenuDetail(String menuDetail) {
            this.menuDetail = menuDetail;
        }

        public String getMenuHarga() {
            return menuHarga;
        }

        public void setMenuHarga(String menuHarga) {
            this.menuHarga = menuHarga;
        }

        public String getMenuSize() {
            return menuSize;
        }

        public void setMenuSize(String menuSize) {
            this.menuSize = menuSize;
        }

        public String getImage1() {
            return image1;
        }

        public void setImage1(String image1) {
            this.image1 = image1;
        }

        public String getImage2() {
            return image2;
        }

        public void setImage2(String image2) {
            this.image2 = image2;
        }
    }

    @Override
    public Request build() {
        return new Request.Builder()
                .get()
                .url(URLs.getMenuUrl())
                .build();
    }
}
