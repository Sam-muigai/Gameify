package com.samkt.shared.util


expect object SharedFunc {
    fun shareMessage(message: String)

    fun getGame(gameUrl: String)
}