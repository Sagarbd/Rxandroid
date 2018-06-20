package com.millionrmaker.interfaceexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
   String base_url="http://jsonplaceholder.typicode.com/";

    @GET("users")
    Call<List<USER>> getUser();
}
