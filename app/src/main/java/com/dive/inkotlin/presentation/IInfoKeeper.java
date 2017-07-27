package com.dive.inkotlin.presentation;


import com.dive.inkotlin.provider.info.Info;

/**
 * Created by olena on 02.04.2017.
 */

public interface IInfoKeeper {

    void updateInfo(Info type, int value);

    Info[] keepTypes();

}
