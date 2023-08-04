package com.samkt.gameify.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

@SuppressLint("QueryPermissionsNeeded")
fun shareMessage(context:Context, message:String){
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }

    val chooserIntent = Intent.createChooser(shareIntent, "Share Via")
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        ContextCompat.startActivity(context, chooserIntent, null)
    }
}