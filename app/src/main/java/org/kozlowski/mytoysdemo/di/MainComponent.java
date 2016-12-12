package org.kozlowski.mytoysdemo.di;

import org.kozlowski.mytoysdemo.ui.component.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by and on 12.12.16.
 */
@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
