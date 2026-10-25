package com.example.Projek_Sherine.Article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.Projek_Sherine.R
import com.example.Projek_Sherine.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    private val articleList = listOf(
        ArticleModel(
            "Pentingnya Minum Air Putih",
            "Manfaat menjaga tubuh tetap terhidrasi.",
            R.drawable.article1,
            "Minum air putih secara teratur membantu menjaga keseimbangan cairan tubuh dan mendukung aktivitas sehari-hari."
        ),
        ArticleModel(
            "Pola Makan Sehat",
            "Tips menjaga pola makan setiap hari.",
            R.drawable.article2,
            "Mengonsumsi makanan bergizi seimbang membantu tubuh mendapatkan energi dan nutrisi yang dibutuhkan."
        ),
        ArticleModel(
            "Manfaat Olahraga",
            "Pentingnya aktivitas fisik untuk kesehatan.",
            R.drawable.article3,
            "Olahraga secara rutin membantu menjaga kebugaran tubuh dan meningkatkan kesehatan secara keseluruhan."
        ),
        ArticleModel(
            "Istirahat yang Cukup",
            "Tips menjaga kualitas tidur.",
            R.drawable.article4,
            "Tidur yang cukup membantu tubuh beristirahat dan memulihkan energi setelah beraktivitas."
        ),
        ArticleModel(
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
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Article"

        val adapter = ArticleAdapter(requireContext(), articleList)
        binding.listArticleItems.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}