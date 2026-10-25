package com.example.Projek_Sherine

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.Projek_Sherine.databinding.ActivityRegisterBinding
import kotlin.random.Random
import androidx.core.widget.doOnTextChanged
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var kodeOTP = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val otpInputs = mutableListOf<EditText>()

        repeat(5) {
            val card = layoutInflater.inflate(
                R.layout.otp_code,
                binding.otpContainer,
                false
            )

            otpInputs.add(card.findViewById(R.id.txtKode))
            binding.otpContainer.addView(card)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnOTP.setOnClickListener {
            val email = binding.edtUsername.text.toString().trim()

            if (email.isEmpty()) {
                binding.edtUsername.error = "Email wajib diisi!"
            } else if (!email.contains("@")) {
                binding.edtUsername.error = "Email harus mengandung @"
            } else {
                kodeOTP = Random.nextInt(10000, 100000).toString()

                Snackbar.make(binding.root, "Kode OTP anda: $kodeOTP", Snackbar.LENGTH_LONG).show()
            }
        }

        binding.btnVerifikasi.setOnClickListener {
            val inputOTP = otpInputs.joinToString("") {
                it.text.toString()
            }
            if (kodeOTP.isEmpty() || inputOTP != kodeOTP) {
                Snackbar.make(
                    binding.root,
                    "Kode OTP salah atau belum diisi!",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Verifikasi OTP")
                    .setMessage("Yakin Kode Benar?")
                    .setNegativeButton("Tidak", null)
                    .setPositiveButton("Iya") { dialog, _ ->
                        Toast.makeText(this, "OTP berhasil diverifikasi!", Toast.LENGTH_SHORT)
                            .show()

                        startActivity(Intent(this, LoginActivity::class.java))
                        dialog.dismiss()
                    }
                    .setCancelable(false)
                    .show()
            }
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}