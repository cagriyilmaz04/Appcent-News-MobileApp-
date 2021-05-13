package com.example.gazeteretrofit.view

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gazeteretrofit.R
import com.example.gazeteretrofit.adapter.Adapter
import com.example.gazeteretrofit.model.Model
import com.example.gazeteretrofit.viewmodel.viewmodelnews
import kotlinx.android.synthetic.main.fragment_news.*


class News : Fragment(),SearchView.OnQueryTextListener {
    var string="besiktas"
    var ax:List<Model>?=null
    private val recyclerBesinAdapter = Adapter(arrayListOf())
    lateinit var viewModels:viewmodelnews
    lateinit var sharedPref:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_news, container, false)
        val toolbar=view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title="News"
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        return view
    }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)!!
            super.onViewCreated(view, savedInstanceState)

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.custom_toolbar_for_news,menu)
        val item=menu.findItem(R.id.search_view)
        val searchView=item.actionView as SearchView
       searchView.onActionViewExpanded()
        searchView.isVisible=true
        searchView.setIconifiedByDefault(true)
        searchView.viewTreeObserver.addOnDrawListener {
           searchView.findViewById<AppCompatImageView>(androidx.appcompat.R.id.search_button).visibility = View.GONE
            searchView.findViewById<AppCompatImageView>(androidx.appcompat.R.id.search_mag_icon).visibility = View.GONE
        }
        searchView.setFocusable(true);
    //    searchView.requestFocusFromTouch();
        searchView.requestFocus()
        searchView.setQuery(sharedPref.getString("key",""),false)
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun changeData(){
        viewModels.news1.observe(viewLifecycleOwner, Observer { besinler->
                besinler?.let {
                    recyclerBesinAdapter.refreshData(it)
                }
        })
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModels=ViewModelProvider(this).get(viewmodelnews::class.java)
        viewModels.getdatas(query!!)
        changeData()
        recycler1.layoutManager=LinearLayoutManager(context)
        recycler1.adapter=recyclerBesinAdapter
        //changeData()
        sharedPref.edit().putString("key",query.toString()).apply()
        return false
    }
    override fun onQueryTextChange(newText: String?): Boolean {

        return true
    }

}