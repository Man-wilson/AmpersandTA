package com.ampersand.ampersandtechnicalassessment.network

import retrofit2.http.GET

interface ApiService {
    @GET("objects")
    suspend fun getObjects(): List<ApiResponse>
}

data class ApiResponse(
    val id: String,
    val name: String,
    val data: Map<String, Any>?
)