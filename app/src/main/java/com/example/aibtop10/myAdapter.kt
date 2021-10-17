package com.example.aibtop10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class myAdapter (private val result: MutableList<TopApp>?) : RecyclerView.Adapter<myAdapter.ItemViewHolder>(){
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row
                ,parent
                ,false
            )
        )

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val name =result!! [position]

        holder.itemView.apply{
            tvResult.text= name.title


        }

    }

    override fun getItemCount() : Int = result!!.size

}

