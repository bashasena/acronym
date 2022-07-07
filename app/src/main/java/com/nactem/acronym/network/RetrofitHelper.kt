package com.nactem.acronym.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class  RetrofitHelper {

    companion object {
       private const val BASE_URL = "http://www.nactem.ac.uk"
        var api: AcronymApi? = null

        init {
            val client = OkHttpClient.Builder().build()

            api = getInstance(BASE_URL,client).create(AcronymApi::class.java)
        }

       private fun getInstance(url: String, client: OkHttpClient) =
           Retrofit.Builder()
               .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
               .baseUrl(url)
               .client(client)
               .build()
    }


}