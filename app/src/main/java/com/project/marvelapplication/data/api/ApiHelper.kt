package com.project.marvelapplication.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getMarvelCharacters() = apiService.getMarvelCharacters()

}