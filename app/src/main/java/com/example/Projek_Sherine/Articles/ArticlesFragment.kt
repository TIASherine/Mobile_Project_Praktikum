package com.example.Projek_Sherine.Articles

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.Projek_Sherine.R
import com.example.Projek_Sherine.databinding.FragmentArticlesBinding
import com.google.android.material.chip.Chip

class ArticlesFragment : Fragment() {
    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!

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

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        val context = requireContext()

        val green = ContextCompat.getColor(context, R.color.green)
        val blue = ContextCompat.getColor(context, R.color.blue)
        val lightGreen = ContextCompat.getColor(context, R.color.lightGreen)

        val pageTitles = listOf("Artikel", "Resep", "Kebugaran")
        var currentPage = 0

        fun showPage() {
            binding.txtArtikel.text = pageTitles[currentPage]

            val selectedList = when (currentPage) {
                0 -> ArticlesData.articleList
                1 -> ArticlesData.resepList
                else -> ArticlesData.kebugaranList
            }

            val categories = when (currentPage) {
                0 -> listOf("Semua", "Resep", "Kebugaran", "Istirahat", "Nutrisi")
                1 -> listOf("Semua", "Makanan", "Minuman", "Cemilan")
                else -> listOf("Semua", "Kardio", "Kekuatan", "Fleksibilitas")
            }

            fun displayArticles(category: String) {
                val filteredList = if (category == "Semua") {
                    selectedList
                } else {
                    selectedList.filter { category in it.categories }
                }

                val adapter = ArticlesAdapter(context, filteredList)

                binding.articleContainer.removeAllViews()

                repeat(adapter.count) { index ->
                    binding.articleContainer.addView(
                        adapter.getView(index, null, binding.articleContainer)
                    )
                }
            }

            binding.chipGroupKategori.removeAllViews()

            categories.forEach { category ->
                val chip = Chip(context).apply {
                    id = View.generateViewId()
                    text = category
                    isCheckable = true
                    isCheckedIconVisible = false
                    isCloseIconVisible = false

                    chipBackgroundColor = ColorStateList.valueOf(Color.TRANSPARENT)
                    chipStrokeColor = ColorStateList.valueOf(green)
                    chipStrokeWidth = resources.displayMetrics.density

                    setTextColor(blue)

                    shapeAppearanceModel = shapeAppearanceModel
                        .toBuilder()
                        .setAllCornerSizes(50f)
                        .build()
                }

                binding.chipGroupKategori.addView(chip)
            }

            binding.chipGroupKategori.setOnCheckedStateChangeListener { group, checkedIds ->

                for (i in 0 until group.childCount) {
                    val chip = group.getChildAt(i) as Chip

                    chip.chipBackgroundColor = ColorStateList.valueOf(
                        if (chip.isChecked) lightGreen
                        else Color.TRANSPARENT
                    )
                }

                val selectedCategory = checkedIds.firstOrNull()?.let { id ->
                    group.findViewById<Chip>(id)?.text?.toString()
                } ?: "Semua"

                displayArticles(selectedCategory)
            }

            val firstChip = binding.chipGroupKategori.getChildAt(0) as Chip
            binding.chipGroupKategori.check(firstChip.id)

            binding.btnPrevPage.isEnabled = currentPage > 0
            binding.btnPrevPage.alpha = if (currentPage > 0) 1f else 0f

            binding.btnNextPage.isEnabled = currentPage < 2
            binding.btnNextPage.alpha = if (currentPage < 2) 1f else 0f

            binding.articleScrollView.post {
                binding.articleScrollView.scrollTo(0, 0)
            }

            binding.chipScrollView.post {
                binding.chipScrollView.scrollTo(0, 0)
            }
        }

        // Receive page navigation from HomeFragment
        parentFragmentManager.setFragmentResultListener(
            "open_articles_page",
            viewLifecycleOwner
        ) { _, bundle ->
            currentPage = bundle.getInt("page", 0).coerceIn(0, 2)
            showPage()
        }

        binding.btnNextPage.setOnClickListener {
            if (currentPage < 2) {
                currentPage++
                showPage()
            }
        }

        binding.btnPrevPage.setOnClickListener {
            if (currentPage > 0) {
                currentPage--
                showPage()
            }
        }

        showPage()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
