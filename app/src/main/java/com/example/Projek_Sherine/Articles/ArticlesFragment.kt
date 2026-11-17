package com.example.Projek_Sherine.Articles

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.Projek_Sherine.R
import com.example.Projek_Sherine.data.AppDatabase
import com.example.Projek_Sherine.databinding.FragmentArticlesBinding
import com.google.android.material.tabs.TabLayoutMediator

class ArticlesFragment : Fragment() {
    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase

    private lateinit var adapter: ArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)

        activity.supportActionBar?.apply {
            title = "- Articles -"
            subtitle = android.text.Html.fromHtml(
                "<font color='#114B5F'>Stay </font>" +
                        "<font color='#1A936F'>Healthy</font>" +
                        "<font color='#114B5F'>, Feel </font>" +
                        "<font color='#1A936F'>Happy</font>",
                android.text.Html.FROM_HTML_MODE_COMPACT
            )
        }

        db = AppDatabase.getInstance(requireContext())

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        val tabsAdapter = ArticleTabsAdapter(this)

        binding.viewPagerArticles.adapter = tabsAdapter

        TabLayoutMediator(
            binding.tabLayoutArticles,
            binding.viewPagerArticles
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Artikel"
                    tab.icon = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.open_book
                    )
                }

                1 -> {
                    tab.text = "Resep"
                    tab.icon = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.cookbook
                    )
                }

                2 -> {
                    tab.text = "Kebugaran"
                    tab.icon = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.fitness
                    )
                }
            }
        }.attach()

        binding.AddArticles.setOnClickListener {
            startActivity(
                Intent(requireContext(), ArticlesFormActivity::class.java)
            )
        }
    }

    override fun onDestroyView() {
        binding.viewPagerArticles.adapter = null
        super.onDestroyView()
        _binding = null
    }
}
