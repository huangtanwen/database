package com.htw.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.htw.presenter.IPresenter;
import com.htw.view.IView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity<P extends IPresenter> extends Activity implements IView {
    public CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private Unbinder mUnbinder = null;
    protected P mPresenter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        bindView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindView();
        mUnbinder.unbind();
        clearDisposable();
    }

    /**
     * 添加订阅
     */
    public void addDisposable(Disposable mDisposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(mDisposable);
    }

    /**
     * 取消所有订阅
     */
    public void clearDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void bindView() {
        mPresenter = createPresenter();
        mPresenter.register(this);
    }

    @Override
    public void unbindView() {
        mPresenter.unRegister();
    }

    protected void log(String msg) {
        Log.e("huangtanwen", msg);
    }

    protected abstract int getLayoutId();

    protected abstract P createPresenter();
}
