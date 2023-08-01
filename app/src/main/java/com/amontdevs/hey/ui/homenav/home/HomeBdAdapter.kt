package com.amontdevs.hey.ui.homenav.home

import android.content.Context
import android.provider.Settings.Global.getString
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

class HomeBdAdapter(
    private val friends: List<Friend>,
    private val context: Context
    )
    : RecyclerView.Adapter<HomeBdAdapter.BdItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BdItemViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.friend_bd_item,
            parent,
            false
        )
        return BdItemViewHolder(viewLayout)
    }

    override fun getItemCount() = friends.size

    override fun onBindViewHolder(holder: BdItemViewHolder, position: Int) {
        val currentItem = friends[position]
        holder.photoIV.setImageBitmap(currentItem.bitmap)
        holder.nameTV.text = currentItem.name
        holder.birthdayTV.text = currentItem.birthday.formattedDate()

        val daysTo = Date().daysTo(currentItem.birthday)
        val daysToString =
            if(daysTo == 0) context.resources.getString(R.string.item_friend_bd_description_today)
            else context.resources.getString(R.string.item_friend_bd_description,daysTo)

        holder.daysToTV.text = daysToString

    }

    class BdItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val photoIV : ImageView = itemView.findViewById(R.id.item_bd_friend_image)
        val nameTV : TextView = itemView.findViewById(R.id.item_bd_friend_name)
        val birthdayTV : TextView = itemView.findViewById(R.id.item_bd_friend_birthday)
        val daysToTV : TextView = itemView.findViewById(R.id.item_bd_friend_description_days)


    }
}

