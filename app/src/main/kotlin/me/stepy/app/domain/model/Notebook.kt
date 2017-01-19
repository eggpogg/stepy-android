package me.stepy.app.domain.model

import com.google.gson.Gson
import java.io.IOException
import java.util.*

data class Notebook(
        val id: String,
        val title: String,
        val items: MutableList<Item>,
        val deviceId: String,
        val createdAt: Date,
        val updatedAt: Date,
        val deletedAt: Date?) {

    @Throws(IOException::class)
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        @Throws(IOException::class)
        fun create(jsonString: String): Notebook {
            return Gson().fromJson(jsonString, Notebook::class.java)
        }
    }
}
