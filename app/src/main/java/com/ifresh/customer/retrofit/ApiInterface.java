package com.ifresh.customer.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface
{
    @FormUrlEncoded
    @POST("paytm_checksum.php")
    Call<ChecksumResponse> getCheckSum(@Field("ORDER_ID") String oId,
                                       @Field("MID") String mId,
                                       @Field("CUST_ID") String custId,
                                       @Field("CHANNEL_ID") String channel,
                                       @Field("INDUSTRY_TYPE_ID") String ind,
                                       @Field("WEBSITE") String web,
                                       @Field("TXN_AMOUNT") String txn,
                                       @Field("CALLBACK_URL") String call);

}
