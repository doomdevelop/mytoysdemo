package org.kozlowski.mytoysdemo.usecase;

import android.os.Handler;
import android.os.Looper;

import org.kozlowski.mytoysdemo.data.remote.ResponseError;
import org.kozlowski.mytoysdemo.data.remote.ResponseWrapper;
import org.kozlowski.mytoysdemo.data.repository.DataRepository;
import org.kozlowski.mytoysdemo.model.Children;
import org.kozlowski.mytoysdemo.model.NavigationEntries;

import javax.inject.Inject;

/**
 * Created by and on 13.12.16.
 */

public class NavigationEntriesUseCase {
    private final DataRepository dataRepository;
    @Inject
    public NavigationEntriesUseCase(DataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    public void getNavigationEntries(final CallBack callBack){
        new Thread(() -> {
            NavigationEntries navigationEntries = null;
            ResponseWrapper responseWrapper = dataRepository.getNavigationEntries();
            if (responseWrapper != null) {
                navigationEntries = (NavigationEntries) responseWrapper.getResponse();
            }
            final NavigationEntries navigationEntriesFinal = navigationEntries;
            new Handler(Looper.getMainLooper()).post(() -> {
                if (navigationEntriesFinal != null) {
                    callBack.onSuccess(navigationEntriesFinal);
                } else {
                    callBack.onError(responseWrapper.getError());
                }
            });
        }).start();
    }

    public interface CallBack{
        void onSuccess(NavigationEntries navigationEntries);

        void onError(ResponseError error);
    }
}
