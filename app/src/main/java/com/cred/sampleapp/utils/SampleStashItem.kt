package com.cred.sampleapp.utils

import com.cred.sampleapp.data.entities.CardEntity
import com.cred.sampleapp.data.entities.PlanEntity
import com.cred.sampleapp.data.entities.StashItemEntity

val stashItems = listOf(
    StashItemEntity(
        title = "nikunj how much do you need?",
        subtitle = "move the dial and set any amount you need upto ₹487891",
        ctaText = "Proceed to EMI selection",
        card = CardEntity(
            header = "credit amount",
            description = "@1.04% monthly",
            maxRange = 487891,
            minRange = 500
        ),
        plans = emptyList(),
        footer = "stash is instant. money will be credited within seconds",
        isOpenState = true,
        key1 = "credit amount",
        key2 = null
    ),
    StashItemEntity(
        title = "how do you wish to repay?",
        subtitle = "choose one of our recommended plans or make your own",
        ctaText = "Select your bank account",
        card = null,
        plans = listOf(
            PlanEntity(emi = "₹4,247 /mo", duration = "12 months", title = "₹4,247 /mo for 12 months", subtitle = "See calculations", tag = null),
            PlanEntity(emi = "₹5,580 /mo", duration = "9 months", title = "₹5,580 /mo for 9 months", subtitle = "See calculations", tag = "recommended"),
            PlanEntity(emi = "₹8,270 /mo", duration = "6 months", title = "₹8,270 /mo for 6 months", subtitle = "See calculations", tag = null)
        ),
        footer = "create your own plan",
        isOpenState = true,
        key1 = "emi",
        key2 = "duration"
    ),
    StashItemEntity(
        title = "where should we send the money?",
        subtitle = "amount will be credited to the bank account. EMI will also be debited from this bank account",
        ctaText = "Tap for 1-click KYC",
        card = null,
        plans = listOf(
            PlanEntity(emi = null, duration = null, title = "HDFC BANK", subtitle = "897458935", tag = null),
            PlanEntity(emi = null, duration = null, title = "SBI", subtitle = "897453435", tag = null),
            PlanEntity(emi = null, duration = null, title = "PNB", subtitle = "8974589334535", tag = null)
        ),
        footer = "change account",
        isOpenState = true,
        key1 = null,
        key2 = null
    )
)
