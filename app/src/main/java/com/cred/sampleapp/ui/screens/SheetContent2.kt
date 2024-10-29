package com.cred.sampleapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cred.sampleapp.R
import com.cred.sampleapp.data.entities.StashItemEntity
import com.cred.sampleapp.ui.theme.cadetBlue
import com.cred.sampleapp.ui.theme.lightSteelBlue
import com.cred.sampleapp.ui.theme.richBlack
import com.cred.sampleapp.ui.theme.royalBlue
import com.cred.sampleapp.utils.stashItems

@Composable
fun SheetContent2(
    configuration: Configuration,
    data: List<StashItemEntity>
) {

    val title by rememberSaveable { mutableStateOf(data.getOrNull(2)?.title ?: "") }
    val subtitle by rememberSaveable { mutableStateOf(data.getOrNull(2)?.subtitle ?: "") }
    val footer by rememberSaveable { mutableStateOf(data.getOrNull(2)?.footer ?: "") }
    val ctaText by rememberSaveable { mutableStateOf(data.getOrNull(2)?.ctaText ?: "") }
    val banks = rememberSaveable { mutableStateOf(data.getOrNull(2)?.plans ?: emptyList()) }
    val currentIndex = rememberSaveable { mutableStateOf(0) }


    Column(
        modifier = Modifier
            .heightIn(
                min = configuration.screenHeightDp.dp-250.dp,
                max = configuration.screenHeightDp.dp-250.dp)
            .background(richBlack)
    ){
        Column(
            modifier = Modifier.padding(32.dp)
        ) {

            Text(
                text = title,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp),
                color = lightSteelBlue
            )

            Text(
                text = subtitle,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp),
                color = lightSteelBlue
            )

            Row(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth(),
            ) {
                Card(
                    modifier = Modifier
                        .height(48.dp)
                        .width(48.dp),
                    elevation = 2.dp,
                    shape = RoundedCornerShape(12.dp)
                ) {

                    val painter : Painter = when (banks.value.getOrNull(currentIndex.value)?.title) {
                        "PNB" ->  painterResource(id = R.drawable.pnbbank)
                        "SBI" ->  painterResource(id = R.drawable.sbiank)
                        "HDFC BANK" -> painterResource(id = R.drawable.hdfcbank)
                        else -> painterResource(id = R.drawable.pnbbank)
                    }

                    Image(
                        modifier = Modifier
                            .height(48.dp)
                            .width(48.dp),
                        contentScale = ContentScale.FillBounds,
                        painter = painter,
                        contentDescription = null
                    )
                }

                Column(
                    modifier = Modifier.padding(start = 8.dp)
                )
                {
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = banks.value.getOrNull(currentIndex.value)?.title ?: "",
                        fontSize = 16.sp,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = banks.value.getOrNull(currentIndex.value)?.subtitle ?: "",
                        fontSize = 12.sp,
                        color= Color.LightGray
                    )
                }
            }

            OutlinedButton(
                onClick = {
                    currentIndex.value = (currentIndex.value + 1) % banks.value.size
                },
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 8.dp)
                    .height(40.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = richBlack,
                    contentColor = cadetBlue,
                ),
                border = BorderStroke(1.dp, cadetBlue)
            ) {
                Text(
                    text = footer,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {},
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = royalBlue,
                contentColor = Color.White
            ),
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            )
        ) {
            Text(
                color = Color.White,
                text= ctaText,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun SheetContent2Preview() {
    SheetContent2(
        configuration = LocalConfiguration.current,
        data = stashItems
    )
}