package com.cred.sampleapp.data.repository.api

import android.util.Log
import com.cred.sampleapp.data.entities.CardEntity
import com.cred.sampleapp.data.entities.PlanEntity
import com.cred.sampleapp.data.entities.StashItemEntity
import com.cred.sampleapp.data.repository.StashRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StashRepositoryImpl @Inject constructor(
    private val service: APIService
) : StashRepository {

    override suspend fun getDomainItems(): List<StashItemEntity> {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getDomainItems()

                response.items.mapNotNull { domainItem ->
                    val openBody = domainItem.open_state.body
                    val closedBody = domainItem.closed_state.body

                    StashItemEntity(
                        title = openBody.title,
                        subtitle = openBody.subtitle,
                        ctaText = domainItem.cta_text,
                        card = openBody.card?.let { card ->
                            CardEntity(
                                header = card.header,
                                description = card.description,
                                maxRange = card.max_range,
                                minRange = card.min_range
                            )
                        },
                        plans = openBody.items?.map { plan ->
                            PlanEntity(
                                emi = plan.emi,
                                duration = plan.duration,
                                title = plan.title,
                                subtitle = plan.subtitle,
                                tag = plan.tag
                            )
                        } ?: emptyList(),
                        footer = openBody.footer,
                        isOpenState = true,
                        key1 = closedBody.key1,
                        key2 = closedBody.key2
                    )
                }
            }catch ( e: Exception)
            {
                Log.e("DomainRepository", "getDomainItems: $e")
                emptyList()
            }
        }
    }
}