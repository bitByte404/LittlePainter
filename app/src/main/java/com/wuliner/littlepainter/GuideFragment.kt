package com.wuliner.littlepainter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wuliner.littlepainter.databinding.FragmentDrawBinding


class GuideFragment : Fragment() {
    private lateinit var binding: FragmentDrawBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDrawBinding.inflate(inflater, container, false)
        return binding.root
    }

}