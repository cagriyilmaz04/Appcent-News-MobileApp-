package com.example.gazeteretrofit

import androidx.lifecycle.LiveData
import com.example.gazeteretrofit.model.Model
import com.example.gazeteretrofit.service.NewsDAO


class UserRepository(private val userDao: NewsDAO) {
 val readAllData: LiveData<List<Model>> = userDao.insertAll()
    suspend fun addUser(user: Model){
        userDao.getNews(user)
    }
  /*  suspend fun compareLink(link:String){
        userDao.usersCount(link)
    }
   */
    suspend fun deleteAllThings(){
        userDao.deleteAllNews()
    }
    suspend fun deleteOnlyOne(new:Model){
        userDao.deleteNew(new)
    }

}