package com.example.gazeteretrofit.model

import com.google.gson.annotations.SerializedName

class Model2( @SerializedName("status")
              val status:String,
              @SerializedName("totalResults")
              val totalResults:Int,
              @SerializedName("articles")
              var articles:List<Model>) {
}