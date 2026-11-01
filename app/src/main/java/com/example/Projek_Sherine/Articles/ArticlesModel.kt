package com.example.Projek_Sherine.Articles

data class ArticlesModel(
    val title: String,
    val description: String,
    val imageResId: Int,
    val content: String,
    val categories: List<String> = emptyList()
)