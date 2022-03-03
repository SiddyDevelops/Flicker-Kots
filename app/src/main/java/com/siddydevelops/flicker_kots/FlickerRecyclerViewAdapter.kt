package com.siddydevelops.flicker_kots

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

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
        if(photoList.isEmpty()) {
            holder.thumbnail.setImageResource(R.drawable.baseline_image_black_48dp)
            holder.title.text = "No photos match your search.\n\nUse the search icon to search for photos."
        } else {
            val photoItem = photoList[position]
            Picasso.get().load(photoItem.image)
                .error(R.drawable.baseline_image_black_48dp)
                .placeholder(R.drawable.baseline_image_black_48dp)
                .into(holder.thumbnail)
            holder.title.text = photoItem.title
        }
    }

    override fun getItemCount(): Int {
        return if(photoList.isNotEmpty()) photoList.size else 1
    }

    fun loadNewData(newPhotos:List<Photo>) {
        photoList = newPhotos
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int): Photo? {
        return if(photoList.isNotEmpty()) photoList[position] else null
    }

}