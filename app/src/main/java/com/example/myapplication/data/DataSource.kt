package com.example.myapplication.data

import com.example.myapplication.R

object DataSource {

    val topBarActionIcons = listOf(
        R.drawable.qr_code_scanner_24px,
        R.drawable.photo_camera_24px,
        R.drawable.more_vert_24px
    )

    val bottomBarItems = listOf(
        BottomBarItem(
            icon = R.drawable.chat_24px,
            title = "Chats"
        ),

        BottomBarItem(
            icon = R.drawable.cached_24px,
            title = "Updates"
        ),

        BottomBarItem(
            icon = R.drawable.groups_24px,
            title = "Communities"
        ),

        BottomBarItem(
            icon = R.drawable.call_24px,
            title = "Calls"
        ),

        )

    val chatFilterType = listOf(
        "All",
        "Unread",
        "Favourites",
        "Groups"
    )

    fun getChatItems(): List<ChatProperties> {
        val chatItems = mutableListOf<ChatProperties>()

        for (i in 1..50) {
            val x = (1..2).random()
            val imageRes = when (x) {
                1 -> R.drawable.profile_pic
                2 -> R.drawable.profile_pic_female
                else -> R.drawable.profile_pic
            }

            val name = when (x) {
                1 -> "William"
                2 -> "Alice"
                else -> "William"
            }

            chatItems.add(
                ChatProperties(
                    senderName = name,
                    senderProfilePic = imageRes,
                    lastMessage = "Shall we hangout after classes tomorrow?",
                    timeOfLastMessage = "19/07/2024"
                )
            )
        }

        return chatItems
    }
}