package com.example.gazeteretrofit.service

import android.view.Display
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.gazeteretrofit.model.Model
@Dao
interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun getNews(news:Model)
    @Query("SELECT * FROM news_table ORDER BY uuid ASC")
     fun insertAll(): LiveData<List<Model>>
    @Query("DELETE FROM news_table")
    suspend fun deleteAllNews()
    @Delete
    suspend fun deleteNew(model:Model)
   /* @Query("SELECT COUNT(*) from news_table WHERE url= :link")
    fun usersCount(link:String) : LiveData<List<Int>>
    */

}
