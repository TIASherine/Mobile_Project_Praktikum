package com.example.Projek_Sherine

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.Projek_Sherine.Home.HomeFragment
import com.example.Projek_Sherine.Message.MessageFragment
import com.example.Projek_Sherine.More.MoreFragment
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
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        replaceFragment(HomeFragment())
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.action_message -> {
                    Toast.makeText(this, "Message Clicked", Toast.LENGTH_SHORT).show()
                    replaceFragment(MessageFragment())
                    true
                }

                R.id.action_more -> {
                    Toast.makeText(this, "More Clicked", Toast.LENGTH_SHORT).show()
                    replaceFragment(MoreFragment())
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
}
