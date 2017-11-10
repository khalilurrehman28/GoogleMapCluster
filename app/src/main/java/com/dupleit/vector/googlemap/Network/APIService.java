package com.dupleit.vector.googlemap.Network;


import com.dupleit.vector.googlemap.modal.UsersMaps;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    //for login
    @FormUrlEncoded
    @POST("getUsers")
    Call<UsersMaps> UserLogin(@Field("test") String email);

   }