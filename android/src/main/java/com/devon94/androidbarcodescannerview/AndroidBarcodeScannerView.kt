package com.devon94.androidbarcodescannerview

import android.content.Context
import android.view.KeyEvent
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.viewevent.EventDispatcher
import expo.modules.kotlin.views.ExpoView
import expo.modules.kotlin.Promise
import expo.modules.kotlin.modules.Module
import android.os.Bundle

class AndroidBarcodeScannerView(context: Context, appContext: AppContext) : ExpoView(context, appContext) {
    private var barcodeBuilder = StringBuilder()
    private var lastKeyEventTime = 0L
    private val keyEventTimeout = 100L // milliseconds
    private var isListening = false
    private val onBarcodeScanned by EventDispatcher()

    init {
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (!isListening) return super.onKeyDown(keyCode, event)

        val currentTime = System.currentTimeMillis()

        if (currentTime - lastKeyEventTime > keyEventTimeout) {
            barcodeBuilder = StringBuilder()
        }

        lastKeyEventTime = currentTime

        event?.let {
            val pressedKey = it.unicodeChar.toChar()
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                val barcode = barcodeBuilder.toString()
                if (barcode.isNotEmpty()) {
                    emitBarcodeScannedEventAsync(barcode)
                    barcodeBuilder = StringBuilder()
                    return true // Consume the event
                } else {
                    return super.onKeyDown(keyCode, event)
                }
            } else {
                barcodeBuilder.append(pressedKey)
            }
        }

        return super.onKeyDown(keyCode, event)
    }

    private fun emitBarcodeScannedEventAsync(barcode: String) {
      onBarcodeScanned(mapOf("barcode" to barcode))
    }
}