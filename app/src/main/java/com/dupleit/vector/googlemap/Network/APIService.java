package com.dupleit.vector.googlemap.Network;


import com.dupleit.vector.googlemap.modal.UsersMaps;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    //for login
    @GET("getUsers")
    Call<UsersMaps> UserLogin();

   }