package com.example.gazeteretrofit.service

import android.view.Display
import com.example.gazeteretrofit.model.Model
import com.example.gazeteretrofit.model.Model2
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIService {

    val BASE_URL="https://newsapi.org/v2/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsAPI::class.java)

        fun getData(str:String): Call<Model2>{
            return api.getData(str)
        }
}