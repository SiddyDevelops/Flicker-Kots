package com.siddydevelops.flicker_kots

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FlickerImageViewHolder(view: View) : RecyclerView.ViewHolder(view)
{
    var thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    var title: TextView = view.findViewById(R.id.title)
}

class FlickerRecyclerViewAdapter(private var photoList : List<Photo>) : RecyclerView.Adapter<FlickerImageViewHolder>() {
    private val TAG = "FRViewAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickerImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.browse,parent,false)
        return FlickerImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlickerImageViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return if(photoList.isNotEmpty()) photoList.size else 0
    }

    fun downloadData(newPhotos:List<Photo>) {
        photoList = newPhotos
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int): Photo? {
        return if(photoList.isNotEmpty()) photoList[position] else null
    }

}