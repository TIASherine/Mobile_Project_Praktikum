package com.example.Projek_Sherine.Articles

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

class ArticlesAdapter(
    context: Context,
    private val messages: List<ArticlesModel>
) : ArrayAdapter<ArticlesModel>(context, 0, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root
        val data = messages[position]

        Glide.with(context)
            .load(data.imageResId)
            .into(binding.articleImg)

        binding.textTitle.text = data.title
        binding.textDescription.text = data.description
        binding.articleImg.setImageResource(data.imageResId)

        val selectedCategories = data.categories

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
                setTextColor(Color.parseColor("#114B5F"))
                textSize = 11f
            }

            binding.articleLabels.addView(chip)
        }

        view.setOnClickListener {
            val i = Intent(context, ArticlesActivity::class.java)

            i.putExtra("TITLE", data.title)
            i.putExtra("CONTENT", data.content)

            context.startActivity(i)
        }

        return view
    }
}


