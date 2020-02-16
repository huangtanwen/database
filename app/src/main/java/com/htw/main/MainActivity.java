package com.htw.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.htw.presenter.BasePresenter;
import com.htw.presenter.IPresenter;
import com.htw.structure.UserInfo;
import com.htw.util.Util;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.Random;
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
    @BindView(R.id.btn_query)
    protected Button mQueryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addClickEvent();
    }

    private void addClickEvent() {
        addDisposable(RxView.clicks(mAddButton)
                .subscribe(o -> {
                    log("添加按键被点击");
                    ((BasePresenter) mPresenter).addData(new UserInfo.Builder()
                            .id("ID_" + new Random().nextInt())
                            .name(Util.getRandomJianHan())
                            .gender(new Random().nextInt(1) == 0 ? "男" : "女")
                            .age(new Random().nextInt(99) + 1)
                            .build());
                }));
        addDisposable(RxView.clicks(mDeleteButton)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(o -> {
                    log("删除按键被点击");
                    ((BasePresenter) mPresenter).deleteData(null);
                }));
        addDisposable(RxView.clicks(mQueryButton)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(o -> {
                    log("查询按键被点击");
                    ((BasePresenter) mPresenter).queryAllData();
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

    @Override
    public void showTip(Object msg) {
        mShowTipView.append(msg + "\n");
    }
}
