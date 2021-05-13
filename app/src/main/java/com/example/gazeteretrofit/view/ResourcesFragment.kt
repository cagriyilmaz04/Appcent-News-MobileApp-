package com.example.gazeteretrofit.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.gazeteretrofit.R
import kotlinx.android.synthetic.main.fragment_resources.*

class ResourcesFragment : Fragment() {
    var url=""

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      val view=inflater.inflate(R.layout.fragment_resources, container, false)
        val toolbar=view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_resources)
        toolbar.title="News Resources"
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            url=ResourcesFragmentArgs.fromBundle(it).url
        }


        webView.settings.javaScriptEnabled=true
        webView.loadUrl(url)
    }




}