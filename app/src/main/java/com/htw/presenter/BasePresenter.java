package com.htw.presenter;

import android.util.Log;

import com.htw.model.BaseModel;
import com.htw.model.IModel;
import com.htw.structure.UserInfo;
import com.htw.view.IView;

import java.lang.ref.WeakReference;
import java.util.Random;

import io.realm.RealmResults;

public class BasePresenter<T> implements IPresenter {
    protected WeakReference mView;
    protected IModel mModel;

    @Override
    public void register(IView view) {
        mView = new WeakReference(view);
        mModel = new BaseModel();
    }

    @Override
    public void unRegister() {
        mView.clear();
        mModel.getRealm().close();
    }

    public void createDataBase() {
    }

    public void addData(UserInfo data) {
        mModel.getRealm().executeTransactionAsync(realm -> {
                    realm.copyToRealmOrUpdate(data);
                }, () -> showTip(data.getName() + ": 插入成功"),
                error -> showTip(data.getName() + ": 插入失败错误原因：" + error));
    }

    public void deleteData(T data) {
        RealmResults<UserInfo> list = mModel.getRealm().where(UserInfo.class).findAll();
        if (list.size() > 0) {
            mModel.getRealm().executeTransaction(realm -> {
                UserInfo info = list.get(new Random().nextInt(list.size()));
                showTip(info.getName() + ": 删除成功");
                info.deleteFromRealm();
            });
        }
    }

    public void queryAllData() {
        RealmResults<UserInfo> list = mModel.getRealm().where(UserInfo.class).findAll();
        for (UserInfo info : list) {
            Log.e("huangtanwen", "name " + info.getName() + "；age " + info.getAge());
        }
        showTip("查询成功，数据 " + list.size() + "条");
    }

    private void showTip(String msg) {
        ((IView) mView.get()).showTip(msg);
    }
}
