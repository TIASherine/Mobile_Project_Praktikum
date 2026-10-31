package com.example.Projek_Sherine.Articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.Projek_Sherine.databinding.FragmentArticlesBinding

class ArticlesFragment : Fragment() {
    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        ArticlesModel("Alya", "Halo! Apa kabar?", "https://i.pravatar.cc/150?img=1"),
        ArticlesModel("Budi", "Sudah makan?", "https://i.pravatar.cc/150?img=2"),
        ArticlesModel("Citra", "Jangan lupa tugasnya ya!", "https://i.pravatar.cc/150?img=3"),
        ArticlesModel("Dika", "Besok kita rapat jam 9", "https://i.pravatar.cc/150?img=4"),
        ArticlesModel("Eka", "Nice job kemarin!", "https://i.pravatar.cc/150?img=5"),
        ArticlesModel("Fajar", "Lagi ngapain?", "https://i.pravatar.cc/150?img=6"),
        ArticlesModel("Gita", "Boleh minta tolong?", "https://i.pravatar.cc/150?img=7"),
        ArticlesModel("Hana", "Lihat email ya", "https://i.pravatar.cc/150?img=8"),
        ArticlesModel("Irfan", "Oke noted", "https://i.pravatar.cc/150?img=9"),
        ArticlesModel("Joko", "Sampai jumpa besok", "https://i.pravatar.cc/150?img=10")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "- Articles -"
        (requireActivity() as AppCompatActivity).supportActionBar?.subtitle =
            android.text.Html.fromHtml(
                "<font color='#114B5F'>Stay </font><font color='#1A936F'>Healthy</font><font color='#114B5F'>, Feel </font><font color='#1A936F'> Happy</font>",
                android.text.Html.FROM_HTML_MODE_COMPACT
            )

        val adapter = ArticlesAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }


}