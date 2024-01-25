package com.practicaltask.shruutest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicaltask.shruutest.data.model.ProductsItem

import com.practicaltask.shruutest.databinding.ItemPostBinding

class PostAdapter(private var postModelList: List<ProductsItem>)
    : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private lateinit var binding:ItemPostBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context),
        parent,false)
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        binding.title.text=postModelList[position].title
        binding.tasks.text=postModelList[position].description
    }

    override fun getItemCount(): Int = postModelList.size

    class PostViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    }

    fun setData(postModelList: List<ProductsItem?>?)
    {
        this.postModelList= postModelList as List<ProductsItem>
        notifyDataSetChanged()
    }

}