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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Children children = null;
                ResponseWrapper responseWrapper = dataRepository.getNavigationEntries();
                if(responseWrapper != null){
                    children = (Children) responseWrapper
                        .getResponse();
                }
                final Children childrenFinal = children;
                new Handler(Looper.getMainLooper()).post(() -> {
                    if (childrenFinal != null) {
                        callBack.onSuccess(childrenFinal);
                    } else {
                        callBack.onError(responseWrapper.getError());
                    }
                });
            }
        }).start();
    }

    public interface CallBack{
        void onSuccess(Children children);

        void onError(ResponseError error);
    }
}
