package com.cred.sampleapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ehsanmsz.mszprogressindicator.progressindicator.BallScaleRippleMultipleProgressIndicator

@Preview(showSystemUi = true)
@Composable
fun ProgressIndicatorComponent(
    modifier: Modifier = Modifier.fillMaxSize()
) {
    Column(
        modifier = modifier
            .background(Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BallScaleRippleMultipleProgressIndicator(
            modifier = Modifier,
            color = Color.White,
            animationDuration = 800,
            animationDelay = 200,
            startDelay = 0,
            diameter = 40.dp,
            strokeWidth = 1.5.dp,
            ballCount = 4
        )

        Text(
            modifier = Modifier.padding(top=32.dp),
            text= "Loading...",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun ProgressIndicatorComponentPreview() {
    ProgressIndicatorComponent(

    )
}