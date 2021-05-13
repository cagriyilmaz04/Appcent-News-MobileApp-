package com.example.gazeteretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.gazeteretrofit.R
import com.example.gazeteretrofit.model.Model
import com.example.gazeteretrofit.view.NewsDirections
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_row.view.*

class Adapter(var array:ArrayList<Model>?): RecyclerView.Adapter<Adapter.ViewVH>() {
    class ViewVH(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewVH {
        val itemView=
            LayoutInflater.from(parent?.context).inflate(R.layout.recycler_row,parent,false)
        return ViewVH(itemView)
    }
    override fun onBindViewHolder(holder: ViewVH, position: Int) {
        holder.itemView.text_title1.text=array?.get(position)?.title
        holder.itemView.text_description1.text=array?.get(position)?.description
        Picasso.get().load(array?.get(position)?.urlToImage).into(holder.itemView.image_for_news_fragment1)
        holder.itemView.setOnClickListener {
            val action=NewsDirections.actionNewsToDetailsFragment(
                array?.get(position)?.url.toString(),array?.get(position)?.urlToImage.toString(),array?.get(position)?.publishedAt.toString(),
                array?.get(position)?.author.toString(),array?.get(position)?.content.toString(),
                    array?.get(position)?.title.toString(), array?.get(position)?.description.toString())
            Navigation.findNavController(it).navigate(action)
        }

    }
    override fun getItemCount(): Int {
        return array!!.size;
    }
    fun refreshData(newNewsList:List<Model>){
            array?.clear()
            array?.addAll(newNewsList)
            notifyDataSetChanged()
    }


}