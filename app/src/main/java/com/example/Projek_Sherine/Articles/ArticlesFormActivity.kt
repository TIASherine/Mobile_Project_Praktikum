package com.example.Projek_Sherine.Articles

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.Projek_Sherine.R
import com.example.Projek_Sherine.data.AppDatabase
import com.example.Projek_Sherine.data.Entity.ArticlesEntity
import com.example.Projek_Sherine.databinding.ActivityArticlesFormBinding
import kotlinx.coroutines.launch

class ArticlesFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticlesFormBinding

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityArticlesFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val categories = listOf(
            "Artikel",
            "Resep",
            "Kebugaran"
        )

        val categoryAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            categories
        )

        binding.dropdownCategory.setAdapter(categoryAdapter)

        binding.btnChooseImage.setOnClickListener {
            pickImage.launch(
                PickVisualMediaRequest(
                    ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        }

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val description = binding.etDesc.text.toString().trim()
            val category = binding.dropdownCategory.text.toString().trim()
            val url = binding.etUrl.text.toString().trim()
            val imageUrl = selectedImageUri

            if (title.isBlank() || description.isBlank() || category.isBlank() || url.isBlank() || imageUrl == null) {
                Toast.makeText(
                    this, "Isi semua kolom!", Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            binding.btnSave.isEnabled = false

            lifecycleScope.launch {
                try {
                    val fileName = "article_${System.currentTimeMillis()}.jpg"
                    val imageFile = java.io.File(filesDir, fileName)

                    db.articlesDao().insert(
                        ArticlesEntity(
                            title = title,
                            description = description,
                            category = category,
                            url = url,
                            imageUrl = imageFile.absolutePath
                        )
                    )
                    finish()
                } catch (e: Exception) {
                    binding.btnSave.isEnabled = true
                    Toast.makeText(
                        this@ArticlesFormActivity,
                        "Gagal menyimpan catatan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private var selectedImageUri: Uri? = null

    private val pickImage = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            selectedImageUri = uri

            binding.imgPreview.setImageURI(uri)
            binding.imgPreview.visibility = View.VISIBLE
        }
    }
}