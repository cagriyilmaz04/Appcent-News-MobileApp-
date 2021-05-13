package com.example.gazeteretrofit.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName="news_table")
data class Model(
                @ColumnInfo(name="author")
                @SerializedName("author")
                 val author:String,
                @ColumnInfo(name="title")
                 @SerializedName("title")
                 val title:String,
                @ColumnInfo(name="description")
                 @SerializedName("description")
                 val description:String,
                @ColumnInfo(name="url")
                 @SerializedName("url")
                 val url:String,
                @ColumnInfo(name="urlToImage")
                 @SerializedName("urlToImage")
                 val urlToImage:String,
                @ColumnInfo(name="publishedAt")
                 @SerializedName("publishedAt")
                 val publishedAt:String,
                @ColumnInfo(name="content")
                 @SerializedName("content")
                val content:String) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0;

}