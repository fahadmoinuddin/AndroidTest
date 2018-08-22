package test.android.repository.apimodels

import android.databinding.BindingAdapter
import android.media.Image
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.SerializedName
import test.android.utils.Config
import test.android.R

/**
 * POJO for storing the json object from web service
 *
 * created by fahad on 21/08/2018
 */
data class MainData(@SerializedName(Config.API_KEY_DATA) val data: Data)

data class Data(@SerializedName(Config.API_KEY_LISTINGS) val listings: ArrayList<Property>)

data class Property(@SerializedName(Config.API_KEY_ID) val id: String,
                    @SerializedName(Config.API_KEY_AREA) val area: String,
                    @SerializedName(Config.API_KEY_BEDROOMS) val bedrooms: Int,
                    @SerializedName(Config.API_KEY_BATHROOMS) val bathrooms: Int,
                    @SerializedName(Config.API_KEY_CARSPACES) val carspaces: Int,
                    @SerializedName(Config.API_KEY_DESCRIPTION) val description: String,
                    @SerializedName(Config.API_KEY_PRICE) val price: String,
                    @SerializedName(Config.API_KEY_CURRENCY) val currency: String,
                    @SerializedName(Config.API_KEY_OWNER) val owner: Owner,
                    @SerializedName(Config.API_KEY_IMAGES) val images: ArrayList<String>,
                    @SerializedName(Config.API_KEY_LOCATION) val location: Location,
                    @SerializedName(Config.API_KEY_SUBURB) val suburb: String,
                    @SerializedName(Config.API_KEY_IS_PREMIUM) val isPremium: Int){

    object ImageViewBindingAdapter {
        @BindingAdapter("bind:avatarUrl")
        @JvmStatic
        fun loadAvatarImage(image: ImageView, url: String) {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_person)
            requestOptions.error(R.drawable.ic_person)
            requestOptions.fallback(R.drawable.ic_person)
            Glide.with(image.context)
                    .load(url)
                    .apply(requestOptions)
                    .into(image)
        }

        @BindingAdapter("bind:imageUrl")
        @JvmStatic
        fun loadPropertyImage(image: ImageView, url: String) {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_default_image)
            requestOptions.error(R.drawable.ic_default_image)
            requestOptions.fallback(R.drawable.ic_default_image)
            Glide.with(image.context)
                    .load(url)
                    .apply(requestOptions)
                    .into(image)
        }
    }

    fun getPropertyImage() = images.get(images.size - 1)

    fun getAddress() = "${location.address1}, ${location.address2}, ${location.suburb}, ${location.state}"
}

data class Owner(@SerializedName(Config.API_KEY_OWNER_NAME) val name: String, @SerializedName(Config.API_KEY_OWNER_LAST_NAME) val lastName: String, @SerializedName(Config.API_KEY_OWNER_IMAGE) val image: OwnerImage)

data class OwnerImage(@SerializedName(Config.API_KEY_OWNER_IMAGE_MEDIUM) val medium: OwnerImageUrl)

data class OwnerImageUrl(@SerializedName(Config.API_KEY_OWNER_IMAGE_URL) val url: String)

data class Location(@SerializedName(Config.API_KEY_ADDRESS_1) val address1: String, @SerializedName(Config.API_KEY_ADDRESS_2) val address2: String, @SerializedName(Config.API_KEY_SUBURB) val suburb: String, @SerializedName(Config.API_KEY_STATE) val state: String)