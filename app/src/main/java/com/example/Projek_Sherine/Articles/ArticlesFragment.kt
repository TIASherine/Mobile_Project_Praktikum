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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Projek_Sherine.R
import com.example.Projek_Sherine.databinding.FragmentArticlesBinding
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout

class ArticlesFragment : Fragment() {

    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!

    private lateinit var articlesAdapter: ArticlesAdapter

    private val pageTitles = listOf(
        "Artikel",
        "Resep",
        "Kebugaran"
    )

    private var currentPage = 0
    private var selectedList: List<ArticlesModel> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticlesBinding.inflate(
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

        // Menyiapkan RecyclerView
        articlesAdapter = ArticlesAdapter(
            requireContext(),
            emptyList()
        )

        binding.recyclerArticles.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = articlesAdapter
        }

        // Membuat tiga Tab
        pageTitles.forEach { title ->
            binding.tabLayoutArticles.addTab(
                binding.tabLayoutArticles.newTab().setText(title)
            )
        }

        binding.tabLayoutArticles.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {

                override fun onTabSelected(tab: TabLayout.Tab) {
                    currentPage = tab.position
                    showPage()
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {}

                override fun onTabReselected(tab: TabLayout.Tab) {}
            }
        )

        // Navigasi dari HomeFragment
        parentFragmentManager.setFragmentResultListener(
            "open_articles_page",
            viewLifecycleOwner
        ) { _, bundle ->
            val page = bundle.getInt("page", 0).coerceIn(0, 2)

            if (binding.tabLayoutArticles.selectedTabPosition == page) {
                currentPage = page
                showPage()
            } else {
                binding.tabLayoutArticles.getTabAt(page)?.select()
            }
        }

        showPage()
    }

    private fun showPage() {
        selectedList = when (currentPage) {
            0 -> ArticlesData.articleList
            1 -> ArticlesData.resepList
            else -> ArticlesData.kebugaranList
        }

        val categories = when (currentPage) {
            0 -> listOf(
                "Semua", "Resep", "Kebugaran",
                "Istirahat", "Nutrisi"
            )

            1 -> listOf(
                "Semua", "Makanan",
                "Minuman", "Cemilan"
            )

            else -> listOf(
                "Semua", "Kardio",
                "Kekuatan", "Fleksibilitas"
            )
        }

        val context = requireContext()

        val green = ContextCompat.getColor(context, R.color.green)
        val blue = ContextCompat.getColor(context, R.color.blue)
        val lightGreen = ContextCompat.getColor(
            context, R.color.lightGreen
        )

        // Menghapus Chip dari tab sebelumnya
        binding.chipGroupKategori
            .setOnCheckedStateChangeListener(null)

        binding.chipGroupKategori.removeAllViews()

        // Membuat Chip kategori
        categories.forEach { category ->
            val chip = Chip(context).apply {
                id = View.generateViewId()
                text = category
                isCheckable = true
                isCheckedIconVisible = false
                isCloseIconVisible = false

                chipBackgroundColor = ColorStateList.valueOf(
                    Color.TRANSPARENT
                )
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

        // Filtering menggunakan Chip
        binding.chipGroupKategori.setOnCheckedStateChangeListener { group, checkedIds ->

            for (i in 0 until group.childCount) {
                val chip = group.getChildAt(i) as Chip

                chip.chipBackgroundColor = ColorStateList.valueOf(
                    if (chip.isChecked) lightGreen
                    else Color.TRANSPARENT
                )
            }

            val category = checkedIds.firstOrNull()?.let { id ->
                group.findViewById<Chip>(id)?.text?.toString()
            } ?: "Semua"

            displayArticles(category)
        }

        // Memilih kategori Semua secara otomatis
        val firstChip = binding.chipGroupKategori.getChildAt(0) as Chip
        binding.chipGroupKategori.check(firstChip.id)

        // Pastikan data ditampilkan meskipun listener tidak terpanggil
        displayArticles("Semua")

        // Kembali ke posisi scroll awal
        binding.recyclerArticles.scrollToPosition(0)

        binding.chipScrollView.post {
            _binding?.chipScrollView?.scrollTo(0, 0)
        }
    }

    private fun displayArticles(category: String) {
        val filteredList = if (category == "Semua") {
            selectedList
        } else {
            selectedList.filter {
                category in it.categories
            }
        }

        articlesAdapter.updateArticles(filteredList)
        binding.recyclerArticles.scrollToPosition(0)
    }

    override fun onDestroyView() {
        binding.recyclerArticles.adapter = null
        super.onDestroyView()
        _binding = null
    }
}
