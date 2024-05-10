package com.hridoy.chatgemini.presentation.state

import android.graphics.Bitmap
import com.hridoy.chatgemini.domain.model.Chat

data class ChatState(
    val chatList: MutableList<Chat> = mutableListOf(),
    var prompt: String = "",
    val bitmap: Bitmap? = null,
    val showIndicator: Boolean = false,
)
