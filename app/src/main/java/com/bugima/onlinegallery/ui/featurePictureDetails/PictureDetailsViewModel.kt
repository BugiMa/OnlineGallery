package com.bugima.onlinegallery.ui.featurePictureDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bugima.onlinegallery.domain.model.Picture
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = PictureDetailsViewModel.Factory::class)
class PictureDetailsViewModel @AssistedInject constructor(
    @Assisted val picture: Picture
) : ViewModel() {
    @AssistedFactory
    interface Factory {
        fun create(picture: Picture): PictureDetailsViewModel
    }

    private val _state = MutableStateFlow(PictureDetailsViewState(picture))
    val state: StateFlow<PictureDetailsViewState>
        get() = _state.asStateFlow()

    private val _sideEffects = Channel<PictureDetailsSideEffect>()
    val sideEffects = _sideEffects.receiveAsFlow()

    fun onNewEvent(event: PictureDetailsEvent) {
        when (event) {
            PictureDetailsEvent.OnBackClicked -> navigateUp()
            is PictureDetailsEvent.OnUrlClicked -> openBrowser(event.url)
        }
    }

    private fun navigateUp() {
        viewModelScope.launch {
            _sideEffects.send(PictureDetailsSideEffect.NavigateUp)
        }
    }

    private fun openBrowser(url: String) {
        viewModelScope.launch {
            _sideEffects.send(PictureDetailsSideEffect.OpenUrl(url))
        }
    }
}
