package com.cred.sampleapp.business

data class StashItem(
    val open_state: StashOpenState,
    val closed_state: StashClosedState,
    val cta_text: String
)