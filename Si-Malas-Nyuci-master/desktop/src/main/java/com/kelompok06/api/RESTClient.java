package com.kelompok06.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RESTClient {
    private static APISimalasNyuci CLIENT;
    public static final String BASE_URL = "";
    static {
        setClient();
    }

    private static void setClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CLIENT = retrofit.create(APISimalasNyuci.class);
    }

    public static APISimalasNyuci getCLIENT() {
        return CLIENT;
    }
}
