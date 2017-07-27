package com.dive.inkotlin.provider.info;


import com.dive.inkotlin.provider.IInfoManager;
import com.dive.inkotlin.provider.IInfoPreferences;
import com.dive.inkotlin.provider.PreferencesProxy;


public class InfoPreferences implements IInfoPreferences {
    private IInfoManager infoManager;

    public InfoPreferences(IInfoManager infoManager) {
        this.infoManager = infoManager;
    }

    @Override
    public boolean add(Info info, int value) {
        int result = addValue(info, value);
        PreferencesProxy.saveIntValue(info.getKey(), result);
        infoManager.update(info, result);
        return true;
    }

    @Override
    public boolean sub(Info info, int value) {
        int result = subValue(info, value);
        if (result < 0)
            return false;
        PreferencesProxy.saveIntValue(info.getKey(), result);
        infoManager.update(info, result);
        return true;
    }

    @Override
    public int getSubResult(Info info, int value) {
        return subValue(info, value);
    }

    @Override
    public int getInfo(Info info) {
        return PreferencesProxy.loadIntValue(info.getKey());
    }

    private int addValue(Info info, int value) {
        return PreferencesProxy.loadIntValue(info.getKey()) + value;
    }

    private int subValue(Info info, int value) {
        return PreferencesProxy.loadIntValue(info.getKey()) - value;
    }

}
