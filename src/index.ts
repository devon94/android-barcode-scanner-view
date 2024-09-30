import { NativeModulesProxy, EventEmitter, Subscription } from 'expo-modules-core';

// Import the native module. On web, it will be resolved to AndroidBarcodeScannerView.web.ts
// and on native platforms to AndroidBarcodeScannerView.ts
import AndroidBarcodeScannerViewModule from './AndroidBarcodeScannerViewModule';
import AndroidBarcodeScannerView from './AndroidBarcodeScannerView';
import { ChangeEventPayload, AndroidBarcodeScannerViewProps } from './AndroidBarcodeScannerView.types';

// Get the native constant value.
export const PI = AndroidBarcodeScannerViewModule.PI;

export function hello(): string {
  return AndroidBarcodeScannerViewModule.hello();
}

export async function setValueAsync(value: string) {
  return await AndroidBarcodeScannerViewModule.setValueAsync(value);
}

const emitter = new EventEmitter(AndroidBarcodeScannerViewModule ?? NativeModulesProxy.AndroidBarcodeScannerView);

export function addChangeListener(listener: (event: ChangeEventPayload) => void): Subscription {
  return emitter.addListener<ChangeEventPayload>('onChange', listener);
}

export { AndroidBarcodeScannerView, AndroidBarcodeScannerViewProps, ChangeEventPayload };
