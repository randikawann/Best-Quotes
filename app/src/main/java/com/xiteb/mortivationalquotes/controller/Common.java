package com.xiteb.mortivationalquotes.controller;

import com.xiteb.mortivationalquotes.remote.QuoteService;
import com.xiteb.mortivationalquotes.remote.RetrofitClient;

public class Common {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static QuoteService getadsService(){

        return RetrofitClient.getClient(BASE_URL).create(QuoteService.class);

    }

}
