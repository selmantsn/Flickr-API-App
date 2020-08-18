package com.e.flickrapi.ui.viewmodel

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.e.flickrapi.models.BaseResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.flickrapi.api.RetrofitClient
import com.e.flickrapi.flicker_api_key
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosViewModel : ViewModel() {

    var page = 1
    fun getPhotos(): MutableLiveData<BaseResponse> {

        val photoResponse: MutableLiveData<BaseResponse> by lazy { MutableLiveData<BaseResponse>() }

        var retrofitInterface = RetrofitClient.create()
        val call = retrofitInterface.getPhotos(
            method = "flickr.photos.getRecent",
            api_key = flicker_api_key,
            extras = "last_update",
            per_page = 20,
            page = page,
            format = "json",
            nojsoncallback = 1
        )

        call.enqueue(object : Callback<BaseResponse?> {
            override fun onResponse(
                call: Call<BaseResponse?>,
                response: Response<BaseResponse?>
            ) {
                var body = response.body()

                if (body != null) {
                    if (body.photos.photo.isNotEmpty()){
                        page++
                        photoResponse.value = body
                    }else{
                        photoResponse.value = null
                    }

                }
            }


            override fun onFailure(call: Call<BaseResponse?>, t: Throwable) {
                photoResponse.value = null
                Log.i("TAG", "onFailure: "+ t.message)
            }
        })

        return photoResponse
    }


}