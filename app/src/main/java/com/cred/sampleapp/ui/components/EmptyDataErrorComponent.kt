package com.cred.sampleapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cred.sampleapp.R

@Composable
fun EmptyDataErrorComponent(
    modifier: Modifier = Modifier.fillMaxSize(),
    isEmptyData: Boolean = false,
    message: String? = null,
    isRetryEnabled: Boolean = false,
    onRetry: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 12.dp),
            imageVector = Icons.Filled.Info,
            contentDescription = null,
            tint = Color.White
        )

        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = message ?: if (isEmptyData) stringResource(id = R.string.no_data) else stringResource(id = R.string.something_went_wrong),
            style = TextStyle(fontSize = 20.sp),
            color = Color.White,
            textAlign = TextAlign.Center
        )

        if (isRetryEnabled) {
            FilledTonalButton(
                modifier = Modifier.padding(top = 8.dp),
                onClick = { onRetry.invoke() }
            ) {
                Text(text = stringResource(id = R.string.retry))
            }
        }
    }
}