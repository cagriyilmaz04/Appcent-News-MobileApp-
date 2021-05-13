package com.example.gazeteretrofit.service

import com.example.gazeteretrofit.model.Model
import com.example.gazeteretrofit.model.Model2
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsAPI {
   var str1:String
   @GET("everything?from=2021-05-08&sortBy=publishedAt&apiKey=cd600064acae4169bf0c1e9f8ce475ab")
   // fun GetData( @Query("q") string: String): Call<Model2>
   fun getData(@Query("q") str: String): Call<Model2>
}


/*@GET("everything?q=turkey&from=2021-05-08&sortBy=publishedAt&apiKey=386fcfeb3a9f4367a07429c2cd0b6dea")
fun GetData(@Query("q") ktor:String): Call<Model2>
 */