package com.cred.sampleapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling. preview.PreviewParameterProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cred.sampleapp.ui.viewModel.StashScreenViewModel
import com.cred.sampleapp.ui.viewModel.StashScreenViewState
import com.cred.sampleapp.data.entities.StashItemEntity
import com.cred.sampleapp.ui.components.EmptyDataErrorComponent
import com.cred.sampleapp.ui.components.ProgressIndicatorComponent
import com.cred.sampleapp.ui.theme.CharcoalGray
import com.cred.sampleapp.ui.theme.DarkSlateGray
import kotlinx.coroutines.launch
import com.cred.sampleapp.ui.theme.SampleAppTheme
import kotlinx.coroutines.CoroutineScope

@Preview
@Composable
fun StashScreen(
) {
    val viewModel : StashScreenViewModel = viewModel()
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val configuration = LocalConfiguration.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(true) {
        viewModel.loadData()
    }

    StashScreen(
        uiState = state,
        configuration = configuration,
        scope = scope,
        onRetry = {
            viewModel.loadData()
        }
    )
}

@Composable
fun StashScreen(
    uiState: StashScreenViewState,
    configuration: Configuration,
    scope: CoroutineScope,
    onRetry: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .background(DarkSlateGray)
        ) {

            when (uiState) {

                is StashScreenViewState.Content -> {

                    if( uiState.list.isNullOrEmpty())
                        EmptyDataErrorComponent(
                            isEmptyData = true,
                            isRetryEnabled = true,
                            onRetry = { onRetry.invoke() }
                        )
                    else {
                        StashScreenContent(
                            configuration = configuration,
                            scope = scope,
                            data = uiState.list
                        )
                    }
                }

                is StashScreenViewState.Error -> {
                    EmptyDataErrorComponent(
                        isEmptyData = true,
                        isRetryEnabled = true,
                        onRetry = { onRetry.invoke() }
                    )
                }

                is StashScreenViewState.Loading -> {
                    ProgressIndicatorComponent()
                }

                is StashScreenViewState.Initial -> {
                    StashScreenContent(
                        configuration = configuration,
                        scope = scope
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
 fun StashScreenContent(
    configuration: Configuration,
    scope: CoroutineScope,
    data: List<StashItemEntity> = listOf()
){

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed,
            confirmStateChange = { bottomSheetValue: BottomSheetValue ->
                true
            }
        )
    )

    val bottomSheetScaffoldState1 = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed,
            confirmStateChange = { bottomSheetValue: BottomSheetValue ->
                true
            }
        )
    )

    BackHandler(
        enabled = bottomSheetScaffoldState.bottomSheetState.isExpanded ||
                bottomSheetScaffoldState1.bottomSheetState.isExpanded,
        onBack = {
            scope.launch {
                if (bottomSheetScaffoldState1.bottomSheetState.isExpanded)
                    bottomSheetScaffoldState1.bottomSheetState.collapse()
                else if (bottomSheetScaffoldState.bottomSheetState.isExpanded)
                    bottomSheetScaffoldState.bottomSheetState.collapse()
            }
        }
    )

    Spacer(Modifier.height(20.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = {},

            ) {
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(CharcoalGray)
                    .padding(5.dp),
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.White
            )
        }

        IconButton(
            onClick = {},
        ) {
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(CharcoalGray)
                    .padding(5.dp),
                imageVector = Icons.Default.QuestionMark,
                contentDescription = "Help",
                tint = Color.White
            )
        }
    }

    Spacer(Modifier.height(10.dp))

    BottomSheetScaffold(
        modifier = Modifier
            .heightIn(
                min = configuration.screenHeightDp.dp - 50.dp,
                max = configuration.screenHeightDp.dp - 50.dp
            ),
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
            topStart = 30.dp,
            topEnd = 30.dp
        ),
        sheetGesturesEnabled = true,
        sheetContent = {

            BottomSheetScaffold(
                modifier = Modifier
                    .heightIn(
                        min = configuration.screenHeightDp.dp - 150.dp,
                        max = configuration.screenHeightDp.dp - 150.dp
                    ),
                scaffoldState = bottomSheetScaffoldState1,
                sheetElevation = 8.dp,
                sheetShape = RoundedCornerShape(
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp,
                    topStart = 30.dp,
                    topEnd = 30.dp
                ),
                sheetGesturesEnabled = true,
                sheetContent = {
                    SheetContent2(
                        configuration = configuration,
                        data= data
                    )
                },
                sheetPeekHeight = 0.dp,
            ) {
                SheetContent1(
                    configuration = configuration,
                    bottomSheetState = bottomSheetScaffoldState1.bottomSheetState,
                    data= data
                )
            }

        },
        sheetPeekHeight = 0.dp,
    ) {
        MainContent(
            bottomSheetState = bottomSheetScaffoldState.bottomSheetState,
            configuration = configuration,
            data = data
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Preview
@Composable
private fun StashScreenPreview(
    @PreviewParameter(BottomSheetStateProvider::class)
    state: BottomSheetValue
) {
    SampleAppTheme {
        StashScreen(
            uiState= StashScreenViewState.Content(listOf()),
            configuration = LocalConfiguration.current,
            scope = rememberCoroutineScope()
        )
    }
}

private class BottomSheetStateProvider : PreviewParameterProvider<BottomSheetValue> {
    override val values: Sequence<BottomSheetValue>
        get() = sequenceOf(
            BottomSheetValue.Collapsed,
            BottomSheetValue.Expanded
        )
}