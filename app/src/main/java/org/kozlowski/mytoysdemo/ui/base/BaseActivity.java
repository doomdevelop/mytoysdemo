package org.kozlowski.mytoysdemo.ui.base;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by and on 12.12.16.
 */

public abstract class BaseActivity extends AppCompatActivity implements Presenter.View {
    protected Presenter presenter;

    protected abstract void initializePresenter();

    public abstract int getLayoutId();

    protected abstract void initializeDagger();
}
