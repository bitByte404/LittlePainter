package com.wuliner.littlepainter.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wuliner.littlepainter.databinding.FragmentGuideBinding
import com.wuliner.littlepainter.utils.SPUtils

class GuideFragment : Fragment() {
    private lateinit var binding: FragmentGuideBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGuideBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //写入数据
        SPUtils.defaultUtils(requireContext()).isFirst = false
    }
}