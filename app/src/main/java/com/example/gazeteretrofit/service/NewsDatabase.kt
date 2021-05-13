package com.example.gazeteretrofit.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gazeteretrofit.model.Model
import com.example.gazeteretrofit.view.News

@Database(entities = arrayOf(Model::class),version =1,exportSchema = false)
    abstract class NewsDatabase:RoomDatabase() {
    abstract fun newsDao():NewsDAO
    companion object{
   @Volatile
   private var instance:NewsDatabase?=null
        private var INSTANCE: NewsDatabase? = null
        fun getDatabase(context: Context): NewsDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
}


}