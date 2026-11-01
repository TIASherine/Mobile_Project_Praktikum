package com.example.Projek_Sherine.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.Projek_Sherine.ArticleWebViewActivity
import com.example.Projek_Sherine.Articles.ArticlesData
import com.example.Projek_Sherine.R
import com.example.Projek_Sherine.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "- Beranda -"
        (requireActivity() as AppCompatActivity).supportActionBar?.subtitle =
            android.text.Html.fromHtml(
                "<font color='#114B5F'>Stay </font><font color='#1A936F'>Healthy</font><font color='#114B5F'>, Feel </font><font color='#1A936F'> Happy</font>",
                android.text.Html.FROM_HTML_MODE_COMPACT
            )

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        val displayUser = sharedPref.getString("username", "Sherine")
        binding.txtWelcome.text = "Apa kabar, $displayUser?"

        Glide.with(this)
            .load(R.drawable.article1)
            .centerCrop()
            .override(150, 150)
            .into(binding.imgArtikel1)

        Glide.with(this)
            .load(R.drawable.article2)
            .centerCrop()
            .override(150, 150)
            .into(binding.imgArtikel2)

        binding.btnArticlesDetail.setOnClickListener {
            openArticlesPage(0)
        }

        val bookmarks = listOf(
            binding.btnBookmark,
            binding.btnBookmark2
        )


        val homeArticleTitles = listOf(
            "Resep Salad Sehat dan Praktis",
            "Olahraga & Aktivitas Fisik"
        )

        val homeArticles = homeArticleTitles.mapNotNull { title ->
            ArticlesData.articleList.find { it.title == title }
        }

        val bookmarkPrefs = requireContext().getSharedPreferences(
            "article_bookmarks",
            MODE_PRIVATE
        )

        val bookmarkButtons = listOf(
            binding.btnBookmark,
            binding.btnBookmark2
        )

        bookmarkButtons.forEachIndexed { index, button ->
            val article = homeArticles.getOrNull(index) ?: return@forEachIndexed
            val bookmarkKey = article.title

            fun updateIcon() {
                val isSaved = bookmarkPrefs.getBoolean(bookmarkKey, false)

                button.setImageResource(
                    if (isSaved) R.drawable.bookmark_filled
                    else R.drawable.bookmark
                )
            }

            updateIcon()

            button.setOnClickListener {
                val newState = !bookmarkPrefs.getBoolean(bookmarkKey, false)

                bookmarkPrefs.edit()
                    .putBoolean(bookmarkKey, newState)
                    .apply()

                updateIcon()
            }
        }


        val detailButtons = listOf(
            binding.btnArticle1Detail,
            binding.btnArticle2Detail
        )

        detailButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                val article = homeArticles.getOrNull(index)
                    ?: return@setOnClickListener

                val intent = Intent(
                    requireContext(),
                    ArticleWebViewActivity::class.java
                ).apply {
                    putExtra("ARTICLE_URL", article.url)
                    putExtra("ARTICLE_TITLE", article.title)
                }

                startActivity(intent)
            }
        }

        binding.btnArticle3Detail.setOnClickListener {
            openArticlesPage(1)
        }

        binding.btnArticle4Detail.setOnClickListener {
            openArticlesPage(2)
        }
    }

    private fun openArticlesPage(page: Int) {
        parentFragmentManager.setFragmentResult(
            "open_articles_page",
            bundleOf("page" to page)
        )

        requireActivity()
            .findViewById<BottomNavigationView>(R.id.bottom_nav_view)
            .selectedItemId = R.id.action_articles
    }
}