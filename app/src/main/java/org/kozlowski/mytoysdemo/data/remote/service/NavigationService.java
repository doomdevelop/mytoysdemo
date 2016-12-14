package org.kozlowski.mytoysdemo.data.remote.service;

import org.kozlowski.mytoysdemo.model.Children;
import org.kozlowski.mytoysdemo.model.NavigationEntries;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by and on 13.12.16.
 */

public interface NavigationService {

    @Headers("x-api-key: hz7JPdKK069Ui1TRxxd1k8BQcocSVDkj219DVzzD")
    @GET("/api/navigation")
    Call<NavigationEntries> getNavigationEntries();
}
