package com.example.Projek_Sherine.data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")

data class ArticlesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val category: String,
    val url: String,
    val imageUrl: String,
)