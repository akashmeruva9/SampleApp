package com.cred.sampleapp.business

data class Plan(
    val emi: String,
    val duration: String,
    val title: String,
    val subtitle: String,
    val tag: String? = null
)