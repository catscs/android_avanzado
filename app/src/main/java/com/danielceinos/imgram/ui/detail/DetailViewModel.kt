package com.danielceinos.imgram.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danielceinos.imgram.data.imgurapi.Image
import com.danielceinos.imgram.usecases.DetailImageUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class DetailViewModel(private val useCase: DetailImageUseCase): ViewModel() {

    private val _event = MutableLiveData<Event>()
    val event: LiveData<Event>
        get() = _event

    fun getAlbumImages(albumId: String) {
        runBlocking {
            launch {
                try {
                    _event.value = Event.Loading
                    _event.value = Event.Content(useCase.execute(albumId))
                } catch (e: Exception) {
                    _event.value = Event.Content(emptyList())
                }
            }
        }
    }

    sealed class Event {
        object Loading: Event()
        data class Content(val image: List<Image>) : Event()
    }
}
