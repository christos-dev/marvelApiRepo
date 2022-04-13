package com.project.marvelapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marvel_character")
data class CharacterRoom(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String
)
