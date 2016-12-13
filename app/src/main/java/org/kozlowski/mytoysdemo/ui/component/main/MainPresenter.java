package org.kozlowski.mytoysdemo.ui.component.main;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;

import org.kozlowski.mytoysdemo.data.remote.ResponseError;
import org.kozlowski.mytoysdemo.model.Children;
import org.kozlowski.mytoysdemo.model.ChildrenNode;
import org.kozlowski.mytoysdemo.model.NavigationEntries;
import org.kozlowski.mytoysdemo.ui.base.Presenter;
import org.kozlowski.mytoysdemo.usecase.NavigationEntriesUseCase;
import org.kozlowski.mytoysdemo.util.Constants;

import javax.inject.Inject;

/**
 * Created by and on 12.12.16.
 */

public class MainPresenter extends Presenter<MainView> {
    final NavigationEntriesUseCase navigationEntriesUseCase;
    @Inject
    public MainPresenter(final NavigationEntriesUseCase navigationEntriesUseCase) {
        this.navigationEntriesUseCase = navigationEntriesUseCase;
    }
    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        view.initWebView(Constants.BASE_WEB_URL);
        view.setupDrawerToggle();
        navigationEntriesUseCase.getNavigationEntries(callBack);
    }
    private NavigationEntriesUseCase.CallBack callBack = new NavigationEntriesUseCase.CallBack() {
        @Override
        public void onSuccess(Children children) {
            if(children instanceof ChildrenNode) {
                Log.d("PRESENTER", ((ChildrenNode)children).getChildren().get(0).getLabel());
            }
        }

        @Override
        public void onError(ResponseError error) {
            Log.d("PRESENTER" ,error.getErrorMessage());
        }
    };

}
