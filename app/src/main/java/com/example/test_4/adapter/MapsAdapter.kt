package com.example.test_4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test_4.R
import com.example.test_4.data.Place
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions


class MapsAdapter(val deleteListener:OnClicked,val clickListener:OnClicked): RecyclerView.Adapter<MapsAdapter.ViewHolder>() {

    val allMaps = ArrayList<Place>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_map,parent,false)
        val viewHolder = ViewHolder(view)
        viewHolder.deleteButton.setOnClickListener {
            deleteListener.onItemDelete(allMaps[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMap = allMaps[position]
        holder.itemView.setOnClickListener {
            clickListener.onItemClicked(position)
        }
        holder.tvTitleMain.text = currentMap.titleMain
        holder.tvCreator.text = "Creator Name:- ${currentMap.creatorName}"


    }

    override fun getItemCount(): Int {
        return allMaps.size
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTitleMain = itemView.findViewById<TextView>(R.id.tvTitleMain)
        val tvCreator = itemView.findViewById<TextView>(R.id.tvCreatorName)
        val deleteButton = itemView.findViewById<ImageView>(R.id.ivDelete)

    }

    fun updateData(maps:List<Place>) {
        allMaps.clear()
        allMaps.addAll(maps)
        notifyItemInserted(maps.size-1)
        notifyDataSetChanged()
    }


    interface OnClicked {
        fun onItemDelete(maps:Place)

        fun onItemClicked(position: Int)
    }
}