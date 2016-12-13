package org.kozlowski.mytoysdemo.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.kozlowski.mytoysdemo.model.Children;
import org.kozlowski.mytoysdemo.model.NavigationEntries;
import org.kozlowski.mytoysdemo.util.gson.NavigationEntriesDeserializer;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by and on 13.12.16.
 */

public class ServiceGenerator {
    private final OkHttpClient.Builder okHttpBuilder;
    private Retrofit retrofit;
    private final Gson gson;

    @Inject
    public ServiceGenerator() {
        this.okHttpBuilder = new OkHttpClient.Builder();
        this.gson = createGson();
    }

    private Gson createGson() {
        return new GsonBuilder()
            .registerTypeAdapter(NavigationEntries.class, new NavigationEntriesDeserializer())
            .create();
    }


    public <S> S createService(Class<S> serviceClass, String baseUrl) {
        OkHttpClient client = okHttpBuilder.build();
        retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
        return retrofit.create(serviceClass);
    }
}
