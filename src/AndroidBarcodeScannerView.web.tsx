import * as React from 'react';

import { AndroidBarcodeScannerViewProps } from './AndroidBarcodeScannerView.types';

export default function AndroidBarcodeScannerView(props: AndroidBarcodeScannerViewProps) {
  return (
    <div>
      <span>{props.name}</span>
    </div>
  );
}
