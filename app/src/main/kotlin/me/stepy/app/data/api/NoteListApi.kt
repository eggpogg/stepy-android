package me.stepy.app.data.api

import me.stepy.app.Environment
import me.stepy.app.data.api.client.HttpClient
import me.stepy.app.data.api.model.Response
import me.stepy.app.domain.model.Notebook
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import rx.Observable

interface NoteListApi {
    @GET("/notebooks")
    fun call(
            @Query("api_key") apiKey: String = Environment.API_KEY
    ): Observable<Response<MutableList<Notebook>>>

    @POST("/notebooks")
    @FormUrlEncoded
    fun create(
            @Field("api_key") apiKey: String = Environment.API_KEY,
            @Field("title") title: String
    ): Observable<Response<Notebook>>
}

object NoteApiFactory {

    fun create(): NoteListApi {
        val builder = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Environment.BASE_URL)
                .client(HttpClient.client)
        return builder.build().create(NoteListApi::class.java)
    }

}