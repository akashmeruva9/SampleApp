package com.cred.sampleapp.ui.viewModel

import com.cred.sampleapp.data.entities.StashItemEntity


// this is the view state for the screen that shows the main content
// the class is sealed which ensures that we always handle all scenarios
sealed class StashScreenViewState {

    // object because there is no data involved
    data object Initial : StashScreenViewState()
    data object Loading : StashScreenViewState()
    data class Content( val list: List<StashItemEntity>) : StashScreenViewState()
    data object Error : StashScreenViewState()
}