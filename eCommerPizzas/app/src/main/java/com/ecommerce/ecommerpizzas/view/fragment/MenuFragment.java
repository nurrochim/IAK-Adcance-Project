package com.ecommerce.ecommerpizzas.view.fragment;

import com.ecommerce.ecommerpizzas.models.menu.MenuModelImp;

/**
 * Created by Asus on 21/05/2017.
 */

public interface MenuFragment {
    void onSuccess(MenuModelImp mainModelImp);
    void onError(Throwable err);
}
