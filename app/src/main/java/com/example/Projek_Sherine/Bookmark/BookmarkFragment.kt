package com.example.Projek_Sherine.Bookmark

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Projek_Sherine.Articles.ArticlesAdapter
import com.example.Projek_Sherine.Articles.ArticlesData
import com.example.Projek_Sherine.R
import com.example.Projek_Sherine.databinding.FragmentBookmarkBinding
import com.google.android.material.chip.Chip

class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private lateinit var articlesAdapter: ArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)

        activity.supportActionBar?.apply {
            title = "- Bookmark -"
            subtitle = android.text.Html.fromHtml(
                "<font color='#114B5F'>Stay </font>" +
                        "<font color='#1A936F'>Healthy</font>" +
                        "<font color='#114B5F'>, Feel </font>" +
                        "<font color='#1A936F'>Happy</font>",
                android.text.Html.FROM_HTML_MODE_COMPACT
            )
        }

        // RecyclerView dengan adapter yang sama
        articlesAdapter = ArticlesAdapter(
            requireContext(),
            emptyList()
        )

        binding.recyclerBookmarkItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = articlesAdapter
        }

        // Filter kategori Bookmark
        binding.chipGroupKategori.setOnCheckedStateChangeListener { group, _ ->

            for (i in 0 until group.childCount) {
                val chip = group.getChildAt(i) as Chip

                chip.chipBackgroundColor = ColorStateList.valueOf(
                    if (chip.isChecked) "#88D498".toColorInt()
                    else "#EAF5EE".toColorInt()
                )

                chip.isCloseIconVisible =
                    chip.isChecked && chip.id != R.id.chipSemua

                chip.setOnCloseIconClickListener {
                    binding.chipGroupKategori.check(R.id.chipSemua)
                }
            }

            displayBookmarks()
        }

        binding.chipGroupKategori.check(R.id.chipSemua)
        displayBookmarks()
    }

    private fun displayBookmarks() {
        val prefs = requireContext().getSharedPreferences(
            "article_bookmarks",
            Context.MODE_PRIVATE
        )

        val allArticles = (
                ArticlesData.articleList +
                        ArticlesData.resepList +
                        ArticlesData.kebugaranList
                ).distinctBy { it.url }

        // Tiga artikel contoh untuk demonstrasi
        val defaultTitles = setOf(
            "Resep Salad Sehat dan Praktis",
            "Jus Mangga Yogurt",
            "7 Gerakan Yoga untuk Pemula"
        )

        val savedArticles = allArticles.filter { article ->
            article.title in defaultTitles ||
                    prefs.getBoolean(article.url, false)
        }

        // Filtering menurut kelompok konten
        val filteredArticles = when (
            binding.chipGroupKategori.checkedChipId
        ) {
            R.id.chipArtikel -> savedArticles.filter {
                it in ArticlesData.articleList
            }

            R.id.chipResep -> savedArticles.filter {
                it in ArticlesData.resepList
            }

            R.id.chipKebugaran -> savedArticles.filter {
                it in ArticlesData.kebugaranList
            }

            else -> savedArticles
        }

        articlesAdapter.updateArticles(filteredArticles)
    }

    override fun onResume() {
        super.onResume()

        if (_binding != null && ::articlesAdapter.isInitialized) {
            displayBookmarks()
        }
    }

    override fun onDestroyView() {
        binding.recyclerBookmarkItems.adapter = null
        super.onDestroyView()
        _binding = null
    }
}
