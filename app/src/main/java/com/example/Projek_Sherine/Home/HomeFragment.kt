package com.example.Projek_Sherine.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.Projek_Sherine.DetailActivity
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
        (requireActivity() as AppCompatActivity).supportActionBar?.subtitle = "Stay Healthy, Feel Happy"

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        val displayUser = sharedPref.getString("username", "Sherine")
        binding.txtWelcome.text = "Apa kabar, $displayUser?"

        binding.btnArticlesDetail.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Detail Page")
                .setMessage("Go See The Detail?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yeah") { dialog, _ ->
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    startActivity(intent)

                    dialog.dismiss()
                }.setCancelable(false).show()
        }
    }
}