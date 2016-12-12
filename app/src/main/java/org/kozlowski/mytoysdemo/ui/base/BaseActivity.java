package org.kozlowski.mytoysdemo.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.kozlowski.mytoysdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by and on 12.12.16.
 */

public abstract class BaseActivity extends AppCompatActivity implements Presenter.View {
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initializeButterKnife();
        initializeDagger();
        initializeToolbar();
        initializePresenter();
        if (presenter != null) {
            presenter.initialize(getIntent().getExtras());
        }
    }

    protected Presenter presenter;

    protected abstract void initializePresenter();

    public abstract int getLayoutId();

    protected abstract void initializeDagger();


    private void initializeButterKnife() {
        ButterKnife.bind(this);
    }

    protected void initializeToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }
}
