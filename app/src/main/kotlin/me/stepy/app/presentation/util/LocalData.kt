package me.stepy.app.presentation.util

/**
 * Created by kihare on 2017/01/17.
 */
object LocalData {
    var deviceId: String
        get() = SharedPreferencesWrap.getString("device_id")
        set(id) = SharedPreferencesWrap.setString("device_id", id)
}