package com.cred.sampleapp.utils

import com.cred.sampleapp.business.StashItem

data class APIResponse(
    val items: List<StashItem>
)