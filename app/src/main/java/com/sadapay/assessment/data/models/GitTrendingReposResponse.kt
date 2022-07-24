package com.sadapay.assessment.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class GitTrendingReposResponse (

    @SerializedName("items")
    val items: List<Items> = emptyList(),

    )

@Parcelize
data class Items(

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("full_name")
    val fullName: String,

    @SerializedName("description")
    val description: String?,


    @SerializedName("stargazers_count")
    val stars: Int,


    @SerializedName("language")
    val language: String?,


    @SerializedName("owner")
    val owner: Owner,

    var isExpanded : Boolean = false
    ) : Parcelable

@Parcelize
data class Owner(
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatar_url: String

): Parcelable