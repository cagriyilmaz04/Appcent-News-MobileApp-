package com.example.gazeteretrofit.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.gazeteretrofit.R
import com.example.gazeteretrofit.model.Model
import com.example.gazeteretrofit.view.FavoriteFragmentsDirections
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_row.view.*

class Adapter_for_news:RecyclerView.Adapter<Adapter_for_news.DatasVH>(){
    private var newsList= emptyList<Model>()
    class DatasVH(itemView:View):RecyclerView.ViewHolder(itemView) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatasVH {
       val itemView=LayoutInflater.from(parent?.context).inflate(R.layout.recycler_row,parent,false)
        return DatasVH(itemView)
    }

    override fun onBindViewHolder(holder: DatasVH, position: Int) {

        holder.itemView.text_title1.text=newsList.get(position).title
        holder.itemView.text_description1.text=newsList.get(position).description

        Picasso.get().load(newsList.get(position).urlToImage).into(holder.itemView.image_for_news_fragment1)
        holder.itemView.setOnClickListener {
            val action=FavoriteFragmentsDirections.actionFavoriteFragmentsToDetailsFragment(newsList.get(position).url,
                newsList.get(position).urlToImage,newsList.get(position).publishedAt,newsList.get(position).author,
                newsList.get(position).content,newsList.get(position).title.toString(),newsList.get(position).description.toString())

            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return newsList.size
    }
    fun setData(Data:List<Model>){
        this.newsList=Data
        notifyDataSetChanged()
    }



}