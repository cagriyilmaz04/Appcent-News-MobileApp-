package com.example.gazeteretrofit.viewmodel

import android.app.Application
import android.util.Log
import android.view.Display
import android.widget.Toast
import androidx.lifecycle.*
import com.example.gazeteretrofit.UserRepository
import com.example.gazeteretrofit.model.Model
import com.example.gazeteretrofit.model.Model2
import com.example.gazeteretrofit.service.NewsAPI
import com.example.gazeteretrofit.service.NewsAPIService
import com.example.gazeteretrofit.service.NewsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class viewmodelnews(application: Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<Model>>
    private val repository:UserRepository
    var arrayList1=ArrayList<Model>()
    init{
        val userDao=NewsDatabase.getDatabase(application).newsDao()

        repository= UserRepository(userDao)
        readAllData=repository.readAllData
    }
    fun addNews(news:Model){
            viewModelScope.launch(Dispatchers.IO) {
                repository.addUser(news)
            }
    }
    /*fun check_Link(link:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.compareLink(link)
        }
    }
     */
    fun deleteNew(new:Model){
            viewModelScope.launch(Dispatchers.IO) {
                repository.deleteOnlyOne(new)
            }
    }
    fun deleteNews(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllThings()
        }
    }
    var str1=""
    var job= Job()
    val BASE_URL="https://newsapi.org/v2/"
    var news1= MutableLiveData<List<Model>>()
    var favorite_news=MutableLiveData<List<Model>>()
    val temp=NewsAPIService()
    fun getdatas(a:String){
       var b=temp.getData(a)
       b.enqueue(object :Callback<Model2>{
           override fun onResponse(call: Call<Model2>, response: Response<Model2>) {
              try {
                  Log.e("Yii",response.body()?.articles?.get(0)?.title.toString())
                  news1.value=response?.body()?.articles
              }catch (e:Exception){

              }


           }
           override fun onFailure(call: Call<Model2>, t: Throwable) {
               Log.e("Mistaka","Error")
           }
       })
    }
    fun addToFavorite(one_new:Model){
        arrayList1.add(one_new)
    }
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


}