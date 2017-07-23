package com.dive.inkotlin.provider.balance;


import com.dive.inkotlin.provider.IBalanceManager;
import com.dive.inkotlin.provider.IBalancePreferences;
import com.dive.inkotlin.provider.PreferencesHandler;


public class BalancePreferences implements IBalancePreferences {
    private IBalanceManager balanceManager;

    public BalancePreferences(IBalanceManager balanceManager) {
        this.balanceManager = balanceManager;
    }

    @Override
    public boolean add(Balance balance, int value) {
        int result = addValue(balance, value);
        PreferencesHandler.saveIntValue(balance.getKey(), result);
        balanceManager.update(balance, result);
        return true;
    }

    @Override
    public boolean sub(Balance balance, int value) {
        int result = subValue(balance, value);
        if (result < 0)
            return false;
        PreferencesHandler.saveIntValue(balance.getKey(), result);
        balanceManager.update(balance, result);
        return true;
    }

    @Override
    public int getSubResult(Balance balance, int value) {
        return subValue(balance, value);
    }

    @Override
    public int getBalance(Balance balance) {
        return PreferencesHandler.loadIntValue(balance.getKey());
    }

    private int addValue(Balance balance, int value) {
        return PreferencesHandler.loadIntValue(balance.getKey()) + value;
    }

    private int subValue(Balance balance, int value) {
        return PreferencesHandler.loadIntValue(balance.getKey()) - value;
    }

}
