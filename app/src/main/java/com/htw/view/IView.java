package com.htw.view;

public interface IView<T> {

    void bindView();

    void unbindView();

    void showTip(T msg);
}
