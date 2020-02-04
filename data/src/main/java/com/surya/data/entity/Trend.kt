package com.surya.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by suryamudti on 12/11/2019.
 */


data class Trend(
//    @PrimaryKey
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