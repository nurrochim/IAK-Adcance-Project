package com.ecommerce.ecommerpizzas.view.fragment.implement;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.models.menu.MenuModelImp;
import com.ecommerce.ecommerpizzas.utils.PhotoLoader;
import com.ecommerce.ecommerpizzas.utils.URLs;
import com.ecommerce.ecommerpizzas.view.fragment.BaseFragment;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Asus on 25/03/2017.
 */

public class FragmentMenuDetail extends BaseFragment{
    @BindView(R.id.add_to_cart)
    Button addToCart;
    @BindView(R.id.text_menu_name)
    TextView textMenu;
    @BindView(R.id.text_harga)
    TextView textHarga;
    @BindView(R.id.text_detail)
    TextView textDetail;
    @BindView(R.id.layout_menu_detail)
    RelativeLayout imageMenuDetail;
    protected Subscription mImageLoadSubscription;

    private String idMenu;
    MenuModelImp.Data model;

    @Override
    public void initView() {
        view = inflater.inflate(R.layout.fragment_menu_detail, container, false);
        ButterKnife.bind(this, view);
        mPhotoLoader = PhotoLoader.getInstance(getContext());
        showDetail();

    }

    public void showDetail(){
        textMenu.setText(model.getMenuName());
        // format harga
        Double number = Double.valueOf(model.getMenuHarga());

        DecimalFormat dec = new DecimalFormat("#,###.##");
        dec.setMinimumFractionDigits(2);
        String harga = dec.format(number);

        textHarga.setText("Rp. "+harga);
        textDetail.setText("Size "+model.getMenuSize()+"\n"+model.getMenuDetail());
        loadImage(URLs.getStorageUrl()+model.getImage2());
    }

    public MenuModelImp.Data getModel() {
        return model;
    }

    public void setModel(MenuModelImp.Data model) {
        this.model = model;
    }

    private PhotoLoader mPhotoLoader;
    @NonNull
    private Func1<String, Observable<? extends Bitmap>> loadImageBitmapFromUrl() {
        return imageUrl -> mPhotoLoader.load(imageUrl);
    }

    /**
     * Simple transformer that will take an observable and
     * 1. Schedule it on a worker thread
     * 2. Observe on main thread
     */
    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void loadImage(String url){

        mImageLoadSubscription = Observable.just(url)
                .flatMap(loadImageBitmapFromUrl())
                .compose(applySchedulers())
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onNext(Bitmap bitmap) {
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                        imageMenuDetail.setBackground(bitmapDrawable);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error ",e.getMessage());
                        mImageLoadSubscription.unsubscribe();
                    }

                    @Override
                    public void onCompleted() {
                        mImageLoadSubscription.unsubscribe();
                    }
                });
    }
}
