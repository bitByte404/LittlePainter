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
        //配置ViewPager2的adapter
        binding.viewPager.adapter = GuideAdapter(
            listOf(GuideOneFragment(), GuideTwoFragment(), GuideThreeFragment(), GuideFourFragment()),
            parentFragmentManager,
            lifecycle
        )
    }
}