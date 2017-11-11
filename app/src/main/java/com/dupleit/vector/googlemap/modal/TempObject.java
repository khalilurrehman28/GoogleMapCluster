package com.dupleit.vector.googlemap.modal;

import retrofit2.Response;

/**
 * Created by rajesh on 4/11/17.
 */

public class TempObject {
    Response<UsersMaps> response;

    public TempObject(Response<UsersMaps> response) {
        this.response = response;
    }

    public Response<UsersMaps> getResponse() {
        return response;
    }

}
