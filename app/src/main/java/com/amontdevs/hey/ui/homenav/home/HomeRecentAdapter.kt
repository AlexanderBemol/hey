package com.amontdevs.hey.ui.homenav.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amontdevs.hey.R
import com.amontdevs.hey.model.Friend
import com.amontdevs.hey.utils.daysTo
import com.amontdevs.hey.utils.formattedDate
import java.util.Date

class HomeRecentAdapter(
    private val friends: List<Friend>,
    private val context: Context
) : RecyclerView.Adapter<HomeRecentAdapter.RecentItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentItemViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.friend_call_item,
            parent,
            false
        )
        return RecentItemViewHolder(viewLayout)
    }

    override fun getItemCount() = friends.size

    override fun onBindViewHolder(holder: RecentItemViewHolder, position: Int) {
        val currentItem = friends[position]
        holder.photoIV.setImageBitmap(currentItem.bitmap)
        holder.nameTV.text = currentItem.name
        holder.datetimeTV.text = context.resources.getString(R.string.sample_friend_call_datetime)
    }

    class RecentItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val photoIV : ImageView = itemView.findViewById(R.id.item_call_friend_image)
        val nameTV : TextView = itemView.findViewById(R.id.item_friend_call_name)
        val datetimeTV : TextView = itemView.findViewById(R.id.item_friend_call_datetime)


    }
}
