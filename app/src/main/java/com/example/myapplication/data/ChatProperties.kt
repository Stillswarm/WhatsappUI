package com.example.myapplication.data

import androidx.annotation.DrawableRes

data class ChatProperties(
    val senderName: String,
    @DrawableRes val senderProfilePic: Int,
    val lastMessage: String,
    val timeOfLastMessage: String
)
