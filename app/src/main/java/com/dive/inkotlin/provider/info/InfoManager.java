package com.dive.inkotlin.provider.info;

import android.support.v4.util.ArrayMap;

import com.dive.inkotlin.provider.IInfoManager;
import com.dive.inkotlin.provider.PreferencesProxy;

import rx.subjects.BehaviorSubject;

/**
 * Created by olena on 30.06.2017.
 */

public class InfoManager implements IInfoManager {
    private ArrayMap<Info, BehaviorSubject<Integer>> subjects;

    public InfoManager() {
        this.subjects = new ArrayMap<>();
        for (Info info :
                Info.values()) {
            BehaviorSubject<Integer> subj = BehaviorSubject.create();
            subj.onNext(PreferencesProxy.loadIntValue(info.getKey()));
            subjects.put(info, subj);
        }
    }

    @Override
    public void update(Info info, int value) {
        subjects.get(info).onNext(value);
    }

    @Override
    public BehaviorSubject<Integer> info(Info info) {
        return subjects.get(info);
    }

}
