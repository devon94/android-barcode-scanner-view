package com.devon94.androidbarcodescannerview

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class AndroidBarcodeScannerViewModule : Module() {
  override fun definition() = ModuleDefinition {
    Name("AndroidBarcodeScannerView")

    View(AndroidBarcodeScannerView::class) {
      Prop("listening") { view: AndroidBarcodeScannerView, listening: Boolean ->
        view.setListening(listening)
      }

      Events("onBarcodeScanned")
    }
  }
}