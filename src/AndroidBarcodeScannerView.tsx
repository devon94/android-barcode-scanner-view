import { requireNativeViewManager } from 'expo-modules-core';
import * as React from 'react';

import { AndroidBarcodeScannerViewProps } from './AndroidBarcodeScannerView.types';

const NativeView: React.ComponentType<AndroidBarcodeScannerViewProps> =
  requireNativeViewManager('AndroidBarcodeScannerView');

export default function AndroidBarcodeScannerView(props: AndroidBarcodeScannerViewProps) {
  return <NativeView {...props} />;
}
