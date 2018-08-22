package test.android.repository.remote.api

import retrofit2.Call
import retrofit2.http.GET
import test.android.repository.apimodels.MainData
import test.android.repository.apimodels.Property
import test.android.utils.Config

/**
 * Interface containing all the web services.
 *
 * created by fahad on 21/08/2018
 */
interface IApi {

    @GET(Config.API_ENDPOINT_PROPERTIES)
    fun getAllProperties() : Call<MainData>
}