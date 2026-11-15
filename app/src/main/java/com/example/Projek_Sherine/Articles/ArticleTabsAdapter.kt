package com.example.Projek_Sherine.Articles

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ArticleTabsAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    // Jumlah total tab yang ada
    override fun getItemCount(): Int = 3

    // Menentukan Fragment yang ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabArtikelFragment()
            1 -> TabResepFragment()
            2 -> TabKebugaranFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}