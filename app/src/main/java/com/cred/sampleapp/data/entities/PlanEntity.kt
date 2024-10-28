package com.cred.sampleapp.data.entities

data class PlanEntity(
    val emi: String? = null,
    val duration: String? = null,
    val title: String,
    val subtitle: String,
    val tag: String? = null
)