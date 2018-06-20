package com.millionrmaker.interfaceexample;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRxJava {

    String base_url="http://jsonplaceholder.typicode.com/";

    @GET("users")
    Observable<List<USER>> getUser();
}
