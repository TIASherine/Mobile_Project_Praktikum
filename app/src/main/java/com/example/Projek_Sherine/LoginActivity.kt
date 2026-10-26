package com.example.Projek_Sherine

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.Projek_Sherine.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.userSeePass.setOnClickListener {

            if (binding.edtPassword.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {

                // Show password
                binding.edtPassword.inputType = InputType.TYPE_CLASS_TEXT
                binding.userSeePass.setImageResource(R.drawable.open_eye)

            } else {

                // Hide password
                binding.edtPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.userSeePass.setImageResource(R.drawable.close_eye)
            }

            binding.edtPassword.setSelection(binding.edtPassword.text.length)
        }

        binding.btnLogin.setOnClickListener {
            val user = binding.edtUsername.text.toString()
            val pass = binding.edtPassword.text.toString()

            val editor = sharedPref.edit()
            editor.putString("username", user)

            if (user.isNotEmpty() && pass.isNotEmpty()) {
                if (binding.switchRememberMe.isChecked) {
                    editor.putBoolean("isLogin", true)
                    Toast.makeText(this, "We'll Remember You :]", Toast.LENGTH_LONG).show()
                }

                editor.apply()

                startActivity(Intent(this, BaseActivity::class.java))
                intent.putExtra("Username", user)
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Login Failed")
                    .setMessage("Please Try Again")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }

        binding.txtPassword

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
}