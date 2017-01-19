package me.stepy.app.data.api.model

data class Response<out T>(val header:Header, val body: T)
data class Header(val status: String, val message: String)
//data class Body<out T>(val body: T)
