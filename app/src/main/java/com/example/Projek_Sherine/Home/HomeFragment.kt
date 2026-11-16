
package com.example.Projek_Sherine.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Projek_Sherine.Articles.ArticlesAdapter
import com.example.Projek_Sherine.Articles.ArticlesData
import com.example.Projek_Sherine.R
import com.example.Projek_Sherine.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeArticlesAdapter = ArticlesAdapter(
            requireContext(),
            ArticlesData.articleList.take(2)
        )

        binding.recyclerHomeArticles.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeArticlesAdapter
            isNestedScrollingEnabled = false
        }

        binding.btnArticlesDetail.setOnClickListener {
            openArticlesPage(0)
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

    override fun onDestroyView() {
        binding.recyclerHomeArticles.adapter = null
        super.onDestroyView()
        _binding = null
    }
}
