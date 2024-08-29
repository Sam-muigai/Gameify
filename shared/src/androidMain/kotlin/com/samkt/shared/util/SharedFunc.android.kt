package com.samkt.shared.util

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity


actual object SharedFunc {

    private var applicationContext: Context? = null

    private val context
        get() = applicationContext
            ?: error("Android context has not been set. Please call setContext in your Application's onCreate.")

    fun setContext(context: Context) {
        applicationContext = context.applicationContext
    }


    actual fun shareMessage(message: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
        }

        val chooserIntent = Intent.createChooser(shareIntent, "Share Via")
        chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (shareIntent.resolveActivity(context.packageManager) != null) {
            startActivity(context, chooserIntent, null)
        }
    }

    actual fun getGame(gameUrl: String) {
        val intent = Intent(ACTION_VIEW, Uri.parse(gameUrl))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(
            context,
            intent,
            null,
        )
    }
}