package com.bugima.onlinegallery.ui.featurePictureBrowser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bugima.onlinegallery.domain.model.Picture
import com.bugima.onlinegallery.domain.usecase.FetchPicturesUseCase
import com.bugima.onlinegallery.domain.usecase.RefreshPicturesUseCase
import com.bugima.onlinegallery.util.AppException
import com.bugima.onlinegallery.util.wrapper.Resource
import com.bugima.onlinegallery.util.wrapper.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PictureBrowserViewModel @Inject constructor(
    private val fetchPicturesUseCase: FetchPicturesUseCase,
    private val refreshImagesUseCase: RefreshPicturesUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(PictureBrowserViewState())
    val state: StateFlow<PictureBrowserViewState>
        get() = _state.asStateFlow()

    private val _sideEffects = Channel<PictureBrowserSideEffect>()
    val sideEffects = _sideEffects.receiveAsFlow()

    fun onNewEvent(event: PictureBrowserEvent) {
        when (event) {
            PictureBrowserEvent.Initialize -> initialize()
            PictureBrowserEvent.LoadMorePictures -> loadMoreImages()
            PictureBrowserEvent.RefreshPictures -> refreshImages()
            is PictureBrowserEvent.OnPictureClicked -> navigateToPictureDetail(event.picture)
        }
    }

    private fun initialize() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = fetchPicturesUseCase.execute()) {
                is Result.Success -> _state.updateSuccess(result.data)
                is Result.Failure -> _state.updateError(result.exception)
            }
        }
    }

    private fun loadMoreImages() {
        viewModelScope.launch(Dispatchers.IO) {
            if (state.value.isMoreLoading) return@launch
            _state.update { it.copy(isMoreLoading = true) }
            when (val result = fetchPicturesUseCase.execute()) {
                is Result.Success -> _state.updateSuccess(result.data)
                is Result.Failure -> _state.updateError(result.exception)
            }
        }
    }

    private fun refreshImages() {
        _state.reset()
        refreshImagesUseCase.execute()
        initialize()
    }

    private fun navigateToPictureDetail(picture: Picture) {
        viewModelScope.launch {
            _sideEffects.send(PictureBrowserSideEffect.NavigateToPictureDetails(picture))
        }
    }

    private fun MutableStateFlow<PictureBrowserViewState>.reset() {
        update {
            it.copy(
                pictureList = Resource.Loading,
                isMoreLoading = false,
                noMorePictures = false
            )
        }
    }

    private fun MutableStateFlow<PictureBrowserViewState>.updateSuccess(data: List<Picture>) {
        update {
            it.copy(
                pictureList = Resource.Success(data),
                isMoreLoading = false
            )
        }
    }

    private fun MutableStateFlow<PictureBrowserViewState>.updateError(exception: AppException) {
        update {
            when (exception) {
                is AppException.NoMoreDataException -> it.copy(
                    isMoreLoading = false,
                    noMorePictures = true
                )
                else -> it.copy(
                    pictureList = Resource.Error(exception),
                    isMoreLoading = false
                )
            }
        }
    }
}
