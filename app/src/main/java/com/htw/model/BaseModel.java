package com.htw.model;

import io.realm.Realm;

public class BaseModel implements IModel {
    private Callback mCallback = null;

    public BaseModel(Callback callback) {
        mCallback = callback;
    }

    public void creatDatabase() {
        mCallback.onDataBaseCreate(true);
    }

    @Override
    public Realm getRealm() {
        return null;
    }

    public interface Callback {
        void onDataBaseCreate(boolean suc);
    }
}
