import { StyleSheet, Text, View } from 'react-native';

import * as AndroidBarcodeScannerView from 'android-barcode-scanner-view';

export default function App() {
  return (
    <View style={styles.container}>
      <Text>{AndroidBarcodeScannerView.hello()}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
