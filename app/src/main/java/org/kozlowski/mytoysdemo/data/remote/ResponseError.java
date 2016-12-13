package org.kozlowski.mytoysdemo.data.remote;

/**
 * Created by and on 13.12.16.
 */

public class ResponseError {

    private String errorMessage;

    public ResponseError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
