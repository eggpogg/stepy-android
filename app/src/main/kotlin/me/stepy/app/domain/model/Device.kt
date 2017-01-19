package me.stepy.app.domain.model

import java.util.*

data class Device(
        val id: String,
        val createdAt: Date,
        val updatedAt: Date,
        val deletedAt: Date
)