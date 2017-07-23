package com.dive.inkotlin.provider;


import com.dive.inkotlin.provider.balance.Balance;



public interface IBalancePreferences {

    boolean add(Balance balance, int value);

    boolean sub(Balance balance, int value);

    int getSubResult(Balance balance, int value);

    int getBalance(Balance balance);

}