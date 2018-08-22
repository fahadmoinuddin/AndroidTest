package test.android.managers

import android.content.Context
import android.net.ConnectivityManager

/**
 * Manager class to provide information about network
 *
 * created by fahad on 21/08/2018
 */
class NetworkManager(val context: Context?) {

    // Returns whether the device is connected to the internet or not
    val isConnected : Boolean
        get() {
            val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected()
        }
}