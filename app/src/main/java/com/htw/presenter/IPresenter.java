package com.htw.presenter;

import com.htw.view.IView;

public interface IPresenter<V extends IView> {

    void register(V view);

    void unRegister();
}
