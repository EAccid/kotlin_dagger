package com.dive.inkotlin.presentation.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface BaseView extends MvpView {

    void showToast(String message);

    void showDialog(String fragmentTags);

    void close();

}
