package com.project.marvelapplication.data.repository

import androidx.lifecycle.LiveData
import com.project.marvelapplication.data.api.ApiHelper
import com.project.marvelapplication.data.database.CharacterRoom
import com.project.marvelapplication.data.database.CharacterRoomDao

class CharacterRepository(
    private val apiHelper: ApiHelper,
    private val characterRoomDao: CharacterRoomDao
) {

    suspend fun getMarvelCharacters() = apiHelper.getMarvelCharacters()

    val readAllCharacters: LiveData<List<CharacterRoom>> = characterRoomDao.readAllCharacters()

    suspend fun isCharacterInSquad(id: Int): Int = characterRoomDao.isCharacterInSquad(id)

    suspend fun addCharacter(characterRoom: CharacterRoom) {
        characterRoomDao.addCharacter(characterRoom)
    }

    suspend fun deleteCharacter(character: CharacterRoom){
        characterRoomDao.deleteCharacter(character)
    }

}