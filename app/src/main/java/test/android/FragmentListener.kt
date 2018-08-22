package test.android

/**
 * Main Interface for callback from activity to fragment. This interface contains methods to toast and other common actions.
 *
 * created by fahad on 21/08/2018
 */
interface FragmentListener {
    fun showToastInActivity(message: String)  // display a toast with message
}