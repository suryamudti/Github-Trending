package com.surya.githubtrending.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by suryamudti on 12/11/2019.
 */

@Entity
data class Trend(
    @PrimaryKey
    @SerializedName("author")
    val author: String,
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("currentPeriodStars")
    val currentPeriodStars: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("forks")
    val forks: Int?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("languageColor")
    val languageColor: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("stars")
    val stars: Int?,
    @SerializedName("url")
    val url: String?
)