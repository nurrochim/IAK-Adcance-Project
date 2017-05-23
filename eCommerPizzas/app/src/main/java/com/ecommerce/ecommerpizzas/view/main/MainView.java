package com.ecommerce.ecommerpizzas.view.main;

import com.ecommerce.ecommerpizzas.models.menu.MenuModelImp;

/**
 * ---------------------------------
 * Created by isfaaghyth with <3
 * Everything in here: @isfaaghyth
 * ---------------------------------
 */

public interface MainView {
    void onSuccess(MenuModelImp mainModelImp);
    void onError(Throwable err);


}
