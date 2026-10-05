package com.example.Projek_Sherine

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.Projek_Sherine.R
import com.example.Projek_Sherine.databinding.ActivityMainBinding
import com.example.Projek_Sherine.p5.LimaActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val displayUser = intent.getStringExtra("Username")
        val displayPass = intent.getStringExtra("Password")

        binding.username.text = displayUser
        binding.password.setText(displayPass)

        binding.btnDetail.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Detail Page")
                .setMessage("Go See The Detail?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yeah") { dialog, _ ->
                    val intent = Intent(this, DetailActivity::class.java)
                    startActivity(intent)

                    dialog.dismiss()
                }.setCancelable(false).show()
        }

        binding.btnP5.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Lima Page")
                .setMessage("Go See Lima?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yeah") { dialog, _ ->
                    val intent = Intent(this, LimaActivity::class.java)
                    startActivity(intent)

                    dialog.dismiss()
                }.setCancelable(false).show()
        }
    }
}