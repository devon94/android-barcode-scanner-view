import { EventEmitter, requireNativeViewManager } from "expo-modules-core"
import * as React from "react"

import {
  AndroidBarcodeScannerViewProps,
  NativeAndroidBarcodeScannerViewProps,
  InternalBarcodeScannedEventPayload,
  BarcodeScannedEventPayload,
} from "./AndroidBarcodeScannerView.types"

const NativeView: React.ComponentType<NativeAndroidBarcodeScannerViewProps> =
  requireNativeViewManager("AndroidBarcodeScannerView")

export default function AndroidBarcodeScannerView(
  props: AndroidBarcodeScannerViewProps,
) {
  const { onBarcodeScanned, children, style, ...otherProps } = props

  const handleBarcodeScanned = React.useCallback(
    async (event: { nativeEvent: BarcodeScannedEventPayload }) => {
      await props.onBarcodeScanned?.(event.nativeEvent)
    },
    [onBarcodeScanned],
  )

  return (
    <NativeView
      {...otherProps}
      style={style}
      onBarcodeScanned={handleBarcodeScanned}
    >
      {children}
    </NativeView>
  )
}
