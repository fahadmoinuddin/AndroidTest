package test.android.ui.propertylist.detail

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import test.android.utils.Config

/**
 * View model class for Property Detail fragment.
 *
 * created by fahad on 21/08/2018
 */
class PropertyDetailViewModel(selectedItemId: String) : ViewModel() {

    //Observable field to display the selected item id in textview;
    val selectedItem = ObservableField<String>()

    init {
        //assign the value from the text passed in constructor
        selectedItem.set(if (selectedItemId.isEmpty()) Config.NOTHING_SELECTED else selectedItemId)
    }

}