package com.samkt.gameify

import android.app.Application
import android.content.Context
import androidx.compose.ui.graphics.ShaderBrush
import com.samkt.shared.util.SharedFunc


class GameifyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        SharedFunc.setContext(applicationContext)
    }
}
