package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build

fun Context.getStatusConnection(isConnected: (Boolean) -> Unit) {

    val networkRequest = NetworkRequest.Builder().apply {
        addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            addTransportType(NetworkCapabilities.TRANSPORT_WIFI_AWARE)
        }
    }.build()

    (getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.apply {
        registerNetworkCallback(networkRequest,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    isConnected(true)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    isConnected(false)
                }
            })
    } ?: isConnected(false)
}