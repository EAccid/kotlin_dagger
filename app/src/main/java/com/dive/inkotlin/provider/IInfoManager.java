package com.dive.inkotlin.provider;

import com.dive.inkotlin.provider.info.Info;

import rx.subjects.BehaviorSubject;



public interface IInfoManager {
    void update(Info info, int value);

    BehaviorSubject<Integer> info(Info info);
}
