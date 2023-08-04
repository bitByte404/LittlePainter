package com.wuliner.littlepainter.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.wuliner.littlepainter.R


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**配置RecyclerView*/
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        /**配置布局方式*/
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL, //HORIZONTAL
            false
        )

        //recyclerView.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        /**配置适配器*/
        val mAdapter = ContactAdapter()
        recyclerView.adapter = mAdapter

        /**按页显示*/
        //PagerSnapHelper().attachToRecyclerView(recyclerView)

        /**配置数据源*/
        mAdapter.setData(listOf(
            Friend(isFriend = false, title = "同班同学"),
            Friend(R.drawable.icon1,"Jack",""),
            Friend(R.drawable.icon2,"Rose",""),
            Friend(R.drawable.icon3,"Merry",""),
            Friend(R.drawable.icon1,"Peter",""),
            Friend(R.drawable.icon2,"Lily",""),
            Friend(R.drawable.icon3,"John",""),
            Friend(R.drawable.icon1,"Jack",""),
            Friend(R.drawable.icon2,"Rose",""),
            Friend(isFriend = false, title = "大学好友"),
            Friend(R.drawable.icon3,"Merry",""),
            Friend(R.drawable.icon1,"Peter",""),
            Friend(R.drawable.icon2,"Lily",""),
            Friend(R.drawable.icon3,"John","")
        ))
    }
}