package com.rancreation.mortivationalquotes.remote;

import com.rancreation.mortivationalquotes.model.Quote;
import com.rancreation.mortivationalquotes.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteService {
    @GET("photos")
    Call<List<Quote>> getmortiqoutes();

    @GET("users")
    Call<List<User>> getotherqoutestitle();

}
