package com.ecommerce.ecommerpizzas.view.fragment.implement;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.adapter.ListAdapter;
import com.ecommerce.ecommerpizzas.holder.MenuListHolder;
import com.ecommerce.ecommerpizzas.models.menu.MenuModelImp;
import com.ecommerce.ecommerpizzas.presenter.menu.MenuPresenter;
import com.ecommerce.ecommerpizzas.presenter.menu.MenuPresenterImp;
import com.ecommerce.ecommerpizzas.utils.GridSpacingItemDecoration;
import com.ecommerce.ecommerpizzas.utils.PhotoLoader;
import com.ecommerce.ecommerpizzas.utils.URLs;
import com.ecommerce.ecommerpizzas.view.fragment.BaseFragment;
import com.ecommerce.ecommerpizzas.view.fragment.MenuFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Asus on 21/05/2017.
 */

public class FragmentMenuImp extends BaseFragment implements MenuFragment {
    @BindView(R.id.view_menu)
    RecyclerView listview;

    private ListAdapter adapter;
    private MenuPresenter menuPresenter;

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.menu_pizza, container, false);
        ButterKnife.bind(this, view);
        menuPresenter = new MenuPresenterImp(this);
        subscriber = menuPresenter.getResult()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError);

    }

    @Override
    public void onSuccess(MenuModelImp result) {
        adapter = new ListAdapter<MenuModelImp.Data, MenuListHolder>(
                R.layout.recycle_view_menu,
                MenuListHolder.class,
                MenuModelImp.Data.class,
                result.getData()) {
            @Override
            protected void bindView(MenuListHolder holder, MenuModelImp.Data model, int position) {
                holder.bind(model.getMenuName());
                holder.loadImage(getContext(), URLs.getStorageUrl()+model.getImage1());
                holder.getMenuLayout().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentMenuDetail fragment = new FragmentMenuDetail();
                        fragment.setModel(model);
                        FragmentTransaction fragmentTrans = fragmentManager.beginTransaction();
                        fragmentTrans.addToBackStack(null);
                        fragmentTrans.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                        fragmentTrans.replace(R.id.content_main, fragment);
                        fragmentTrans.commit();
                    }
                });
            }
        };

        listViewDecor();
    }

    @Override
    public void onError(Throwable err) {
        Log.e("Error", err.getMessage());
    }

    public void listViewDecor() {
        listview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        int spanCount = 2; // 3 columns
        int spacing = 10; // 20px
        boolean includeEdge = true;
        listview.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        listview.setAdapter(adapter);
    }




}
