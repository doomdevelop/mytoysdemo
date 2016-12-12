package org.kozlowski.mytoysdemo.ui.component.main;

import android.os.Bundle;
import android.provider.SyncStateContract;

import org.kozlowski.mytoysdemo.ui.base.Presenter;
import org.kozlowski.mytoysdemo.util.Constants;

import javax.inject.Inject;

/**
 * Created by and on 12.12.16.
 */

public class MainPresenter extends Presenter<MainView> {

    @Inject
    public MainPresenter() {

    }
    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        view.initWebView(Constants.BASE_WEB_URL);
        view.setupDrawerToggle();
    }


}
