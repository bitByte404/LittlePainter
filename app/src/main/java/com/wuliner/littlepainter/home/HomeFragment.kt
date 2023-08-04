package com.wuliner.littlepainter.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        /**配置适配器*/
        val mAdapter = QQAdapter()
        recyclerView.adapter = mAdapter

        /**配置数据源*/
        mAdapter.setData(listOf(
            Friend(R.drawable.icon1,"Jack",""),
            Friend(R.drawable.icon2,"Rose",""),
            Friend(R.drawable.icon3,"Merry",""),
            Friend(R.drawable.icon1,"Peter",""),
            Friend(R.drawable.icon2,"Lily",""),
            Friend(R.drawable.icon3,"John",""),
            Friend(R.drawable.icon1,"Jack",""),
            Friend(R.drawable.icon2,"Rose",""),
            Friend(R.drawable.icon3,"Merry",""),
            Friend(R.drawable.icon1,"Peter",""),
            Friend(R.drawable.icon2,"Lily",""),
            Friend(R.drawable.icon3,"John","")
        ))
    }
}