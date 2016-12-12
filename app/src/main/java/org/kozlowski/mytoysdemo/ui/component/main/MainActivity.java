package org.kozlowski.mytoysdemo.ui.component.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.kozlowski.mytoysdemo.MyToysApplication;
import org.kozlowski.mytoysdemo.R;
import org.kozlowski.mytoysdemo.ui.base.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainView{

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initializeDagger() {
        MyToysApplication app = (MyToysApplication) getApplication();
        app.getMainComponent().inject(this);
    }
}
