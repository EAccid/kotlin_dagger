package com.dive.inkotlin.presentation;


import com.dive.inkotlin.provider.balance.Balance;

/**
 * Created by olena on 02.04.2017.
 */

public interface IBalanceKeeper {

    void updateBalance(Balance type, int value);

    Balance[] keepTypes();

}
