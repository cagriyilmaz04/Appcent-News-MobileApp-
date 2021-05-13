package com.example.gazeteretrofit.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gazeteretrofit.R
import com.example.gazeteretrofit.adapter.Adapter_for_news
import com.example.gazeteretrofit.model.Model
import com.example.gazeteretrofit.model.Model2
import com.example.gazeteretrofit.viewmodel.viewmodelnews
import kotlinx.android.synthetic.main.fragment_favorite_fragments.*
import java.lang.IndexOutOfBoundsException


class FavoriteFragments : Fragment() {
    //private var datas1: MutableLiveData<List<Model>>?=null
   var  newsRecyclerAdapter=Adapter_for_news()
   lateinit var  newsViewModel:viewmodelnews
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_favorite_fragments, container, false)
        val toolbar=view.findViewById<Toolbar>(R.id.toolbar_for_details)
        toolbar.title="Favorites"
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar=view.findViewById<Toolbar>(R.id.toolbar_for_details)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        newsViewModel=ViewModelProvider(this).get(viewmodelnews::class.java)
        newsViewModel.readAllData.observe(viewLifecycleOwner, Observer { datas->
            newsViewModel=ViewModelProvider(this).get(viewmodelnews::class.java)
            newsRecyclerAdapter?.setData(datas)
           try {
                if(datas.size==0){
                    text_error_message.text="There is no favorite"
                }else{
                    text_error_message.visibility=View.GONE
                    recyclerView_favorite.layoutManager=LinearLayoutManager(context?.applicationContext)
                    recyclerView_favorite.adapter=newsRecyclerAdapter
                }

           }catch (e:IndexOutOfBoundsException){

           }

        })
        view.hideKeyboard()
         //  newsViewModel.deleteNews()
    }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}