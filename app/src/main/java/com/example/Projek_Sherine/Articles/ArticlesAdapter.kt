package com.example.Projek_Sherine.Articles

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.Projek_Sherine.ArticleWebViewActivity
import com.example.Projek_Sherine.R
import com.example.Projek_Sherine.databinding.ItemArticleBinding
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar

class ArticlesAdapter(
    context: Context,
    private var articles: List<ArticlesModel>
) : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {
    private val sharedPref = context.getSharedPreferences(
        "article_bookmarks",
        Context.MODE_PRIVATE
    )

    private val blue = "#114B5F".toColorInt()
    private val lightGreen = "#C6DABF".toColorInt()

    inner class ArticleViewHolder(val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ArticleViewHolder,
        position: Int
    ) {
        val article = articles[position]
        val binding = holder.binding

        Glide.with(binding.articleImg)
            .load(article.imageResId)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .override(150, 150)
            .centerCrop()
            .into(binding.articleImg)

        binding.textTitle.text = article.title
        binding.textDescription.text = article.description

        binding.articleLabels.removeAllViews()

        article.categories.forEach { category ->
            val chip = Chip(binding.root.context).apply {
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
            val context = binding.root.context

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

        fun updateBookmarkIcon() {
            val isSaved = sharedPref.getBoolean(bookmarkKey, false)

            binding.btnArticleBookmark.setImageResource(
                if (isSaved) R.drawable.bookmark_filled
                else R.drawable.bookmark
            )
        }

        updateBookmarkIcon()

        binding.btnArticleBookmark.setOnClickListener {
            val isSaved = sharedPref.getBoolean(bookmarkKey, false)

            sharedPref.edit {
                putBoolean(bookmarkKey, !isSaved)
            }

            updateBookmarkIcon()

            if (!isSaved) {
                Snackbar.make(
                    binding.root,
                    "Artikel Tersimpan",
                    Snackbar.LENGTH_LONG
                ).setAction("BATAL") {
                    sharedPref.edit {
                        putBoolean(bookmarkKey, false)
                    }
                    updateBookmarkIcon()
                }.show()
            }
        }
    }

    override fun getItemCount(): Int = articles.size

    fun updateArticles(newArticles: List<ArticlesModel>) {
        articles = newArticles
        notifyDataSetChanged()
    }
}
