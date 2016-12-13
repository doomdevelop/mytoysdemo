package org.kozlowski.mytoysdemo.data.repository;

import org.kozlowski.mytoysdemo.data.remote.ResponseWrapper;

import javax.inject.Inject;

/**
 * Created by and on 13.12.16.
 */

public class DataRepository {
    private ApiRepository apiRepository;

    @Inject
    public DataRepository(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    public ResponseWrapper getNavigationEntries(){
        return apiRepository.getNavigationEntries();
    }
}
