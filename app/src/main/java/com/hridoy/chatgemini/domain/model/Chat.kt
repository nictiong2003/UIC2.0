package com.hridoy.chatgemini.domain.model

import android.graphics.Bitmap
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.GenerationConfig
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.ai.client.generativeai.type.generationConfig

data class Chat(
    val prompt: String,
    val bitmap: Bitmap?,
    val isFromUser: Boolean,
)

val generationConfig: GenerationConfig = generationConfig {
    temperature = 0.9f
    topK = 1
    topP = 1f
    maxOutputTokens = 8192
}

val safetySettings: List<SafetySetting> = listOf(
    SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.MEDIUM_AND_ABOVE),
    SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.MEDIUM_AND_ABOVE),
    SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.MEDIUM_AND_ABOVE),
    SafetySetting(HarmCategory.DANGEROUS_CONTENT, BlockThreshold.MEDIUM_AND_ABOVE),
)

