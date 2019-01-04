package ca.kallanou.itunesfinder.presentation.ui.features.search

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ca.kallanou.itunesfinder.domain.models.Album
import ca.kallanou.itunesfinder.domain.usecases.SearchAlbumUseCase
import ca.kallanou.itunesfinder.presentation.base.framework.SingleLiveEvent
import ca.kallanou.itunesfinder.presentation.base.framework.base.BaseViewModel
import ca.kallanou.itunesfinder.presentation.base.framework.extensions.default
import java.text.Normalizer

class SearchViewModel(private val searchMovieUseCase: SearchAlbumUseCase): BaseViewModel() {

    lateinit var adapter: SearchAlbumAdapter

    val isLoading = MutableLiveData<Int>().default(View.GONE)
    val isEmpty = MutableLiveData<Int>().default(View.GONE)
    val searchTextAlbum = MutableLiveData<String>()

    private val _errorState = SingleLiveEvent<Throwable>(null)
    val errorState: LiveData<Throwable>
        get() = _errorState

    fun searchClicked() {
        var escapeText =  Normalizer.normalize(searchTextAlbum.value, Normalizer.Form.NFD).replace(Regex("[^A-Za-z0-9 ]"), "")
        escapeText = escapeText.replace(" ", "+")

        if (escapeText.isEmpty()) {
            isLoading.value = View.GONE

        } else {
            isEmpty.value = View.GONE
            isLoading.value = View.VISIBLE
            performSearch(escapeText)
        }
    }

    private fun performSearch(term: String) {
        addDisposable(searchMovieUseCase.search(term)
                .subscribe({ albums ->
                    displayResult(albums?.sortedByDescending { album: Album -> album.releaseDate }, null)

                }, {
                    displayResult(listOf(), it)
                }))
    }

    private fun displayResult(albums: List<Album>?, error: Throwable?) {
        isLoading.value = View.GONE
        if(albums.isNullOrEmpty() || error != null) {
            isEmpty.value = View.VISIBLE
            adapter.updateAlbums(listOf())
        } else {
            isEmpty.value = View.GONE
            adapter.updateAlbums(albums)
        }

        _errorState.value = error
    }

}