package org.kozlowski.mytoysdemo.data.remote;

/**
 * Created by and on 13.12.16.
 */

public class ResponseWrapper {
    private int code;
    private Object response;
    private ResponseError error;

    public ResponseWrapper(int code, Object response) {
        this.code = code;
        this.response = response;
    }

    public ResponseWrapper(ResponseError error) {
        this.error = error;
    }

    public ResponseWrapper(Object response) {
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public ResponseError getError() {
        return error;
    }

    public Object getResponse() {

        return response;
    }
}
