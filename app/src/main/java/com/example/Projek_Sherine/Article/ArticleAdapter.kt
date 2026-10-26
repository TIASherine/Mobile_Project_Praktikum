package com.example.Projek_Sherine.Article

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.Projek_Sherine.databinding.ItemArticleBinding

class ArticleAdapter(
    context: Context,
    private val articles: List<ArticleModel>
) : ArrayAdapter<ArticleModel>(context, 0, articles) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root
        val data = articles[position]

        Glide.with(context)
            .load(data.image)
            .centerCrop()
            .override(300, 300)
            .into(binding.articleImg)
        binding.textTitle.text = data.title
        binding.textDescription.text = data.description

        view.setOnClickListener {
            val i = Intent(context, ArticleActivity::class.java)

            i.putExtra("TITLE", data.title)
            i.putExtra("CONTENT", data.content)

            context.startActivity(i)
        }

        return view
    }
}