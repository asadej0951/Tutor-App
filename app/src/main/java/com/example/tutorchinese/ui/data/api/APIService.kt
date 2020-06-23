package com.example.tutorchinese.ui.data.api

import com.example.tutorchinese.ui.data.response.RegisterResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIService {
    /*@FormUrlEncoded
    @POST("services/toey_test.php?func=getInvoice")
    fun getInvoice(
        @Field("vehicle_id") vehicle_id: String
    ): Observable<InvoiceResponse>*/

    /*@FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("user") username: String,
        @Field("pass") password: String,
        @Field("serial") serial: String
    ): Observable<UserResponse>


    @FormUrlEncoded
    @POST("api/login")
    fun logout(
        @Field("user") username: String,
        @Field("pass") password: String,
        @Field("serial") serial: String
    ): Observable<UserResponse>*/


    @FormUrlEncoded
    @POST("api/login")
    fun register(@Body body: RequestBody): Observable<RegisterResponse>
}