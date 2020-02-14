package com.htw.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.htw.presenter.BasePresenter;
import com.htw.presenter.IPresenter;
import com.htw.structure.UserInfo;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_show_tip)
    protected TextView mShowTipView;
    @BindView(R.id.btn_add)
    protected Button mAddButton;
    @BindView(R.id.btn_delete)
    protected Button mDeleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addClickEvent();
    }

    private void addClickEvent() {
        addDisposable(RxView.clicks(mAddButton)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(o -> {
                    log("添加按键被点击");
                    ((BasePresenter) mPresenter).addData(new UserInfo.Builder()
                            .id("1")
                            .name("张三")
                            .gender("男")
                            .age(18)
                            .build());
                }));
        addDisposable(RxView.clicks(mDeleteButton)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(o -> {
                    log("删除按键被点击");
                    ((BasePresenter) mPresenter).deleteData("123");
                }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected IPresenter createPresenter() {
        return new BasePresenter();
    }
}
