package com.samkt.shared.util

import platform.Foundation.NSString
import platform.Foundation.create
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import platform.Foundation.NSURL


actual object SharedFunc {

    actual fun shareMessage(message: String) {
        val activityController = UIActivityViewController(
            activityItems = listOf(NSString.create(string = message)),
            applicationActivities = null
        )

        // Get the root view controller
        val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
            ?: error("Root view controller is not available")

        rootViewController.presentViewController(activityController, animated = true, completion = null)
    }

    actual fun getGame(gameUrl: String) {
        val nsUrl = NSURL(string = gameUrl)
        if (UIApplication.sharedApplication.canOpenURL(nsUrl)) {
            UIApplication.sharedApplication.openURL(nsUrl)
        } else {
            println("Unable to open URL: $gameUrl")
        }
    }
}
