package com.example.countrylist.domain.model

import com.google.gson.JsonElement

data class ApiResponse(
    val data: Map<String, JsonElement>
)
