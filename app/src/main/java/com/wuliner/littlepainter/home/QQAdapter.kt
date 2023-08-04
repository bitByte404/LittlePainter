package com.wuliner.littlepainter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.wuliner.littlepainter.R

/**创建Adapter 管理RecyclerView显示的子视图 item*/
class QQAdapter : RecyclerView.Adapter<QQAdapter.MyViewHolder>() {
    private var mFriends = emptyList<Friend>()

    /**创建一个ViewHolder 确定每个Item的样式*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //解析layout资源
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(layoutInflater.inflate(R.layout.layout_qq_item, parent, false))
    }

    /**配置item的个数*/
    override fun getItemCount(): Int {
       return mFriends.size
    }

    /**绑定数据 告诉每一个ViewHolder该显示什么内容*/
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //取出position对应的数据源 拿给ViewHolder去显示
        holder.bind(mFriends[position])
    }

    /**持有一个View*/
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: Friend) {
            val iconImageView = itemView.findViewById<ImageView>(R.id.imageView)
            val nameTextView = itemView.findViewById<TextView>(R.id.tv_name)
            iconImageView.setImageResource(model.icon)
            nameTextView.text =  model.name
        }
    }
    /**外部设置数据源*/
    fun setData(friend: List<Friend>) {
        mFriends = friend
    }
}