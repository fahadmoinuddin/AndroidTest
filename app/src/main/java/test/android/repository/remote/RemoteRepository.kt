package test.android.repository.remote

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import test.android.repository.apimodels.MainData
import test.android.repository.apimodels.Property
import test.android.repository.remote.api.Apis

/**
 * Remote Repository acts as the medium to communicate with web services and returns the
 * results to main Repository.
 *
 * created by fahad on 21/08/2018
 */
class RemoteRepository private constructor(){

    //Singleton instance
    companion object {
        private val remoteRepository = RemoteRepository()

        fun getInstance() = remoteRepository
    }

    //Get all properties from the web service. Calls successHandler if the response is received successfully, else error is thrown.
    fun getAllProperties(successHandler: (List<Property>?) -> Unit, failureHandler: (Throwable?) -> Unit){
        Apis.getApi().getAllProperties().enqueue(object : Callback<MainData>{
            override fun onResponse(call: Call<MainData>?, response: Response<MainData>?) {
                response?.body()?.let{
                    successHandler(it.data.listings)
                }
            }

            override fun onFailure(call: Call<MainData>?, t: Throwable?) {
                failureHandler(t)
            }
        })
    }
}