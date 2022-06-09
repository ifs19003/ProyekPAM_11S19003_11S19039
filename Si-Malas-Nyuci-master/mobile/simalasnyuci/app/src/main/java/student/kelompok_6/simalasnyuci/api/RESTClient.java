package student.kelompok_6.simalasnyuci.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RESTClient {
    private static ApiSiMalasNyuci REST_CLIENT;
    public static String BASE_URL = "http://192.168.1.6/si-malas-nyuci/server/public/";
    static {
        setupRestClient();
    }
    private RESTClient(){}
    private static void setupRestClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        REST_CLIENT = retrofit.create(ApiSiMalasNyuci.class);
    }
    public static ApiSiMalasNyuci get(){
        return REST_CLIENT;
    }
}
