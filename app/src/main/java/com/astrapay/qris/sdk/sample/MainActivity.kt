package com.astrapay.qris.sdk.sample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.astrapay.qris.sdk.AstraPayQris
import com.astrapay.qris.sdk.AstraPayQris.Companion.getInstance
import com.astrapay.qris.sdk.QRConfiguration
import com.astrapay.qris.sdk.QrisTransactionListener
import com.astrapay.qris.sdk.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), QrisTransactionListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        /**
         * Sets QRIS SDK configuration for authToken, sdkToken, environment, and snap.
         * authToken can be set from B2B2C authToken that can be received after user account binding.
         * sdkToken can be set from the SDK token that is received from the letter of agreement.
         */
        val configuration = QRConfiguration.Builder(
            /**
             * Input user authToken here
             */
            authToken = "",
            /**
             * Input your sdkToken here
             */
            sdkToken = "",
            environment = "UAT",
            isSnap = true
        )
            /**
             * To use the SDK, the activity is required to set itsef
             */
            .setEventListener(this)
            .build()
        AstraPayQris.initialize(configuration = configuration)


        binding.buttonQris.setOnClickListener {
            /**
             * Starts AstraPay QRIS SDK.
             */
            getInstance().start(this)
        }
    }

    /**
     * Called when a QRIS transaction is successfully completed.
     *
     * This method is invoked when the transaction has been successfully processed.
     */
    override fun onTransactionComplete() {
        Toast.makeText(this, "onTransactionComplete", Toast.LENGTH_LONG).show()
    }

    /**
     * Called when a QRIS transaction fails.
     *
     * This method is invoked when the transaction processing fails due to an error during payment.
     */
    override fun onTransactionFailed() {
        Toast.makeText(this, "onTransactionFailed", Toast.LENGTH_LONG).show()
    }

    /**
     * Called when a token is expired.
     *
     * This method is invoked when the SDK detects that token is expired.
     */
    override fun onTransactionForbidden() {
        Toast.makeText(this, "onTransactionForbidden", Toast.LENGTH_LONG).show()
    }

    /**
     * Called when QRIS SDK is closed.
     *
     * This method is invoked when the SDK is closed from the starting screen.
     */
    override fun onTransactionCanceled() {
        Toast.makeText(this, "onTransactionCanceled", Toast.LENGTH_LONG).show()
    }

    /**
     * Called when QRIS transaction status is still processing.
     *
     * This method is invoked when the transaction is pending.
     */
    override fun onTransactionProcessing() {
        Toast.makeText(this, "onTransactionProcessing", Toast.LENGTH_LONG).show()
    }

    /**
     * Called when QRIS transaction status is still processing and user selects show history.
     *
     * This method is invoked when the transaction is pending and user wants to check transaction status.
     */
    override fun onShowTransactionHistory() {
        Toast.makeText(this, "onShowTransactionHistory", Toast.LENGTH_LONG).show()
    }

}