package org.kozlowski.mytoysdemo.data.repository;

import android.support.annotation.NonNull;

import org.kozlowski.mytoysdemo.data.remote.ResponseError;
import org.kozlowski.mytoysdemo.data.remote.ResponseWrapper;
import org.kozlowski.mytoysdemo.data.remote.ServiceGenerator;
import org.kozlowski.mytoysdemo.data.remote.service.NavigationService;
import org.kozlowski.mytoysdemo.model.Children;
import org.kozlowski.mytoysdemo.model.NavigationEntries;
import org.kozlowski.mytoysdemo.util.Constants;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by and on 13.12.16.
 */
@Singleton
public class ApiRepository {
    private ServiceGenerator serviceGenerator;

    @Inject
    public ApiRepository(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;
    }

    public ResponseWrapper getNavigationEntries() {
        NavigationService navigationService = serviceGenerator.createService(
            NavigationService.class, Constants.NAVIGATION_ENDPOINT);
        Call<NavigationEntries> navigationEntries = navigationService.getNavigationEntries();
        return processResponse(navigationEntries);
    }
    @NonNull
    private ResponseWrapper processCall(Call call) {
        ResponseWrapper responseWrapper = processResponse(call);
        final ResponseError responseError = responseWrapper.getError();
        if (responseError != null) {

        }
        return responseWrapper;
    }
    @NonNull
    private ResponseWrapper processResponse(Call call) {
        try {
            Response response = call.execute();
            int responseCode = response.code();
            if (response.isSuccessful()) {
                return new ResponseWrapper(responseCode, response.body());
            } else {
                return new ResponseWrapper(new ResponseError(response.errorBody().string()));
            }
        } catch (IOException e1) {
            e1.printStackTrace();
            return new ResponseWrapper(new ResponseError(Constants.KEY_UNKNOWN_ERROR));
        }
    }

}
