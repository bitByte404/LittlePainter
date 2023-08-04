package com.wuliner.littlepainter.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wuliner.littlepainter.R

class ContactAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mFriends = emptyList<Friend>()
    private val TYPE_NORMAL = 0
    private val TYPE_TITLE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_NORMAL) { //根据viewType 创建对应的ViewHolder对象
            FriendViewHolder(inflater.inflate(R.layout.layout_qq_item, parent,false))
        } else {
            SectionViewHolder(inflater.inflate(R.layout.layout_qq_item, parent,false))
        }
    }

    override fun getItemCount(): Int {
        return mFriends.size
    }

    override fun getItemViewType(position: Int): Int { //根据数据源配置当前这个item应该是那种类型的view
        val model = mFriends[position]
        return if (model.isFriend) TYPE_NORMAL else TYPE_TITLE
    }

    fun setData(friends: List<Friend>) {
        mFriends = friends
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = mFriends[position]
        if (model.isFriend) {
            (holder as FriendViewHolder).bind(model)
        } else {
            (holder as SectionViewHolder).bind(model)

        }
    }

    class FriendViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: Friend) {
            val iconImageView = itemView.findViewById<ImageView>(R.id.imageView)
            val nameTextView = itemView.findViewById<TextView>(R.id.tv_name)
            iconImageView.setImageResource(model.icon)
            nameTextView.text =  model.name
        }
    }
    class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: Friend) {
            val titleTextView = itemView.findViewById<TextView>(R.id.tv_section_title)
            titleTextView.text = "model.title"
        }
    }

}