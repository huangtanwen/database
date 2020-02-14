package com.htw.presenter;

import android.util.Log;

import com.htw.model.IModel;
import com.htw.view.IView;

import java.lang.ref.WeakReference;

public class BasePresenter<T> implements IPresenter {
    protected WeakReference mView;
    protected IModel mModel;

    @Override
    public void register(IView view) {
        mView = new WeakReference(view);
    }

    @Override
    public void unRegister() {
        mView.clear();
    }

    public void createDataBase() {
    }

    public void addData(T data) {
    }

    public void deleteData(T data) {

    }
}
