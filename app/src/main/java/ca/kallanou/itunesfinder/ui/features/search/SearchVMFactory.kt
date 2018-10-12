package ca.kallanou.itunesfinder.ui.features.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ca.kallanou.itunesfinder.domain.usecases.SearchAlbumUseCase

@Suppress("UNCHECKED_CAST")
class SearchVMFactory(private val searchAlbumUseCase: SearchAlbumUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(searchAlbumUseCase) as T
    }

}