//
//package com.example.Projek_Sherine.Home
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//import com.example.Projek_Sherine.Articles.ArticlesModel
//import com.example.Projek_Sherine.R
//import com.example.Projek_Sherine.databinding.ItemArticleBinding
//
//class HomeAdapter(
//    context: Context,
//    private val articles: List<ArticlesModel>,
//    private val onDetailClick: (ArticlesModel) -> Unit
//) : RecyclerView.Adapter<HomeAdapter.ArticleViewHolder>() {
//
//    private val sharedPref = context.getSharedPreferences(
//        "article_bookmarks",
//        Context.MODE_PRIVATE
//    )
//
//    inner class ArticleViewHolder(
//        val binding: ItemArticleBinding
//    ) : RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): ArticleViewHolder {
//
//        val binding = ItemArticleBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )
//
//        return ArticleViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(
//        holder: ArticleViewHolder,
//        position: Int
//    ) {
//        val article = articles[position]
//
//        holder.binding.apply {
//
//            // Image
//            Glide.with(root)
//                .load(article.image)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .centerCrop()
//                .override(150, 150)
//                .into(imgArtikel)
//
//            // Title and description
//            txtJudulArtikel.text = article.title
//            txtDescArtikel.text = article.description
//
//            // Categories
//            kategori1.text = article.categories.getOrNull(0) ?: ""
//
//            if (article.categories.size > 1) {
//                kategori2.visibility = View.VISIBLE
//                kategori2.text = article.categories[1]
//            } else {
//                kategori2.visibility = View.GONE
//            }
//
//            // Bookmark
//            fun updateBookmark() {
//                val isSaved = sharedPref.getBoolean(article.title, false)
//
//                btnBookmark.setImageResource(
//                    if (isSaved) R.drawable.bookmark_filled
//                    else R.drawable.bookmark
//                )
//            }
//
//            updateBookmark()
//
//            btnBookmark.setOnClickListener {
//                val newState = !sharedPref.getBoolean(
//                    article.title,
//                    false
//                )
//
//                sharedPref.edit()
//                    .putBoolean(article.title, newState)
//                    .apply()
//
//                updateBookmark()
//            }
//
//            // Detail
//            btnArticleDetail.setOnClickListener {
//                onDetailClick(article)
//            }
//        }
//    }
//
//    override fun getItemCount(): Int = articles.size
//}
