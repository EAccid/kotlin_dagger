package com.dive.inkotlin.presentation.base;

import com.dive.inkotlin.presentation.NetworkAvailableness;
import com.dive.inkotlin.presentation.UserDataUtil;

import rx.Subscription;


public interface IBasePresenter extends NetworkAvailableness, UserDataUtil {
    void subscribe(Subscription subscription);
}
