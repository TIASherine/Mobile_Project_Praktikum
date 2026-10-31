package com.example.Projek_Sherine.Articles

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.Projek_Sherine.databinding.ItemMessageBinding

class ArticlesAdapter(
    context: Context,
    private val messages: List<ArticlesModel>
) : ArrayAdapter<ArticlesModel>(context, 0, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root
        val data = messages[position]

        Glide.with(context)
            .load(data.avatarUrl)
            .into(binding.avatarImg)
        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText

        view.setOnClickListener {
//            Snackbar.make(
//                parent,
//                "Pesan dari ${data.senderName}: ${data.messageText}",
//                Snackbar.LENGTH_SHORT
//            ).show()

            val i = Intent(context, ArticlesActivity::class.java)

            i.putExtra("SENDER", data.senderName)
            i.putExtra("MESSAGE", data.messageText)

            context.startActivity(i)
        }

        return view
    }
}


