package com.example.Projek_Sherine.Bookmark

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import com.example.Projek_Sherine.R
import com.example.Projek_Sherine.databinding.FragmentArticleBinding
import com.google.android.material.chip.Chip

class BookmarkFragment : Fragment() {
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    private val articleList = listOf(
        BookmarkModel(
            "Pentingnya Minum Air Putih",
            "Manfaat menjaga tubuh tetap terhidrasi.",
            R.drawable.article1,
            "Minum air putih secara teratur membantu menjaga keseimbangan cairan tubuh dan mendukung aktivitas sehari-hari."
        ),
        BookmarkModel(
            "Pola Makan Sehat",
            "Tips menjaga pola makan setiap hari.",
            R.drawable.article2,
            "Mengonsumsi makanan bergizi seimbang membantu tubuh mendapatkan energi dan nutrisi yang dibutuhkan."
        ),
        BookmarkModel(
            "Manfaat Olahraga",
            "Pentingnya aktivitas fisik untuk kesehatan.",
            R.drawable.article3,
            "Olahraga secara rutin membantu menjaga kebugaran tubuh dan meningkatkan kesehatan secara keseluruhan."
        ),
        BookmarkModel(
            "Istirahat yang Cukup",
            "Tips menjaga kualitas tidur.",
            R.drawable.article4,
            "Tidur yang cukup membantu tubuh beristirahat dan memulihkan energi setelah beraktivitas."
        ),
        BookmarkModel(
            "Menjaga Kebersihan",
            "Kebiasaan sederhana untuk hidup sehat.",
            R.drawable.article5,
            "Menjaga kebersihan diri dan lingkungan merupakan salah satu langkah penting dalam menjaga kesehatan."
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "- Article -"
        (requireActivity() as AppCompatActivity).supportActionBar?.subtitle =
            android.text.Html.fromHtml(
                "<font color='#114B5F'>Stay </font><font color='#1A936F'>Healthy</font><font color='#114B5F'>, Feel </font><font color='#1A936F'> Happy</font>",
                android.text.Html.FROM_HTML_MODE_COMPACT
            )

        val adapter = BookmarkAdapter(requireContext(), articleList)
        binding.gridArticleItems.adapter = adapter

        binding.chipGroupKategori.check(R.id.chipSemua)

        binding.chipGroupKategori.setOnCheckedStateChangeListener { group, checkedIds ->
            for (i in 0 until group.childCount) {
                val chip = group.getChildAt(i) as Chip

                chip.chipBackgroundColor = ColorStateList.valueOf(
                    if (chip.isChecked)
                        "#88D498".toColorInt()
                    else
                        android.graphics.Color.TRANSPARENT
                )
            }
        }

        binding.chipGroupKategori.clearCheck()
        binding.chipGroupKategori.check(R.id.chipSemua)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}