package com.dive.inkotlin.provider;

import com.dive.inkotlin.provider.balance.Balance;

import rx.subjects.BehaviorSubject;



public interface IBalanceManager {
    void update(Balance balance, int value);

    BehaviorSubject<Integer> balance(Balance balance);
}
