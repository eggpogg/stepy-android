package me.stepy.app.data.api.client

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import me.stepy.app.App
import okhttp3.CookieJar
import okhttp3.OkHttpClient

object HttpClient {
    private val cookie: CookieJar by lazy {
        PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(App.context))
    }

    val client: OkHttpClient by lazy {
        OkHttpClient().newBuilder().cookieJar(cookie).build()
    }
}
