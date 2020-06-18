package com.akshat.jet2sample.model


import com.google.gson.annotations.SerializedName

data class Response(
    val responseItem: List<ResponseItem>
)

data class ResponseItem(
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("media")
    val media: List<Media>,
    @SerializedName("user")
    val user: List<User>
)

data class Media(
    @SerializedName("blogId")
    val blogId: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)

data class User(
    @SerializedName("about")
    val about: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("blogId")
    val blogId: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("designation")
    val designation: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("name")
    val name: String
)