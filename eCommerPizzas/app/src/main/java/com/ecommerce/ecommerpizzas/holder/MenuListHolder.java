package com.ecommerce.ecommerpizzas.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecommerce.ecommerpizzas.R;
import com.ecommerce.ecommerpizzas.utils.PhotoLoader;
import com.ecommerce.ecommerpizzas.view.fragment.implement.FragmentMenuDetail;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MenuListHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text_menu_item)
    TextView txtName;
    @BindView(R.id.menu_layout)
    RelativeLayout menuLayout;
    View itemView;
    @BindView(R.id.image_menu_item)
    ImageView imageView;
    protected Subscription mImageLoadSubscription;
    private PhotoLoader mPhotoLoader;

    public MenuListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemView = itemView;
    }

    public RelativeLayout getMenuLayout() {
        return menuLayout;
    }

    public void bind(String menu){
        txtName.setText(menu);
    }

    public ImageView getImageView() {
        return imageView;
    }

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

    public void loadImage(Context context, String url){
        mPhotoLoader = PhotoLoader.getInstance(context);
        mImageLoadSubscription = Observable.just(url)
                .flatMap(loadImageBitmapFromUrl())
                .compose(applySchedulers())
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onNext(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
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
