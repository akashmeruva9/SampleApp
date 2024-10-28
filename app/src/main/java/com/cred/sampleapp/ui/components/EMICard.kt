package com.cred.sampleapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cred.sampleapp.data.entities.PlanEntity

@Composable
fun EMICard(
    modifier: Modifier = Modifier,
    emiCard: PlanEntity,
    color: Color,
    checked: Boolean,
    onSelect: () -> Unit
) {

    Box(
        modifier = modifier
            .size(height = 180.dp, width = 140.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color)
            .clickable {
                onSelect.invoke()
            }

    ) {

        Column(
            modifier= Modifier.fillMaxSize()
                .padding(bottom=20.dp, top=20.dp, start=12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){

            FavoriteButton(
                color = Color.White,
                isChecked = checked,
                onFavoriteChange = onSelect
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    modifier = Modifier.padding(2.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    text = emiCard.emi!!.dropLast(3),
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(2.dp),
                    color = Color.White,
                    text = emiCard.emi.takeLast(3),
                    fontSize = 12.sp
                )
            }

            Text(
                color = Color.White,
                text = emiCard.title,
                fontSize = 12.sp
            )

            Text(
                modifier = Modifier
                    .clickable {  }
                    .drawBehind {
                        val underlineHeight = 1.dp.toPx()
                        val space = 4.dp.toPx()
                        val dotWidth = 2.dp.toPx()
                        val y = size.height - underlineHeight

                        var startX = 0f
                        while (startX < size.width) {
                            drawRect(
                                color = Color.White,
                                topLeft = Offset(x = startX, y = y),
                                size = Size(width = dotWidth, height = underlineHeight)
                            )
                            startX += dotWidth + space
                        }
                    },
                color = Color.White,
                text = emiCard.subtitle,
                fontSize = 12.sp
            )

        }
    }

}

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color(0xffE91E63),
    isChecked: Boolean,
    onFavoriteChange: () -> Unit
) {

    IconToggleButton(
        checked = isChecked,
        onCheckedChange = {
            onFavoriteChange()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.size(24.dp)
        ) {
            Canvas(
                modifier = Modifier.matchParentSize()
            ) {
                drawCircle(
                    color = color,
                    style = Stroke(width = if (isChecked) 3f else 1f) // Customize the width
                )
            }

            if (isChecked) {
                Icon(
                    imageVector = Icons.Default.Check, // Use a check icon
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(16.dp) // Adjust size to fit inside circle
                )
            }
        }
    }
}

@Preview
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(device = Devices.PIXEL_C)
@Composable
fun HorizontalSnackCardPreview() {
//    val emiCard = emiPlans.first()
//    HorizontalSnackCard(emiCard = emiCard)
}

@Preview
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(device = Devices.PIXEL_C)
@Composable
fun FavoriteButtonPreview() {
//    FavoriteButton(
//        checked = false,
//        onFavoriteChange = {}
//    )
}