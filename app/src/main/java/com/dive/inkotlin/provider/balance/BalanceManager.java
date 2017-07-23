package com.dive.inkotlin.provider.balance;

import android.support.v4.util.ArrayMap;

import com.dive.inkotlin.provider.IBalanceManager;
import com.dive.inkotlin.provider.PreferencesHandler;

import rx.subjects.BehaviorSubject;

/**
 * Created by olena on 30.06.2017.
 */

public class BalanceManager implements IBalanceManager {
    private ArrayMap<Balance, BehaviorSubject<Integer>> subjects;

    public BalanceManager() {
        this.subjects = new ArrayMap<>();
        for (Balance balance :
                Balance.values()) {
            BehaviorSubject<Integer> subj = BehaviorSubject.create();
            subj.onNext(PreferencesHandler.loadIntValue(balance.getKey()));
            subjects.put(balance, subj);
        }
    }

    @Override
    public void update(Balance balance, int value) {
        subjects.get(balance).onNext(value);
    }

    @Override
    public BehaviorSubject<Integer> balance(Balance balance) {
        return subjects.get(balance);
    }

}
