package com.example.Projek_Sherine.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.Projek_Sherine.data.Entity.ArticlesEntity

@Dao
interface ArticlesDao {
    @Query("SELECT * FROM articles ORDER BY Id DESC")
    suspend fun getAll(): List<ArticlesEntity>

    @Insert
    suspend fun insert(note: ArticlesEntity)

    @Delete
    suspend fun delete(note: ArticlesEntity)
}
