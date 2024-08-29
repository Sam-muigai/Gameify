package com.samkt.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform