package test.android.repository

import test.android.repository.apimodels.Property
import test.android.repository.remote.RemoteRepository

/**
 * Repository acts as a medium between view model and data.
 * Communicates with Remote repository to get the data.
 *
 * created by fahad on 21/08/2018
 */
class Repository private constructor(val remoteRepository: RemoteRepository?){

    //Singleton instance
    companion object {
        private val repository = Repository(RemoteRepository.getInstance())

        fun getInstance() = repository
    }

    //This function is called to get all properties from the URL. Based on the response, either successHandler or failureHandler is called.
    fun getAllProperties(successHandler: (List<Property>?) -> Unit, failureHandler: (Throwable?) -> Unit){
        remoteRepository?.getAllProperties(successHandler, failureHandler)
    }

}