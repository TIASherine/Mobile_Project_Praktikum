package com.example.Projek_Sherine.Bookmark

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.graphics.toColorInt
import com.bumptech.glide.Glide
import com.example.Projek_Sherine.databinding.ItemArticleBinding
import com.google.android.material.chip.Chip

class BookmarkAdapter(
    context: Context,
    articles: List<BookmarkModel>
) : ArrayAdapter<BookmarkModel>(context, 0, articles) {

    private val blue = "#114B5F".toColorInt()
    private val lightGreen = "#C6DABF".toColorInt()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val binding = if (convertView == null) {
            ItemArticleBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        } else {
            ItemArticleBinding.bind(convertView)
        }

        val article = getItem(position) ?: return binding.root

        Glide.with(binding.articleImg)
            .load(article.image)
            .override(150, 150)
            .centerCrop()
            .into(binding.articleImg)

        binding.textTitle.text = article.title
        binding.textDescription.text = article.description

        binding.articleLabels.removeAllViews()

        val categories = listOf("Resep", "Nutrisi", "Kebugaran")
        val selectedCategories = when (position) {
            0 -> listOf("Artikel", "Resep")
            1 -> listOf("Kebugaran")
            else -> categories.take(2)
        }

        selectedCategories.forEach { category ->
            val chip = Chip(context).apply {
                text = category
                isClickable = false
                isCheckable = false
                isCheckedIconVisible = false

                chipBackgroundColor = ColorStateList.valueOf(lightGreen)
                chipStrokeColor = ColorStateList.valueOf(blue)
                chipStrokeWidth = resources.displayMetrics.density

                setTextColor(blue)
                textSize = 12f

                shapeAppearanceModel = shapeAppearanceModel
                    .toBuilder()
                    .setAllCornerSizes(50f)
                    .build()
            }

            binding.articleLabels.addView(chip)
        }

        binding.root.setOnClickListener {
            val intent = Intent(context, BookmarkActivity::class.java).apply {
                putExtra("TITLE", article.title)
                putExtra("CONTENT", article.content)
            }
            context.startActivity(intent)
        }

        return binding.root
    }
}
