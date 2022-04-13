package com.project.marvelapplication.data.api

import com.project.marvelapplication.data.model.MarvelCharacterModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/public/characters")
    suspend fun getMarvelCharacters(
        @Query("apikey")apiKey: String = RetrofitBuilder.PUBLIC_KEY,
        @Query("ts")ts: String = RetrofitBuilder.ts,
        @Query("hash")hash: String = RetrofitBuilder.hash(),
        @Query("limit")offset: String = RetrofitBuilder.limit
    ): MarvelCharacterModel

}