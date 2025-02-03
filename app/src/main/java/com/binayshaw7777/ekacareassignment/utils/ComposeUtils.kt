package com.binayshaw7777.ekacareassignment.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn

fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}

val FadeIn = fadeIn(
    animationSpec = tween(durationMillis = 220, delayMillis = 90)
) + scaleIn(
    initialScale = 0.92f,
    animationSpec = tween(durationMillis = 220, delayMillis = 90)
)

val FadeOut = fadeOut(
    animationSpec = tween(durationMillis = 90)
)