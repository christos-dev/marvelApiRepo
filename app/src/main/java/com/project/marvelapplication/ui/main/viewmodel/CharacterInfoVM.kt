package com.project.marvelapplication.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.project.marvelapplication.data.api.ApiHelper
import com.project.marvelapplication.data.api.RetrofitBuilder
import com.project.marvelapplication.data.database.CharacterRoom
import com.project.marvelapplication.data.database.CharacterRoomDatabase
import com.project.marvelapplication.data.repository.CharacterRepository
import com.project.marvelapplication.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterInfoVM(application: Application): ViewModel() {

    private val repository: CharacterRepository

    init {
        val characterDao = CharacterRoomDatabase.getDatabase(application).characterDao()
        repository = CharacterRepository(ApiHelper(RetrofitBuilder.apiService), characterDao)
    }

    fun addCharacter(character: CharacterRoom) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCharacter(character)
        }
    }

    fun deleteCharacter(character: CharacterRoom){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCharacter(character)
        }
    }

    fun isCharacterInSquad(id: Int) = liveData(Dispatchers.Main){
        try {
            emit(Resource.success(repository.isCharacterInSquad(id)))
        }catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error occurred."))
        }

    }

}