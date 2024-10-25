
# Astrapay QRIS SDK for Android

An Android SDK to integrate Astrapay QRIS payment services into your mobile application, enabling seamless QRIS transactions.

## Installation

To install the Astrapay QRIS SDK in your project, follow these steps:

#### Gradle Setup:
1. Open your app/build.gradle file at the app level and ensure that ViewBinding is enabled. If you have already enabled ViewBinding, you can skip this step.
```groovy
android {
    // Enable ViewBinding if not already enabled
    buildFeatures {
        viewBinding true
    }
}
```
2. Add the Astrapay QRIS SDK dependency to your project by including the following line in the dependencies section:
```groovy
dependencies {
    implementation "com.astrapay:qris-sdk-android:<version>"
}
```
> [!NOTE]  
> Note: Replace <version> with the latest version of the Astrapay QRIS SDK. Check [Astrapay SDK release](https://www.npmjs.com/package/@astrapay/qris-react-native?activeTab=versions)  for the latest release.
3. Sync your project with Gradle to ensure the SDK is correctly installed.

## Configuration
Before starting any QRIS transaction, initialize the SDK with a valid configuration object.
```kotlin
AstraPayQris.initialize(configuration = configuration)
```
#### QrisSdkConfiguration

| Parameter     | Type     | Required     | Description|
| :--------     | :------- | :-------     | :----------|
| `authToken`   | `string` | Yes          | Authentication token provided by Custommer Astrapay |
| `sdkToken`   | `string` | Yes           | SDK token provided by registered client |
| `environment`   | `string` | Yes        | API environment (UAT for testing or PROD for production) |
| `isSnap`   | `boolean` | Yes        | Boolean flag to indicate if the client already use Snap |

#### Example
```kotlin
val configuration = QRConfiguration.Builder(
            authToken = "",
            sdkToken = "",
            environment = "UAT",
            isSnap = true
        )
            .setEventListener(this)
            .build()
```
> [!NOTE]  
> Note: Ensure your configuration is accurate. Invalid parameters may lead to failed transactions or improper initialization.

## Usage
Once the SDK is initialized, you can start a QRIS transaction:

```kotlin
AstraPayQris.getInstance().start(this)
```
Call this method to start a new QRIS transaction.

## Methods
The SDK provides several methods to facilitate the QRIS transaction flow:

* AstraPayQris.initialize(config): Initializes the SDK with the required configuration parameters.
* AstraPayQris.getInstance(): Retrieves the initialized SDK instance. Must be called after initialize().
* AstraPayQris.startTransaction(context): Starts the QRIS transaction process using the provided context.

## Listeners
The SDK provides listeners for handling various transaction events:

* onTransactionComplete: Triggered when a transaction is successfully completed.
* onTransactionFailed: Triggered when a transaction fails due to an error.
* onTransactionForbidden: Triggered if the transaction is forbidden (e.g., insufficient permissions or invalid tokens).
* onTransactionCanceled: Triggered if the user cancels the transaction.

## Example

```kotlin
class MainActivity : AppCompatActivity(), QrisTransactionListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val configuration = QRConfiguration.Builder(
            authToken = "yourAuthToken",
            sdkToken = "yourSdkToken",
            environment = "UAT",
            isSnap = true
        )
            .setEventListener(this)  // Register this activity as a listener
            .build()

        AstraPayQris.initialize(configuration = configuration)

        binding.buttonQris.setOnClickListener {
            AstraPayQris.getInstance().start(this)
        }
    }

    override fun onTransactionComplete() {
        Toast.makeText(this, "Transaction Complete", Toast.LENGTH_LONG).show()
    }

    override fun onTransactionFailed() {
        Toast.makeText(this, "Transaction Failed", Toast.LENGTH_LONG).show()
    }

    override fun onTransactionForbidden() {
        Toast.makeText(this, "Transaction Forbidden", Toast.LENGTH_LONG).show()
    }

    override fun onTransactionCanceled() {
        Toast.makeText(this, "Transaction Canceled", Toast.LENGTH_LONG).show()
    }

    override fun onTransactionProcessing() {
        Toast.makeText(this, "Transaction Processing", Toast.LENGTH_LONG).show()
    }

    override fun onShowTransactionHistory() {
        Toast.makeText(this, "Showing Transaction History", Toast.LENGTH_LONG).show()
    }
}
```
