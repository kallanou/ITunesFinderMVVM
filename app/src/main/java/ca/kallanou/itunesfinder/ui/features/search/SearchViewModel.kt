package ca.kallanou.itunesfinder.ui.features.search

import android.arch.lifecycle.MutableLiveData
import android.databinding.adapters.TextViewBindingAdapter
import android.view.View
import ca.kallanou.itunesfinder.domain.models.Album
import ca.kallanou.itunesfinder.domain.usecases.SearchAlbumUseCase
import ca.kallanou.itunesfinder.extensions.default
import ca.kallanou.itunesfinder.ui.base.framework.SingleLiveEvent
import ca.kallanou.itunesfinder.ui.base.framework.base.BaseViewModel
import java.text.Normalizer

class SearchViewModel(private val searchMovieUseCase: SearchAlbumUseCase): BaseViewModel() {

    lateinit var adapter: SearchAlbumAdapter

    private var searchText = ""
    private var searchTextChanged: TextViewBindingAdapter.OnTextChanged

    val isLoading: MutableLiveData<Int> = MutableLiveData<Int>().default(View.GONE)
    val isEmpty: MutableLiveData<Int> = MutableLiveData<Int>().default(View.GONE)
    var errorState = SingleLiveEvent<Throwable>(null)

    init {
        searchTextChanged = TextViewBindingAdapter.OnTextChanged { text, _, _, _ -> searchText = text.toString() }
    }

    fun search(term: String) {
        if (term.isEmpty()) {
            isLoading.value = View.GONE

        } else {
            isEmpty.value = View.GONE
            isLoading.value = View.VISIBLE
            performSearch(term)
        }
    }

    fun searchClicked() {
        var escapeText =  Normalizer.normalize(searchText, Normalizer.Form.NFD).replace(Regex("[^A-Za-z0-9 ]"), "")
        escapeText = escapeText.replace(" ", "+")
        search(escapeText)
    }

    fun onSearchTextChanged(): TextViewBindingAdapter.OnTextChanged {
        return searchTextChanged
    }

    private fun performSearch(term: String) {
        addDisposable(searchMovieUseCase.search(term)
                .subscribe({ albums ->
                    displayResult(albums, null)

                }, {
                    displayResult(listOf(), it)
                }))
    }

    private fun displayResult(albums: List<Album>, error: Throwable?) {
        isLoading.value = View.GONE
        if(albums.isEmpty() || error != null) {
            isEmpty.value = View.VISIBLE
        } else {
            isEmpty.value = View.GONE
        }

        adapter.updateAlbums(albums)
        errorState.value = error
    }

}