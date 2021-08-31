package com.ifresh.customer.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    /*========================================================================================================*/

    //offline path
    public static final String BASE_URL = "http://ifresh.co.in/paytm_checksum.php/";
    private static Retrofit retrofit = null;
    private static ApiInterface apiWorkInterface;

    public static ApiInterface getClient()
    {
        if (retrofit == null)
        {
            Gson gson = new GsonBuilder().setLenient().create();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES) // write timeout
                    .readTimeout(2, TimeUnit.MINUTES)
                    .addInterceptor(interceptor).build();

            /*Values Inserted in retrofit*/
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        if (apiWorkInterface == null)
        {
            apiWorkInterface = retrofit.create(ApiInterface.class);
        }
        return apiWorkInterface;
    }
}
