package com.cred.sampleapp.business

data class StashOpenBody(
    val title: String,
    val subtitle: String,
    val card: CardDetails? = null,
    val items: List<Plan>? = null,
    val footer: String
)