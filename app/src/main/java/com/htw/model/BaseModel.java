package com.htw.model;

import android.content.Context;

import com.htw.structure.DatebaseInfo;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseModel implements IModel {
    private Realm mRealm = null;

    public BaseModel() {
    }

    @Override
    public Realm getRealm() {
        if (mRealm == null || mRealm.isClosed()) {
            initRealm();
        }
        return mRealm;
    }

    private void initRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(DatebaseInfo.NAME)
                .schemaVersion(DatebaseInfo.VERSION) //版本号
                .deleteRealmIfMigrationNeeded()
                .build();
        mRealm = Realm.getInstance(config);
    }

}
