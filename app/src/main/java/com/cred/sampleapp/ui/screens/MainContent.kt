package com.cred.sampleapp.ui.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cred.sampleapp.data.entities.StashItemEntity
import com.cred.sampleapp.ui.components.CircularAmountPicker
import com.cred.sampleapp.ui.components.EmptyDataErrorComponent
import com.cred.sampleapp.ui.theme.DeepCharcoal
import com.cred.sampleapp.ui.theme.MidnightBlue
import com.cred.sampleapp.ui.theme.SlateGray
import com.cred.sampleapp.ui.theme.limeGreen
import com.cred.sampleapp.ui.theme.royalBlue
import com.cred.sampleapp.ui.theme.steelBlue
import com.cred.sampleapp.utils.formatCurrencyWithRupee
import com.cred.sampleapp.utils.rememberCurrencyVisualTransformation
import com.cred.sampleapp.utils.stashItems
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun MainContent(
    bottomSheetState: BottomSheetState,
    configuration: Configuration,
    data: List<StashItemEntity>,
) {

    val context = LocalContext.current
    val currencyVisualTransformation = rememberCurrencyVisualTransformation(currency = "INR")
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    val title by rememberSaveable { mutableStateOf(data.getOrNull(0)?.title ?: "") }
    val subtitle by rememberSaveable { mutableStateOf(data.getOrNull(0)?.subtitle ?: "") }
    val cardHeader by rememberSaveable { mutableStateOf(data.getOrNull(0)?.card?.header ?: "") }
    val cardDescription by rememberSaveable { mutableStateOf(data.getOrNull(0)?.card?.description ?: "") }
    val minAmount by rememberSaveable { mutableStateOf(data.getOrNull(0)?.card?.minRange ?: 0) }
    val maxAmount by rememberSaveable { mutableStateOf(data.getOrNull(0)?.card?.maxRange ?: 0) }
    val footer by rememberSaveable { mutableStateOf(data.getOrNull(0)?.footer ?: "") }
    val key1 by rememberSaveable { mutableStateOf(data.getOrNull(0)?.key1 ?: "") }
    val ctaText by rememberSaveable { mutableStateOf(data.getOrNull(0)?.ctaText ?: "") }
    var creditAmount by rememberSaveable { mutableStateOf((data.getOrNull(0)?.card?.minRange ?: 0).toString()) }
    var progress by rememberSaveable { mutableDoubleStateOf((data.getOrNull(0)?.card?.minRange ?: 0) * 360.0 / (data.getOrNull(0)?.card?.maxRange ?: 1).toDouble()) }

    Column(
        Modifier.fillMaxSize().clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            focusManager.clearFocus()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MidnightBlue),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(30.dp),
                backgroundColor = DeepCharcoal,
                elevation = 4.dp
            ) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        Modifier.fillMaxSize()
                    ){

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
                                        color = SlateGray
                                    )

                                    Text(
                                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                                        text = formatCurrencyWithRupee(creditAmount),
                                        fontSize = 16.sp,
                                        color = SlateGray
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
                            Modifier.padding(32.dp)
                        ) {

                            Text(
                                modifier = Modifier
                                    .padding(top = 4.dp, bottom = 4.dp),
                                text = title,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = steelBlue
                            )

                            Text(
                                modifier = Modifier
                                    .padding(top = 4.dp, bottom = 4.dp),
                                text = subtitle,
                                fontSize = 12.sp,
                                color = steelBlue
                            )

                            Card(
                                modifier = Modifier
                                    .height(configuration.screenWidthDp.dp )
                                    .width(configuration.screenWidthDp.dp)
                                    .padding(top=32.dp),
                                shape = RoundedCornerShape(16.dp),
                                backgroundColor = Color.White,
                                elevation = 4.dp
                            ) {

                                Box(
                                    modifier = Modifier
                                    ,
                                    contentAlignment = Alignment.Center
                                ) {

                                    Box(
                                        modifier = Modifier,
                                        contentAlignment = Alignment.Center
                                    ) {

                                        CircularAmountPicker(
                                            modifier = Modifier
                                                .padding(32.dp),
                                            initialAngle = progress
                                        )
                                        {
                                            creditAmount = (it*maxAmount).toInt().toString()
                                            if( creditAmount.toInt() < minAmount )
                                                creditAmount = minAmount.toString()
                                        }

                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {

                                            Text(
                                                modifier = Modifier.padding(
                                                    top = 4.dp,
                                                    bottom = 4.dp
                                                ),
                                                text = cardHeader,
                                                fontSize = 12.sp,
                                                color = Color.DarkGray
                                            )

                                            BasicTextField(
                                                modifier = Modifier.padding(
                                                    top = 4.dp,
                                                    bottom = 4.dp
                                                ),
                                                value = creditAmount,
                                                onValueChange = { newValue ->
                                                    val trimmed = newValue.trimStart('0').takeIf { it.all { char -> char.isDigit() } } ?: "0"
                                                    val numericValue = trimmed.toLongOrNull() ?: 0

                                                    if( numericValue < 10000000 )
                                                        creditAmount = trimmed

                                                    if (numericValue in minAmount..maxAmount) {
                                                        progress = (numericValue.toDouble()/maxAmount)*360.00
                                                    }else if( numericValue > maxAmount ){
                                                        progress = (maxAmount.toDouble()/maxAmount)*360.00
                                                    }else if( numericValue < minAmount ){
                                                        progress = (minAmount.toDouble()/maxAmount)*360.00
                                                    }
                                                },

                                                visualTransformation = currencyVisualTransformation,
                                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                                                textStyle = TextStyle(
                                                    fontSize = 25.sp,
                                                    color = Color.Black,
                                                    fontWeight = FontWeight.Bold,
                                                    textAlign = TextAlign.Center
                                                ),
                                                decorationBox = { innerTextField ->
                                                    innerTextField()
                                                }
                                            )
                                            Text(
                                                modifier = Modifier.padding(
                                                    top = 4.dp,
                                                    bottom = 4.dp
                                                ),
                                                text = cardDescription,
                                                fontSize = 12.sp,
                                                color = limeGreen
                                            )
                                        }
                                    }

                                    Text(
                                        modifier = Modifier
                                            .padding( start= 20.dp, end=20.dp, bottom=20.dp )
                                            .align(Alignment.BottomEnd),
                                        text = footer,
                                        textAlign = TextAlign.Center,
                                        fontSize = 12.sp,
                                        color = Color.DarkGray
                                    )
                                }
                            }
                        }
                    }
                    Button(
                        onClick = {
                            scope.launch {
                                focusManager.clearFocus()

                                val trimmed = creditAmount.trimStart('0').takeIf { it.all { char -> char.isDigit() } } ?: "0"
                                val numericValue = trimmed.toLongOrNull() ?: 0

                                if(numericValue < minAmount) {
                                    Toast.makeText(context, "Minimum amount is $minAmount, value adjusted to $minAmount", Toast.LENGTH_SHORT).show()
                                    creditAmount = minAmount.toString()
                                    progress = (minAmount.toDouble()/maxAmount)*360.00
                                }

                                else if( numericValue > maxAmount) {
                                    Toast.makeText(context, "Maximum amount is $maxAmount, value adjusted to $maxAmount", Toast.LENGTH_SHORT).show()
                                    creditAmount = maxAmount.toString()
                                    progress = (maxAmount.toDouble()/maxAmount)*360.00
                                }

                                bottomSheetState.expand()
                            }
                        },
                        modifier = Modifier
                            .height(80.dp)
                            .align(Alignment.BottomEnd)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = royalBlue,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            color = Color.White,
                            text = ctaText,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun MainContentPreview() {
    MainContent(
        bottomSheetState = BottomSheetState(
            BottomSheetValue.Collapsed,
            Density(LocalContext.current)
        ),
        configuration = LocalConfiguration.current,
        data = stashItems
    )
}