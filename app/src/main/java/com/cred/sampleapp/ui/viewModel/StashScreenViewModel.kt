package com.cred.sampleapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cred.sampleapp.data.repository.StashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StashScreenViewModel @Inject constructor(
    private val repository: StashRepository
) : ViewModel() {

    // this ensures that _viewState can be edited inSide the view model but not outside
    private val _viewState = MutableStateFlow<StashScreenViewState>(StashScreenViewState.Initial)

    // and view state can only be read not edited because it is a stateFlow not MutableStateFlow
    val viewState: StateFlow<StashScreenViewState> get() = _viewState

    // used to Load Data
    fun loadData( )
    {
        // creating a coroutine
        // here scope defines the lifetime of the coroutine
        viewModelScope.launch {
            _viewState.value = StashScreenViewState.Loading
            Log.e("MainContentViewModel", "Loading")

            try {
                val list = repository.getDomainItems()
                _viewState.value = StashScreenViewState.Content(list = list)
                Log.e("MainContentViewModel", "Show Content")
            } catch (e: Exception) {
                Log.e("MainContentViewModel", "Error loading data", e)
                _viewState.value = StashScreenViewState.Error
            }
        }
    }
}