package com.wuliner.littlepainter.guide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wuliner.littlepainter.R
import com.wuliner.littlepainter.databinding.FragmentGuideFourBinding
import com.wuliner.littlepainter.utils.AnimUtils
import com.wuliner.littlepainter.utils.SPUtils


class GuideFourFragment : Fragment() {
    private lateinit var binding: FragmentGuideFourBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGuideFourBinding.inflate(layoutInflater, container , false)

        binding.btExperience.setOnClickListener {
            //改变idFirst的值
            SPUtils.defaultUtils(requireContext()).isFirst = false
            //切换到主页
            findNavController().navigate(R.id.action_guideFragment_to_homeFragment)
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        AnimUtils.addRotateAndAlphaAnimation(binding.tvTitle, onEnd = {})
        AnimUtils.addRotateAndAlphaAnimation(binding.tvDesc, onEnd = {})
    }
}