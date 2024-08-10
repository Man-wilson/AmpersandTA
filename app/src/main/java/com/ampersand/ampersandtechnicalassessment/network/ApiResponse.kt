package com.ampersand.ampersandtechnicalassessment.network

data class ApiResponse(
    val id: String,
    val name: String,
    val data: Map<String, Any>?
)