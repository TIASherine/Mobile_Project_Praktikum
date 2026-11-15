package com.example.Projek_Sherine.Articles

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Projek_Sherine.R
import com.example.Projek_Sherine.databinding.FragmentTabKebugaranBinding
import com.google.android.material.chip.Chip

class TabKebugaranFragment : Fragment() {
    private var _binding: FragmentTabKebugaranBinding? = null
    private val binding get() = _binding!!

    private lateinit var articlesAdapter: ArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabKebugaranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()

        articlesAdapter = ArticlesAdapter(
            context,
            ArticlesData.kebugaranList
        )

        binding.recyclerArticles.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articlesAdapter
        }

        val categories = listOf(
            "Semua",
            "Fleksibilitas",
            "Kardio",
            "Kekuatan"
        )

        val green = ContextCompat.getColor(context, R.color.green)
        val blue = ContextCompat.getColor(context, R.color.blue)
        val lightGreen = ContextCompat.getColor(
            context, R.color.lightGreen
        )

        categories.forEach { category ->
            val chip = Chip(context).apply {
                id = View.generateViewId()
                text = category

                isCheckable = true
                isCheckedIconVisible = false
                isCloseIconVisible = false

                chipBackgroundColor =
                    ColorStateList.valueOf(Color.TRANSPARENT)

                chipStrokeColor =
                    ColorStateList.valueOf(green)

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

            val filteredArticles = if (selectedCategory == "Semua") {
                ArticlesData.kebugaranList
            } else {
                ArticlesData.kebugaranList.filter {
                    selectedCategory in it.categories
                }
            }

            articlesAdapter.updateArticles(filteredArticles)
            binding.recyclerArticles.scrollToPosition(0)
        }

        val firstChip = binding.chipGroupKategori.getChildAt(0) as Chip
        binding.chipGroupKategori.check(firstChip.id)
    }

    override fun onDestroyView() {
        binding.recyclerArticles.adapter = null
        super.onDestroyView()
        _binding = null
    }
}
