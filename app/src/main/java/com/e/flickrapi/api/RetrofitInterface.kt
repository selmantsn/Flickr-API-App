package com.e.flickrapi.api

import com.e.flickrapi.models.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("/services/rest/")
    fun getPhotos(
        @Query("method") method: String,
        @Query("api_key") api_key: String,
        @Query("extras") extras: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int,
        @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: Int
    ): Call<BaseResponse?>

}