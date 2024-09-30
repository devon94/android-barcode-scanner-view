package com.devon94.androidbarcodescannerview

import android.content.Context
import android.view.KeyEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.viewevent.EventDispatcher
import expo.modules.kotlin.views.ExpoView

class AndroidBarcodeScannerView(context: Context, appContext: AppContext) : ExpoView(context, appContext) {
    private var barcodeBuilder = StringBuilder()
    private var lastKeyEventTime = 0L
    private val keyEventTimeout = 50L // milliseconds
    private var isListening = false
    private val onBarcodeScanned by EventDispatcher()
    
    private var keyPressCount = 0
    private val minBarcodeLength = 5
    private val maxKeyPressInterval = 50L // milliseconds

    init {
        // Set this view to use MATCH_PARENT for both width and height
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

        // Enable focus for key events
        isFocusable = true
        isFocusableInTouchMode = true
    }

    fun setListening(listening: Boolean) {
        isListening = listening
        isFocusable = isListening
        isFocusableInTouchMode = isListening
        if (isListening) {
            requestFocus()
        } else {
            clearFocus()
        }
    }

    override fun addView(child: android.view.View, index: Int, params: ViewGroup.LayoutParams) {
        super.addView(child, index, params)
        child.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (!isListening) return super.onKeyDown(keyCode, event)

        val currentTime = System.currentTimeMillis()
        val timeSinceLastKeyPress = currentTime - lastKeyEventTime

        if (timeSinceLastKeyPress > keyEventTimeout) {
            barcodeBuilder.clear()
            keyPressCount = 0
        }

        lastKeyEventTime = currentTime

        event?.let {
            val pressedKey = it.unicodeChar.toChar()
            if (pressedKey == '\u0000') return true

            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                val barcode = barcodeBuilder.toString()
                if (barcode.isNotEmpty() && keyPressCount >= minBarcodeLength && timeSinceLastKeyPress <= maxKeyPressInterval) {
                    emitBarcodeScannedEventAsync(cleanBarcodeData(barcode))
                    barcodeBuilder.clear()
                    keyPressCount = 0
                    return true // Consume the event
                } else {
                    barcodeBuilder.clear()
                    keyPressCount = 0
                    return false // Let the event propagate
                }
            } else {
                barcodeBuilder.append(pressedKey)
                keyPressCount++
            }
        }

        return super.onKeyDown(keyCode, event)
    }

    private fun cleanBarcodeData(barcode: String): String {
        return barcode.trimStart('\u0000')
    }

    private fun emitBarcodeScannedEventAsync(barcode: String) {
        onBarcodeScanned(mapOf("barcode" to barcode))
    }
}