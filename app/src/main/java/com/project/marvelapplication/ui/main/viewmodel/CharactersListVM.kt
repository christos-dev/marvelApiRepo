package com.project.marvelapplication.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.project.marvelapplication.data.api.ApiHelper
import com.project.marvelapplication.data.api.RetrofitBuilder
import com.project.marvelapplication.data.database.CharacterRoom
import com.project.marvelapplication.data.database.CharacterRoomDatabase
import com.project.marvelapplication.data.repository.CharacterRepository
import com.project.marvelapplication.utils.Resource
import kotlinx.coroutines.Dispatchers

class CharactersListVM(application: Application) : ViewModel() {

    val readAllCharacters: LiveData<List<CharacterRoom>>
    private val repository: CharacterRepository

    init {
        val characterDao = CharacterRoomDatabase.getDatabase(application).characterDao()
        repository = CharacterRepository(ApiHelper(RetrofitBuilder.apiService), characterDao)
        readAllCharacters = repository.readAllCharacters
    }

    fun getMarvelCharacters() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getMarvelCharacters()))
        }catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error occurred."))
        }
    }

}