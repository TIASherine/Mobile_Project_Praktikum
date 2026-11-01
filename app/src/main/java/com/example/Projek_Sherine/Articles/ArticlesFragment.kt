package com.example.Projek_Sherine.Articles

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.Projek_Sherine.R
import com.example.Projek_Sherine.databinding.FragmentArticlesBinding
import com.google.android.material.chip.Chip

class ArticlesFragment : Fragment() {
    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!

    private val articleList = listOf(
        ArticlesModel(
            "Nutrisi & Menu Sehat",
            "Panduan menjaga pola makan sehat dan bergizi.",
            R.drawable.article1,
            "Mengonsumsi makanan bergizi seimbang membantu tubuh memperoleh nutrisi yang dibutuhkan untuk menjaga kesehatan.",
            listOf("Resep", "Nutrisi")
        ),
        ArticlesModel(
            "Olahraga & Aktivitas Fisik",
            "Manfaat aktivitas fisik untuk kesehatan tubuh.",
            R.drawable.article2,
            "Olahraga secara rutin membantu menjaga kebugaran tubuh, meningkatkan stamina, dan mendukung kesehatan secara keseluruhan.",
            listOf("Kebugaran")
        ),
        ArticlesModel(
            "Pentingnya Jeda Istirahat di Sela Kerja",
            "Manfaat beristirahat sejenak saat beraktivitas.",
            R.drawable.article3,
            "Mengambil jeda istirahat secara teratur dapat membantu mengurangi kelelahan dan menjaga konsentrasi selama bekerja.",
            listOf("Istirahat")
        ),
        ArticlesModel(
            "Hubungan Tidur Cukup dengan Pemulihan Otot",
            "Pentingnya tidur untuk pemulihan tubuh.",
            R.drawable.article4,
            "Tidur yang cukup berperan penting dalam proses pemulihan otot dan membantu tubuh mendapatkan kembali energi setelah beraktivitas.",
            listOf("Istirahat", "Kebugaran")
        ),
        ArticlesModel(
            "Manfaat Telur Sebagai Sumber Protein Lengkap",
            "Kandungan protein dan manfaat telur bagi tubuh.",
            R.drawable.article5,
            "Telur merupakan sumber protein lengkap yang mengandung asam amino esensial untuk mendukung pertumbuhan dan pemeliharaan jaringan tubuh.",
            listOf("Kebugaran", "Nutrisi")
        )
    )


    private val resepList = listOf(
        ArticlesModel(
            "Sarapan Sehat Bergizi Lengkap",
            "Ide sarapan sehat untuk memulai hari.",
            R.drawable.resep1,
            "Sarapan bergizi seimbang dapat terdiri dari telur, sayuran, buah-buahan, dan sumber karbohidrat untuk membantu memenuhi kebutuhan energi sehari-hari.",
            listOf("Makanan")
        ),
        ArticlesModel(
            "Smoothie Bowl Beri & Granola",
            "Menu smoothie bowl yang sehat dan menyegarkan.",
            R.drawable.resep2,
            "Smoothie bowl dapat dibuat menggunakan buah beri, yoghurt, dan granola sebagai pilihan sarapan atau camilan yang mengandung serat dan berbagai nutrisi.",
            listOf("Makanan")
        ),
        ArticlesModel(
            "Burger Gandum Daging Sapi",
            "Alternatif burger dengan roti gandum.",
            R.drawable.resep3,
            "Burger gandum dapat dibuat menggunakan roti gandum, daging sapi matang, selada, tomat, dan bahan pelengkap lainnya untuk menghasilkan menu yang lebih seimbang.",
            listOf("Makanan")
        ),
        ArticlesModel(
            "Tumis Sayur Cepat Warna-Warni",
            "Resep tumis sayuran praktis dan bergizi.",
            R.drawable.resep4,
            "Tumis sayur dibuat menggunakan kombinasi wortel, brokoli, paprika, dan sayuran lainnya dengan sedikit minyak dan bumbu sesuai selera.",
            listOf("Makanan")
        ),
        ArticlesModel(
            "Olahan Alpukat Super Nutrisi",
            "Inspirasi hidangan sehat berbahan alpukat.",
            R.drawable.resep5,
            "Alpukat mengandung lemak tak jenuh dan serat. Buah ini dapat diolah menjadi berbagai hidangan seperti salad, roti alpukat, atau smoothie.",
            listOf("Makanan")
        )
    )

    private val kebugaranList = listOf(
        ArticlesModel(
            "Olahraga Pagi",
            "Manfaat olahraga ringan di pagi hari.",
            R.drawable.fitness,
            "Olahraga pagi seperti berjalan kaki, jogging, atau peregangan dapat membantu meningkatkan kebugaran tubuh dan mempersiapkan diri untuk aktivitas sehari-hari.",
            listOf("Kardio")
        ),
        ArticlesModel(
            "Yoga untuk Pemula",
            "Latihan sederhana untuk menjaga fleksibilitas.",
            R.drawable.article4,
            "Yoga merupakan aktivitas fisik yang menggabungkan gerakan tubuh, pernapasan, dan konsentrasi untuk membantu meningkatkan fleksibilitas serta keseimbangan.",
            listOf("Fleksibilitas")
        ),
        ArticlesModel(
            "Latihan di Rumah",
            "Tips menjaga kebugaran tanpa pergi ke gym.",
            R.drawable.resep3,
            "Latihan sederhana seperti squat, plank, push-up, dan jumping jack dapat dilakukan di rumah tanpa memerlukan peralatan khusus.",
            listOf("Kekuatan")
        ),
        ArticlesModel(
            "Latihan di Rumah2",
            "Tips menjaga kebugaran tanpa pergi ke gym.",
            R.drawable.resep3,
            "Latihan sederhana seperti squat, plank, push-up, dan jumping jack dapat dilakukan di rumah tanpa memerlukan peralatan khusus.",
            listOf("Kardio", "Kekuatan")
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "- Articles -"
        (requireActivity() as AppCompatActivity).supportActionBar?.subtitle =
            android.text.Html.fromHtml(
                "<font color='#114B5F'>Stay </font><font color='#1A936F'>Healthy</font><font color='#114B5F'>, Feel </font><font color='#1A936F'> Happy</font>",
                android.text.Html.FROM_HTML_MODE_COMPACT
            )


        val pageTitles = listOf("Artikel", "Resep", "Kebugaran")
        var currentPage = 0


        fun showPage() {
            binding.txtArtikel.text = pageTitles[currentPage]

            val selectedList = when (currentPage) {
                0 -> articleList
                1 -> resepList
                2 -> kebugaranList
                else -> articleList
            }

            val categories = when (currentPage) {
                0 -> listOf("Semua", "Resep", "Kebugaran", "Istirahat", "Nutrisi")
                1 -> listOf("Semua", "Makanan", "Minuman", "Cemilan")
                2 -> listOf("Semua", "Kardio", "Kekuatan", "Fleksibilitas")
                else -> listOf("Semua")
            }

            fun displayArticles(category: String) {
                val filteredList = if (category == "Semua") {
                    selectedList
                } else {
                    selectedList.filter { category in it.categories }
                }

                val adapter = ArticlesAdapter(requireContext(), filteredList)

                binding.articleContainer.removeAllViews()

                for (i in 0 until adapter.count) {
                    val itemView = adapter.getView(
                        i,
                        null,
                        binding.articleContainer
                    )
                    binding.articleContainer.addView(itemView)
                }
            }

            binding.chipGroupKategori.removeAllViews()

            categories.forEach { category ->
                val chip = Chip(requireContext()).apply {
                    id = View.generateViewId()
                    text = category
                    isCheckable = true
                    isCheckedIconVisible = false
                    isCloseIconVisible = false

                    chipBackgroundColor = ColorStateList.valueOf(Color.TRANSPARENT)
                    chipStrokeColor = ColorStateList.valueOf(
                        ContextCompat.getColor(requireContext(), R.color.green)
                    )
                    chipStrokeWidth = resources.displayMetrics.density

                    setTextColor(
                        ContextCompat.getColor(requireContext(), R.color.blue)
                    )

                    shapeAppearanceModel = shapeAppearanceModel
                        .toBuilder()
                        .setAllCornerSizes(50f)
                        .build()
                }

                binding.chipGroupKategori.addView(chip)
            }

            binding.chipGroupKategori.setOnCheckedStateChangeListener { group, checkedIds ->

                for (i in 0 until group.childCount) {
                    val chip = group.getChildAt(i) as Chip

                    chip.chipBackgroundColor = ColorStateList.valueOf(
                        if (chip.isChecked)
                            ContextCompat.getColor(requireContext(), R.color.lightGreen)
                        else
                            Color.TRANSPARENT
                    )
                }

                val selectedCategory = checkedIds.firstOrNull()?.let { id ->
                    group.findViewById<Chip>(id)?.text?.toString()
                } ?: "Semua"

                displayArticles(selectedCategory)
            }

            val firstChip = binding.chipGroupKategori.getChildAt(0) as Chip
            binding.chipGroupKategori.check(firstChip.id)

            binding.btnPrevPage.isEnabled = currentPage > 0
            binding.btnPrevPage.alpha = if (currentPage > 0) 1f else 0f

            binding.btnNextPage.isEnabled = currentPage < 2
            binding.btnNextPage.alpha = if (currentPage < 2) 1f else 0f

            binding.articleScrollView.scrollTo(0, 0)
        }


        binding.btnNextPage.setOnClickListener {
            if (currentPage < 2) {
                currentPage++
                showPage()
            }
        }

        binding.btnPrevPage.setOnClickListener {
            if (currentPage > 0) {
                currentPage--
                showPage()
            }
        }

        showPage()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}