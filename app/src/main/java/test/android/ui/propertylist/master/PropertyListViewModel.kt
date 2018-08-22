package test.android.ui.propertylist.master

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import test.android.repository.Repository
import test.android.repository.apimodels.Property

/**
 * View model class for Property List fragment.
 *
 * created by fahad on 21/08/2018
 */
class PropertyListViewModel : ViewModel() {

    //Repository instance. Intermediate between View Model and data
    val repository = Repository.getInstance()

    //Live data objects which are listened in the fragment to manipulate the view.
    val apiError = MutableLiveData<Throwable>()
    val propertyList = MutableLiveData<List<Property>>()

    //Observable fields for data binding with layout
    val loadingProperties = ObservableField<Boolean>(false)
    val propertiesAvailable = ObservableField<Boolean>(false)

    //Function to get all properties from the repository
    fun getAllProperties(){
        loadingProperties.set(true)
        repository.getAllProperties({
            loadingProperties.set(false)
            propertyList.value = it
            propertiesAvailable.set(true)
        }, {
            loadingProperties.set(false)
            apiError.value = it
        })
    }
}