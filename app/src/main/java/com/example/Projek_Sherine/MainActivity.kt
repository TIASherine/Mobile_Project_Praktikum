package com.example.Projek_Sherine

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.Projek_Sherine.P5.LimaActivity
import com.example.Projek_Sherine.P6.EnamActivity
import com.example.Projek_Sherine.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

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

        binding.btnP5.setOnClickListener {
            Toast.makeText(this, "P5 clicked!", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, LimaActivity::class.java))
        }

        binding.btnP6.setOnClickListener {
            Toast.makeText(this, "P6 clicked!", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, EnamActivity::class.java))
        }

        binding.btnSnackbar.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Hai",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        binding.btnAlert.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Alert")
                .setMessage("Yada yada?")
                .setNegativeButton("Tidak", null)
                .setPositiveButton("Iya") { dialog, _ ->
                    Snackbar.make(
                        binding.root,
                        "Contoh Alert dan Snackbar!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }

        binding.btnTutorial.setOnClickListener {
            startActivity(Intent(this, TutorialScreenActivity::class.java))
        }

        binding.btnLogOut.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}