package com.project.marvelapplication.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

object RetrofitBuilder {

    private const val BASE_URL = "https://gateway.marvel.com"
    val ts = Timestamp(System.currentTimeMillis()).time.toString()
    const val PUBLIC_KEY = "356435f5e8a584bab7e0c18da8ced2d6"
    private const val PRIVATE_KEY = "55d8f870070188a86b2f2bf292746789e936e9d1"
    const val limit = "100"
    fun hash(): String {
        val apiKey = "$ts$PRIVATE_KEY$PUBLIC_KEY"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(apiKey.toByteArray())).toString(16).padStart(32, '0')
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}