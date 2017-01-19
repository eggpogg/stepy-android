package me.stepy.app.data.api

import me.stepy.app.Environment
import me.stepy.app.data.api.client.HttpClient
import me.stepy.app.data.api.model.Response
import me.stepy.app.domain.model.Device
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface DeviceApi {
    @GET("/devices")
    fun call(@Query("api_key") apiKey: String = Environment.API_KEY): Observable<Response<Device>>
}

object DeviceApiFactory {

    fun create(): DeviceApi {
        val builder = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Environment.BASE_URL)
                .client(HttpClient.client)
        return builder.build().create(DeviceApi::class.java)
    }

}