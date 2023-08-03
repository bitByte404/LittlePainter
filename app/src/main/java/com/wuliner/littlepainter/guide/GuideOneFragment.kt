package com.wuliner.littlepainter.guide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wuliner.littlepainter.R
import com.wuliner.littlepainter.databinding.FragmentGuideFourBinding
import com.wuliner.littlepainter.databinding.FragmentGuideOneBinding
import com.wuliner.littlepainter.utils.AnimUtils


class GuideOneFragment : Fragment() {
    private lateinit var binding: FragmentGuideOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGuideOneBinding.inflate(layoutInflater, container , false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        AnimUtils.addRotateAndAlphaAnimation(binding.tvTitle, onEnd = {})
        AnimUtils.addRotateAndAlphaAnimation(binding.tvDesc, onEnd = {})
    }
}