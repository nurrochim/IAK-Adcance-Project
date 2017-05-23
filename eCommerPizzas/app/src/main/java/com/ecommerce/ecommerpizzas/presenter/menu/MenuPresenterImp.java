package com.ecommerce.ecommerpizzas.presenter.menu;

import android.support.v7.view.menu.MenuView;

import com.ecommerce.ecommerpizzas.models.menu.MenuModel;
import com.ecommerce.ecommerpizzas.models.menu.MenuModelImp;
import com.ecommerce.ecommerpizzas.utils.OkHttpTime;
import com.ecommerce.ecommerpizzas.utils.RxOkhttp;
import com.ecommerce.ecommerpizzas.view.fragment.MenuFragment;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

import okhttp3.Request;
import rx.Observable;

/**
 * ---------------------------------
 * Created by isfaaghyth with <3
 * Everything in here: @isfaaghyth
 * ---------------------------------
 */

public class MenuPresenterImp implements MenuPresenter {

    MenuModel model;
    MenuFragment view;

    public MenuPresenterImp(MenuFragment view) {
        model = new MenuModelImp();
        this.view = view;
    }

    @Override public Observable<MenuModelImp> getResult() {
        Request request = model.build();
        return RxOkhttp.streamStrings(OkHttpTime.client, request).map(
                json -> new Gson().fromJson((json==null||json.isEmpty()?"":json), MenuModelImp.class)
        );
    }

}
