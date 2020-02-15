package com.htw.model;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseModel implements IModel {
    private Realm mRealm = null;

    public BaseModel() {
        RealmConfiguration myConfig = new RealmConfiguration.Builder()
                .name("myrealm.realm")//保存在内存中
                .build();
        mRealm = Realm.getInstance(myConfig);
    }

    @Override
    public Realm getRealm() {
        return mRealm;
    }

}
