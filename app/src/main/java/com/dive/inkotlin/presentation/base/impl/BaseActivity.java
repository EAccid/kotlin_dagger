package com.dive.inkotlin.presentation.base.impl;

import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.dive.inkotlin.di.HasActivitySubcomponentBuilders;
import com.dive.inkotlin.di.components.ActivityComponent;
import com.dive.inkotlin.presentation.CustomDialog;
import com.dive.inkotlin.presentation.base.BaseView;

import butterknife.ButterKnife;
import xdroid.toaster.Toaster;

public abstract class BaseActivity extends MvpAppCompatActivity implements BaseView {

    ActivityComponent component;

    @InjectPresenter(type = PresenterType.GLOBAL)
    BasePresenter basePresenter;

    //    @Inject
    CustomDialog dialog;

    public BaseActivity() {
//        component = .activityModule(new ActivityModule(this)).build();
//        component.inject(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(getActivityContentView());
        ButterKnife.bind(this);
        setupActivityComponent();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        component = null;
        super.onDestroy();
    }

    @Override
    public void showDialog(String fragmentTags) {
//        dialog.showDialog(fragmentTags);
    }

    @Override
    public void showToast(String message) {
        Toaster.toast(message);
    }

    @Override
    public void close() {
        finish();
    }

//    @LayoutRes
//    protected abstract int getActivityContentView();

    protected void setVisibility(View view, boolean isVisible) {
        int visibility = isVisible ? View.VISIBLE : View.GONE;
        view.setVisibility(visibility);
    }

    protected void setupActivityComponent() {
//        injectMembers(App.get(this));
    }

    protected abstract void injectMembers(HasActivitySubcomponentBuilders hasActivitySubcomponentBuilders);


}
