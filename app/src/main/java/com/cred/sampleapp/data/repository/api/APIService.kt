package com.cred.sampleapp.data.repository.api

import com.cred.sampleapp.utils.APIResponse
import retrofit2.http.GET

// defining the different requests we have to do to get the data
interface APIService {
    @GET(".")
    suspend fun getDomainItems(): APIResponse
}