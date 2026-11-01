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
    private val articleList = ArticlesData.articleList
    private val resepList = ArticlesData.resepList
    private val kebugaranList = ArticlesData.kebugaranList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "- Articles -"
        (requireActivity() as AppCompatActivity).supportActionBar?.subtitle =
            android.text.Html.fromHtml(
                "<font color='#114B5F'>Stay </font><font color='#1A936F'>Healthy</font><font color='#114B5F'>, Feel </font><font color='#1A936F'> Happy</font>",
                android.text.Html.FROM_HTML_MODE_COMPACT
            )


        val pageTitles = listOf("Artikel", "Resep", "Kebugaran")
        var currentPage = 0

        fun showPage() {
            binding.txtArtikel.text = pageTitles[currentPage]

            val selectedList = when (currentPage) {
                0 -> articleList
                1 -> resepList
                2 -> kebugaranList
                else -> articleList
            }

            val categories = when (currentPage) {
                0 -> listOf("Semua", "Resep", "Kebugaran", "Istirahat", "Nutrisi")
                1 -> listOf("Semua", "Makanan", "Minuman", "Cemilan")
                2 -> listOf("Semua", "Kardio", "Kekuatan", "Fleksibilitas")
                else -> listOf("Semua")
            }

            fun displayArticles(category: String) {
                val filteredList = if (category == "Semua") {
                    selectedList
                } else {
                    selectedList.filter { category in it.categories }
                }

                val adapter = ArticlesAdapter(requireContext(), filteredList)

                binding.articleContainer.removeAllViews()

                for (i in 0 until adapter.count) {
                    val itemView = adapter.getView(
                        i,
                        null,
                        binding.articleContainer
                    )
                    binding.articleContainer.addView(itemView)
                }
            }

            binding.chipGroupKategori.removeAllViews()

            categories.forEach { category ->
                val chip = Chip(requireContext()).apply {
                    id = View.generateViewId()
                    text = category
                    isCheckable = true
                    isCheckedIconVisible = false
                    isCloseIconVisible = false

                    chipBackgroundColor = ColorStateList.valueOf(Color.TRANSPARENT)
                    chipStrokeColor = ColorStateList.valueOf(
                        ContextCompat.getColor(requireContext(), R.color.green)
                    )
                    chipStrokeWidth = resources.displayMetrics.density

                    setTextColor(
                        ContextCompat.getColor(requireContext(), R.color.blue)
                    )

                    shapeAppearanceModel = shapeAppearanceModel
                        .toBuilder()
                        .setAllCornerSizes(50f)
                        .build()
                }

                binding.chipGroupKategori.addView(chip)
            }

            parentFragmentManager.setFragmentResultListener(
                "open_articles_page",
                viewLifecycleOwner
            ) { _, bundle ->
                currentPage = bundle.getInt("page", 0)
                showPage()
            }

            binding.chipGroupKategori.setOnCheckedStateChangeListener { group, checkedIds ->

                for (i in 0 until group.childCount) {
                    val chip = group.getChildAt(i) as Chip

                    chip.chipBackgroundColor = ColorStateList.valueOf(
                        if (chip.isChecked)
                            ContextCompat.getColor(requireContext(), R.color.lightGreen)
                        else
                            Color.TRANSPARENT
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

            binding.articleScrollView.scrollTo(0, 0)
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