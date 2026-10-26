package com.example.Projek_Sherine.Tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.Projek_Sherine.R

class TutorialFragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. Tampilkan layout fragmentnya
        val view = inflater.inflate(R.layout.fragment_tutorial, container, false)
        return view
    }
}
