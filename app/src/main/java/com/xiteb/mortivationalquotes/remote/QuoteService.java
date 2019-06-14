package com.xiteb.mortivationalquotes.remote;

import com.xiteb.mortivationalquotes.model.Quote;
import com.xiteb.mortivationalquotes.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteService {
    @GET("photos")
    Call<List<Quote>> getmortiqoutes();

    @GET("users")
    Call<List<User>> getotherqoutestitle();

}
