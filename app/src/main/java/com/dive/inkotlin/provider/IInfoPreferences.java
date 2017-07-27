package com.dive.inkotlin.provider;


import com.dive.inkotlin.provider.info.Info;



public interface IInfoPreferences {

    boolean add(Info info, int value);

    boolean sub(Info info, int value);

    int getSubResult(Info info, int value);

    int getInfo(Info info);

}