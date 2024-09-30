import { PropsWithChildren } from "react"
import { StyleProp, ViewStyle } from "react-native"

export type BarcodeScannedEventPayload = {
  barcode: string;
};

export type InternalBarcodeScannedEventPayload = BarcodeScannedEventPayload & {
  promise: {
    resolve: () => void;
    reject: (reason?: any) => void;
  };
};

export type AndroidBarcodeScannerViewProps = PropsWithChildren<{
  listening?: boolean;
  onBarcodeScanned?: (event: BarcodeScannedEventPayload) => Promise<void>;
  style?: StyleProp<ViewStyle>;
}>;

export type NativeAndroidBarcodeScannerViewProps = Omit<
  AndroidBarcodeScannerViewProps,
  "onBarcodeScanned"
> & {
  onBarcodeScanned?: (event: {
    nativeEvent: InternalBarcodeScannedEventPayload;
  }) => void;
};
