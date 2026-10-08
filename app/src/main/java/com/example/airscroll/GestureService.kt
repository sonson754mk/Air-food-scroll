package com.example.airscroll

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.view.accessibility.AccessibilityEvent

class GestureService : AccessibilityService() {

    companion object {
        var instance: GestureService? = null
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() {}

    fun swipeUp() {
        val path = Path().apply {
            val displayMetrics = resources.displayMetrics
            val middleX = (displayMetrics.widthPixels / 2).toFloat()
            val startY = (displayMetrics.heightPixels * 0.8).toFloat()
            val endY = (displayMetrics.heightPixels * 0.2).toFloat()
            moveTo(middleX, startY)
            lineTo(middleX, endY)
        }

        val gestureBuilder = GestureDescription.Builder()
        gestureBuilder.addStroke(GestureDescription.StrokeDescription(path, 0, 200))
        dispatchGesture(gestureBuilder.build(), null, null)
    }
}
