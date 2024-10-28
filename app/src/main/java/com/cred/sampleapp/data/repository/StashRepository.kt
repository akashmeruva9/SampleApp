package com.cred.sampleapp.data.repository

import com.cred.sampleapp.data.entities.StashItemEntity

interface StashRepository {
    suspend fun getDomainItems(): List<StashItemEntity>
}
