package com.example.Projek_Sherine.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.Projek_Sherine.P9.NinthActivity
import com.example.Projek_Sherine.R
import com.example.Projek_Sherine.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "- Beranda -"
        (requireActivity() as AppCompatActivity).supportActionBar?.subtitle =
            android.text.Html.fromHtml(
                "<font color='#114B5F'>Stay </font><font color='#1A936F'>Healthy</font><font color='#114B5F'>, Feel </font><font color='#1A936F'> Happy</font>",
                android.text.Html.FROM_HTML_MODE_COMPACT
            )

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        val displayUser = sharedPref.getString("username", "Sherine")
        binding.txtWelcome.text = "Apa kabar, $displayUser?"

        Glide.with(this)
            .load(R.drawable.article1)
            .centerCrop()
            .override(300, 300)
            .into(binding.imgArtikel1)

        Glide.with(this)
            .load(R.drawable.article2)
            .centerCrop()
            .override(300, 300)
            .into(binding.imgArtikel2)

        binding.btnArticlesDetail.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("P9 Page")
                .setMessage("Go See Page 9?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yeah") { dialog, _ ->
                    val intent = Intent(requireContext(), NinthActivity::class.java)
                    startActivity(intent)

                    dialog.dismiss()
                }.setCancelable(false).show()
        }
    }
}