package com.cred.sampleapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cred.sampleapp.data.entities.StashItemEntity
import com.cred.sampleapp.data.entities.PlanEntity
import com.cred.sampleapp.ui.components.EMICard
import com.cred.sampleapp.ui.theme.cadetBlue
import com.cred.sampleapp.ui.theme.darkSlateGray2
import com.cred.sampleapp.ui.theme.lightSteelBlue
import com.cred.sampleapp.ui.theme.royalBlue
import com.cred.sampleapp.ui.theme.tealGray
import com.cred.sampleapp.utils.emiCardColors
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun SheetContent1(
    configuration: Configuration,
    bottomSheetState: BottomSheetState,
    data: List<StashItemEntity>
) {

    val scope = rememberCoroutineScope()

    val title by rememberSaveable { mutableStateOf(data.getOrNull(1)?.title ?: "") }
    val subtitle by rememberSaveable { mutableStateOf(data.getOrNull(1)?.subtitle ?: "") }
    val footer by rememberSaveable { mutableStateOf(data.getOrNull(1)?.footer ?: "") }
    val key1 by rememberSaveable { mutableStateOf(data.getOrNull(1)?.key1 ?: "") }
    val key2 by rememberSaveable { mutableStateOf(data.getOrNull(1)?.key2 ?: "") }
    val ctaText by rememberSaveable { mutableStateOf(data.getOrNull(1)?.ctaText ?: "") }
    val emiPlans = rememberSaveable { mutableStateOf(data.getOrNull(1)?.plans ?: emptyList()) }
    val checkBoxes = remember {
        mutableStateListOf<Boolean>().apply {
            repeat(emiPlans.value.size) { add(false) }
            if (isNotEmpty()) this[0] = true
        }
    }
    val selectedPlan = rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .heightIn(
                min = configuration.screenHeightDp.dp-150.dp,
                max = configuration.screenHeightDp.dp-150.dp)
            .background(darkSlateGray2),
    ) {
        if (bottomSheetState.isExpanded) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(start= 28.dp, top= 28.dp, end= 28.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                )
                {
                    Text(
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                        text = key1,
                        fontSize = 12.sp,
                        color = tealGray
                    )

                    Text(
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                        text = emiPlans.value[selectedPlan.value].emi!!,
                        fontSize = 16.sp,
                        color = tealGray
                    )
                }

                Column(
                    modifier = Modifier
                )
                {
                    Text(
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                        text = key2,
                        fontSize = 12.sp,
                        color = tealGray
                    )

                    Text(
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                        text = emiPlans.value[selectedPlan.value].duration!!,
                        fontSize = 16.sp,
                        color = tealGray
                    )
                }

                IconButton(
                    onClick = {
                        scope.launch {
                            bottomSheetState.collapse()
                        }
                    },
                ) {
                    Icon(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.Transparent)
                            .padding(5.dp),
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Close",
                        tint = Color.White
                    )
                }

            }
        }

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

            LazyRow(
                modifier = Modifier.padding( top= 32.dp, bottom = 32.dp ),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                content = {
                    items(emiPlans.value) { emiCards: PlanEntity ->
                        EMICard(
                            emiCard = emiCards,
                            color = emiCardColors.getOrElse(emiPlans.value.indexOf(emiCards)) {
                                Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f)
                            },
                            checked = checkBoxes[emiPlans.value.indexOf(emiCards)],
                            onSelect = {
                                selectedPlan.value = emiPlans.value.indexOf(emiCards)
                                checkBoxes.forEachIndexed { i, _ ->
                                    checkBoxes[i] = i == selectedPlan.value
                                }
                            }
                        )
                    }
                }
            )

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .height(40.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = darkSlateGray2,
                    contentColor = cadetBlue
                ),
                border = BorderStroke(1.dp, cadetBlue)
            ) {
                Text(
                    color = cadetBlue,
                    text = footer,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                scope.launch {
                    bottomSheetState.expand()
                }
            },
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
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
