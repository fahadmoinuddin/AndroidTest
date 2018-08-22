package test.android.ui.propertylist.detail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders

/**
 * Factory class for instantiating view model with custom constructor
 *
 * created by fahad on 21/08/2018
 */
class PropertyDetailFactory(private val itemId: String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PropertyDetailViewModel(itemId) as T
    }
}