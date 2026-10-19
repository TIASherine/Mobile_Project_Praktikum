package com.example.Projek_Sherine

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.Projek_Sherine.databinding.ActivityMainBinding
import com.example.Projek_Sherine.Home.p5.LimaActivity
import com.example.Projek_Sherine.Home.p6.EnamActivity
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

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        val displayUser = intent.getStringExtra("Username")

        binding.username.text = displayUser

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Halaman Pertemuan 5"
            subtitle = "Ini adalah subtitle"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

//        binding.btnDetail.setOnClickListener {
//            MaterialAlertDialogBuilder(this)
//                .setTitle("Detail Page")
//                .setMessage("Go See The Detail?")
//                .setNegativeButton("No", null)
//                .setPositiveButton("Yeah") { dialog, _ ->
//                    val intent = Intent(this, DetailActivity::class.java)
//                    startActivity(intent)
//
//                    dialog.dismiss()
//                }.setCancelable(false).show()
//        }
//
//        binding.btnP5.setOnClickListener {
//            MaterialAlertDialogBuilder(this)
//                .setTitle("Lima Page")
//                .setMessage("Go See Lima?")
//                .setNegativeButton("No", null)
//                .setPositiveButton("Yeah") { dialog, _ ->
//                    val intent = Intent(this, LimaActivity::class.java)
//                    startActivity(intent)
//
//                    dialog.dismiss()
//                }.setCancelable(false).show()
//        }
//
//        binding.btnP6.setOnClickListener {
//            MaterialAlertDialogBuilder(this)
//                .setTitle("Enam Page")
//                .setMessage("Go See Enam?")
//                .setNegativeButton("No", null)
//                .setPositiveButton("Yeah") { dialog, _ ->
//                    val intent = Intent(this, EnamActivity::class.java)
//                    startActivity(intent)
//
//                    dialog.dismiss()
//                }.setCancelable(false).show()
//        }
//
//        binding.btnLogOut.setOnClickListener {
//            AlertDialog.Builder(this)
//                .setTitle("Log Out")
//                .setMessage("Are You Sure?")
//                .setPositiveButton("Yeah") { dialog, _ ->
//                    val editor = sharedPref.edit()
//                    editor.clear()
//                    editor.apply()
//                    dialog.dismiss()
//                    startActivity(Intent(this, LoginActivity::class.java))
//                    finish()
//                }
//                .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
//                .show()
//        }
    }


}

package com.example.Projek_Sherine

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.Projek_Sherine.databinding.ActivityMainBinding
import com.example.Projek_Sherine.Home.p5.LimaActivity
import com.example.Projek_Sherine.Home.p6.EnamActivity
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


        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        val displayUser = intent.getStringExtra("Username")

        binding.username.text = displayUser
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            R.id.action_home -> {
                Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_articles -> {
                Toast.makeText(this, "Article Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_bookmark -> {
                Toast.makeText(this, "Bookmark Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_profile -> {
                Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_logout -> {
                Toast.makeText(this, "Logout Clicked", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}