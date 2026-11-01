package com.example.Projek_Sherine

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.Projek_Sherine.Articles.ArticlesFragment
import com.example.Projek_Sherine.Bookmark.BookmarkFragment
import com.example.Projek_Sherine.Home.HomeFragment
import com.example.Projek_Sherine.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val editor = sharedPref.edit()

        replaceFragment(HomeFragment())
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                android.R.id.home -> {
                    onBackPressedDispatcher.onBackPressed()
                    true
                }

                R.id.action_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.action_articles -> {
                    replaceFragment(ArticlesFragment())
                    true
                }

//                R.id.action_more -> {
//                    replaceFragment(MoreFragment())
//                    true
//                }

                R.id.action_bookmark -> {
                    replaceFragment(BookmarkFragment())
                    true
                }

                R.id.action_profile -> {
                    val options = arrayOf("Main", "Log Out")

                    AlertDialog.Builder(this)
                        .setTitle("Profile")
                        .setItems(options) { dialog, which ->
                            when (which) {
                                0 -> {
                                    Toast.makeText(this, "Main Page", Toast.LENGTH_SHORT)
                                        .show()
                                    startActivity(Intent(this, MainActivity::class.java))
                                }

                                1 -> { // Settings
                                    Toast.makeText(this, "Log out", Toast.LENGTH_SHORT)
                                        .show()

                                    editor.putBoolean("isLogin", false)
                                    editor.putBoolean("tutorialYes", false)
                                    editor.apply()

                                    startActivity(Intent(this, LoginActivity::class.java))
                                    finish()
                                }
                            }
                        }
                        .show()
                    true
                }


                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

}
