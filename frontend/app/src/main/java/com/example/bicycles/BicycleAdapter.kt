package com.example.bicycles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bicycles.models.Bicycle

class BicycleAdapter(var bicycleList: ArrayList<Bicycle>) : RecyclerView.Adapter<BicycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v :View = LayoutInflater.from(parent.context).inflate(R.layout.bicycle_list_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(bicycleList[position])
    }

    override fun getItemCount(): Int {
        return bicycleList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(b: Bicycle){
            val txt_brand: TextView = itemView.findViewById(R.id.textViewBrand)
            val txt_model: TextView = itemView.findViewById(R.id.textViewModel)
            txt_brand.text = b.brand
            txt_model.text = b.model
        }
    }

}