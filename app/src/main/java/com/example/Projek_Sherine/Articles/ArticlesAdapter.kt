package com.example.Projek_Sherine.Articles

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.edit
import androidx.core.graphics.toColorInt
import com.bumptech.glide.Glide
import com.example.Projek_Sherine.ArticleWebViewActivity
import com.example.Projek_Sherine.R
import com.example.Projek_Sherine.databinding.ItemArticleBinding
import com.google.android.material.chip.Chip

class ArticlesAdapter(
    context: Context,
    articles: List<ArticlesModel>
) : ArrayAdapter<ArticlesModel>(context, 0, articles) {
    private val sharedPref = context.getSharedPreferences(
        "article_bookmarks",
        Context.MODE_PRIVATE
    )

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
            .load(article.imageResId)
            .override(150, 150)
            .centerCrop()
            .into(binding.articleImg)

        binding.textTitle.text = article.title
        binding.textDescription.text = article.description

        binding.articleLabels.removeAllViews()

        article.categories.forEach { category ->
            val chip = Chip(context).apply {
                text = category
                isClickable = false
                isCheckable = false
                isCheckedIconVisible = false

                chipBackgroundColor = ColorStateList.valueOf(lightGreen)
                chipStrokeColor = ColorStateList.valueOf(blue)
                chipStrokeWidth = resources.displayMetrics.density

                setTextColor(blue)
                textSize = 11f
            }

            binding.articleLabels.addView(chip)
        }

        binding.btnArticleArrow.setOnClickListener {
            val intent = Intent(
                context,
                ArticleWebViewActivity::class.java
            ).apply {
                putExtra("ARTICLE_URL", article.url)
                putExtra("ARTICLE_TITLE", article.title)
            }

            context.startActivity(intent)
        }

        val bookmarkKey = article.url
        var isBookmarked = sharedPref.getBoolean(bookmarkKey, false)

        fun updateBookmarkIcon() {
            binding.btnArticleBookmark.setImageResource(
                if (isBookmarked) R.drawable.bookmark_filled
                else R.drawable.bookmark
            )
        }

        updateBookmarkIcon()

        binding.btnArticleBookmark.setOnClickListener {
            isBookmarked = !isBookmarked

            sharedPref.edit {
                putBoolean(bookmarkKey, isBookmarked)
            }

            updateBookmarkIcon()
        }

        return binding.root
    }
}
