package com.dive.inkotlin.presentation.base.impl;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.dive.inkotlin.App;
import com.dive.inkotlin.presentation.IBalanceKeeper;
import com.dive.inkotlin.presentation.base.BaseView;
import com.dive.inkotlin.presentation.base.IBasePresenter;
import com.dive.inkotlin.provider.IBalanceManager;
import com.dive.inkotlin.provider.balance.Balance;
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
    Lazy<IBalanceManager> balanceManager;

    public BasePresenter() {
        this.compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void attachView(BaseView view) {
        super.attachView(view);
        Log.i(TAG, getViewState().getClass().getName() + " has been attached: " + ++count);
        if (view instanceof IBalanceKeeper)
            subscribeOnBalance((IBalanceKeeper) view);
    }

    @Override
    public void destroyView(BaseView view) {
        if (view != null)
            Log.i(TAG, view.getClass().getName() + " has been detached: " + --count);
        if (view instanceof IBalanceKeeper)
            unsubscribe((IBalanceKeeper) view);
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

    private void subscribeOnBalance(IBalanceKeeper balanceKeeper) {
        App.getComponent().inject(this);
        unsubscribe(balanceKeeper);
        subscriptions = new HashMap<>(balanceKeeper.keepTypes().length);
        for (Balance type :
                balanceKeeper.keepTypes()) {
            Subscription sub = balanceManager.get().balance(type)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            value -> {
                                balanceKeeper.updateBalance(type, value);
                                Log.i(TAG, getViewState().getClass().getName() + "Balance has been updated on " + getViewState().getClass().getName());
                            }, Throwable::printStackTrace);
            subscribe(sub);
            subscriptions.put(type.getKey(), new WeakReference<>(sub));
        }
    }

    private void unsubscribe(IBalanceKeeper balanceKeeper) {
        if (subscriptions == null)
            return;
        for (Balance type : balanceKeeper.keepTypes())
            if (subscriptions.get(type.getKey()).get() != null) {
                subscriptions.get(type.getKey()).get().unsubscribe();
                subscriptions.get(type.getKey()).clear();
            }
        subscriptions.clear();
    }

}
