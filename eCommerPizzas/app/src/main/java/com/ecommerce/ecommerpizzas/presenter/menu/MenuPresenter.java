package com.ecommerce.ecommerpizzas.presenter.menu;

import com.ecommerce.ecommerpizzas.models.menu.MenuModelImp;

import rx.Observable;

/**
 * ---------------------------------
 * Created by isfaaghyth with <3
 * Everything in here: @isfaaghyth
 * ---------------------------------
 */

public interface MenuPresenter {
    Observable<MenuModelImp> getResult();
}
