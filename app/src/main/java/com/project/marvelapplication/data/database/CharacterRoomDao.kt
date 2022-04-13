package com.project.marvelapplication.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CharacterRoomDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCharacter(characterRoom: CharacterRoom)

    @Delete
    suspend fun deleteCharacter(characterRoom: CharacterRoom)

    @Query("SELECT * FROM marvel_character")
    fun readAllCharacters(): LiveData<List<CharacterRoom>>

    @Query("SELECT count(id) FROM marvel_character WHERE id = :id")
    suspend fun isCharacterInSquad(id: Int): Int

}