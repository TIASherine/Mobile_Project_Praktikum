package com.example.Projek_Sherine.Bookmark

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.Projek_Sherine.databinding.ItemArticleBinding

class BookmarkAdapter(
    context: Context,
    private val articles: List<BookmarkModel>
) : ArrayAdapter<BookmarkModel>(context, 0, articles) {

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
            val i = Intent(context, BookmarkActivity::class.java)

            i.putExtra("TITLE", data.title)
            i.putExtra("CONTENT", data.content)

            context.startActivity(i)
        }

        return view
    }
}