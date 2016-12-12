package org.kozlowski.mytoysdemo;

import android.app.Application;
import android.content.Context;

import org.kozlowski.mytoysdemo.di.DaggerMainComponent;
import org.kozlowski.mytoysdemo.di.MainComponent;

/**
 * Created by and on 12.12.16.
 */

public class MyToysApplication extends Application {
    private MainComponent mainComponent;

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.create();
        context = getApplicationContext();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
