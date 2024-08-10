package com.ampersand.ampersandtechnicalassessment.network

import retrofit2.http.GET

interface ApiService {
    @GET("objects")
    suspend fun getObjects(): List<ApiResponse>
}