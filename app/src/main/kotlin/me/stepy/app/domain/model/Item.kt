package me.stepy.app.domain.model

import java.util.*

data class Item(
        val id: String,
        val title: String,
        val notebook: String,
        val state: String,
        val createdAt: Date,
        val updatedAt: Date,
        val deletedAt: Date)