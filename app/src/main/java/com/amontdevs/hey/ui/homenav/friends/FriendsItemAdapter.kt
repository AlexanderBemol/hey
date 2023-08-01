package com.amontdevs.hey.ui.homenav.friends

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amontdevs.hey.R
import com.amontdevs.hey.model.Friend

class FriendsItemAdapter(
    private val friends: List<Friend>,
    private val context: Context
) : RecyclerView.Adapter<FriendsItemAdapter.FriendItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendItemViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.friend_item,
            parent,
            false
        )
        return FriendItemViewHolder(viewLayout)
    }

    override fun getItemCount() = friends.size

    override fun onBindViewHolder(holder: FriendItemViewHolder, position: Int) {
        val currentItem = friends[position]
        holder.photoIV.setImageBitmap(currentItem.bitmap)
        holder.nameTV.text = currentItem.name
        holder.cityTV.text = currentItem.city
        holder.cellTV.text = currentItem.cell
    }

    class FriendItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val photoIV : ImageView = itemView.findViewById(R.id.item_friend_image)
        val nameTV : TextView = itemView.findViewById(R.id.item_friend_text_name)
        val cityTV : TextView = itemView.findViewById(R.id.item_friend_text_state)
        val cellTV : TextView = itemView.findViewById(R.id.item_friend_text_number)

    }
}