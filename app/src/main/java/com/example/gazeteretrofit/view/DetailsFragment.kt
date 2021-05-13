package com.example.gazeteretrofit.view
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.gazeteretrofit.R
import com.example.gazeteretrofit.model.Model
import com.example.gazeteretrofit.viewmodel.viewmodelnews
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {
    var image=""
    var title=""
    var author=""
    var Calendar=""
    var detail=""
    var content=""
    var edit_content=""
    var url=""
    var flag=0
    var temp_flag=-1
    lateinit var viewModelNew:viewmodelnews
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_details, container, false)
        viewModelNew=ViewModelProvider(this).get(viewmodelnews::class.java)
        val toolbar=view.findViewById<Toolbar>(R.id.toolbar_detail)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
       (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            image=DetailsFragmentArgs.fromBundle(it).urlToImage
            detail=DetailsFragmentArgs.fromBundle(it).description
            author=DetailsFragmentArgs?.fromBundle(it)?.author
            Calendar=DetailsFragmentArgs.fromBundle(it).calendar
            content=DetailsFragmentArgs.fromBundle(it).content
            url=DetailsFragmentArgs.fromBundle(it).url
            title=DetailsFragmentArgs.fromBundle(it).title
        }
        var custom_calendar=Calendar.substring(0,10)
        var a=edit_to_string(content)
        edit_content=content.substring(0,a)
        if(author.toString()=="null"){
            text_author_detail.text="Unknown"
        }else{
            text_author_detail.text=author
        }
        text_calendar.text=custom_calendar
        text_content.text=edit_content
        text_title_detail.text=detail
        Picasso.get().load(image).into(imageView)
        button_new_resources.setOnClickListener {
            val action=DetailsFragmentDirections.actionDetailsFragmentToResourcesFragment(url)
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_toolbar,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var temp_value=0
            when(item.itemId) {
              R.id.favorite->{
                  var a_news=Model(author,title,detail,url,image,Calendar,content)
                  viewModelNew.readAllData.observe(viewLifecycleOwner, Observer { arr1->
                      if(arr1.size>0) {
                          var flag2 = -1
                          for (i in arr1) {
                              if (i.url.equals(a_news.url) == true) {
                                  flag2 = 0
                                  Toast.makeText(context,"This new was already added to Favorite",Toast.LENGTH_SHORT).show()
                              } else {
                                  flag2 = 1
                              }
                          }
                          if (flag2 == 1) {
                              viewModelNew.addNews(a_news)
                              item.setIcon(R.drawable.choosed_favorite)
                              Toast.makeText(context,"Added to Favorite",Toast.LENGTH_SHORT).show()
                          } else {
                          }
                      }else{
                          viewModelNew.addNews(a_news)
                          item.setIcon(R.drawable.choosed_favorite)
                        Toast.makeText(context,"Added to Favorite",Toast.LENGTH_SHORT).show()
                      }
                  })
                    /* temp_flag= check_the_Same_thing(viewModelNew.readAllData,a_news)!!
                    if(temp_flag==1){
                    }else{
                    }
*/
                    return true
            }
                android.R.id.home->{
                    activity?.onBackPressed()
                    return true
                }
    }
        return super.onOptionsItemSelected(item)
    }
    fun edit_to_string(string:String):Int{
        var count=0
        for(i in string){
            if(i!='['){
                count++;
            }else{
                break
            }
        }
        return count
    }
    


}