package com.example.Projek_Sherine.Bookmark

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.Projek_Sherine.databinding.ItemArticleBinding
import com.google.android.material.chip.Chip

class BookmarkAdapter(
    context: Context,
    private val articles: List<BookmarkModel>
) : ArrayAdapter<BookmarkModel>(context, 0, articles) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root
        val data = articles[position]

        Glide.with(binding.articleImg)
            .load(data.image)
            .override(300, 300)
            .centerCrop()
            .into(binding.articleImg)

        binding.textTitle.text = data.title
        binding.textDescription.text = data.description

        view.setOnClickListener {
            val i = Intent(context, BookmarkActivity::class.java)

            i.putExtra("TITLE", data.title)
            i.putExtra("CONTENT", data.content)

            context.startActivity(i)
        }

        val categories = listOf("Resep", "Nutrisi", "Kebugaran")

        val selectedCategories = when (position) {
            0 -> listOf("Artikel", "Resep")
            1 -> listOf("Kebugaran")
            else -> categories.shuffled().take((1..2).random())
        }

        binding.articleLabels.removeAllViews()

        selectedCategories.forEach { category ->
            val chip = Chip(context).apply {
                text = category
                isClickable = false
                isCheckable = false

                chipBackgroundColor = ColorStateList.valueOf(
                    Color.parseColor("#C6DABF")
                )
                chipStrokeColor = ColorStateList.valueOf(
                    Color.parseColor("#114B5F")
                )
                chipStrokeWidth = context.resources.displayMetrics.density
                shapeAppearanceModel = shapeAppearanceModel
                    .toBuilder()
                    .setAllCornerSizes(50f)
                    .build()

                setTextColor(Color.parseColor("#114B5F"))
                textSize = 12f
            }

            binding.articleLabels.addView(chip)
        }

        return view
    }
}