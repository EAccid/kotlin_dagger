package com.dive.inkotlin.presentation.base.impl;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.dive.inkotlin.App;
import com.dive.inkotlin.presentation.IInfoKeeper;
import com.dive.inkotlin.presentation.base.BaseView;
import com.dive.inkotlin.presentation.base.IBasePresenter;
import com.dive.inkotlin.provider.IInfoManager;
import com.dive.inkotlin.provider.info.Info;
import com.dive.inkotlin.semantics.Network;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.Lazy;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


@InjectViewState
public class BasePresenter extends MvpPresenter<BaseView> implements IBasePresenter {
    private static int count = 0;
    private final String TAG = getClass().getName();
    private CompositeSubscription compositeSubscription;

    @Inject
    Lazy<IInfoManager> infoManager;

    public BasePresenter() {
        this.compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void attachView(BaseView view) {
        super.attachView(view);
        Log.d(TAG, getViewState().getClass().getName() + " has been attached: " + ++count);
        if (view instanceof IInfoKeeper)
            subscribeOnInfo((IInfoKeeper) view);
    }

    @Override
    public void destroyView(BaseView view) {
        if (view != null)
            Log.d(TAG, view.getClass().getName() + " has been detached: " + --count);
        if (view instanceof IInfoKeeper)
            unsubscribe((IInfoKeeper) view);
        if (compositeSubscription != null)
            compositeSubscription.clear();
        compositeSubscription = null;
        super.destroyView(view);
    }

    @Override
    public boolean isNetworkAvailable(boolean showDialog) {
        boolean isAv = new Network().isNetworkAvailable();
        if (!isAv && showDialog)
            getViewState().showToast("dialog is showing");
        return isAv;
    }

    @Override
    public boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private Map<String, WeakReference<Subscription>> subscriptions;

    @Override
    public void subscribe(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    private void subscribeOnInfo(IInfoKeeper infoKeeper) {
        App.getComponent().inject(this);
        unsubscribe(infoKeeper);
        subscriptions = new HashMap<>(infoKeeper.keepTypes().length);
        for (Info type :
                infoKeeper.keepTypes()) {
            Subscription sub = infoManager.get().info(type)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            value -> {
                                infoKeeper.updateInfo(type, value);
                                Log.d(TAG, getViewState().getClass().getName() + "Info has been updated on " + getViewState().getClass().getName());
                            }, Throwable::printStackTrace);
            subscribe(sub);
            subscriptions.put(type.getKey(), new WeakReference<>(sub));
        }
    }

    private void unsubscribe(IInfoKeeper infoKeeper) {
        if (subscriptions == null)
            return;
        for (Info type : infoKeeper.keepTypes())
            if (subscriptions.get(type.getKey()).get() != null) {
                subscriptions.get(type.getKey()).get().unsubscribe();
                subscriptions.get(type.getKey()).clear();
            }
        subscriptions.clear();
    }

}
