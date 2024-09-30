import { AndroidBarcodeScannerView } from "android-barcode-scanner-view"
import { BarcodeScannedEventPayload } from "android-barcode-scanner-view/AndroidBarcodeScannerView.types"
import { StyleSheet, Text } from "react-native"

export default function App() {
  const onBarcodeScanned = async ({ barcode }: BarcodeScannedEventPayload) => {
    console.log(barcode)
  }

  return (
    <AndroidBarcodeScannerView
      listening={true}
      style={styles.container}
      onBarcodeScanned={onBarcodeScanned}
    >
      <Text>Hello</Text>
    </AndroidBarcodeScannerView>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
})
