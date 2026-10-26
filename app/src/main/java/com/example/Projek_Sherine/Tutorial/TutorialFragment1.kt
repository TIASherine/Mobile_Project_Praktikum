package com.example.Projek_Sherine.Tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.Projek_Sherine.databinding.FragmentTutorialBinding

class TutorialFragment1 : Fragment() {
    private var _binding: FragmentTutorialBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTutorialBinding.inflate(inflater, container, false)
        return binding.root
    }
}
