package com.cred.sampleapp.data.entities


data class StashItemEntity(
    val title: String,
    val subtitle: String,
    val ctaText: String,
    val card: CardEntity?,
    val plans: List<PlanEntity>?,
    val footer: String,
    val isOpenState: Boolean,
    val key1: String? = null,
    val key2: String? = null
)