package ca.kallanou.itunesfinder.presentation.ui.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.kallanou.itunesfinder.domain.usecases.SearchAlbumUseCase

@Suppress("UNCHECKED_CAST")
class SearchVMFactory(private val searchAlbumUseCase: SearchAlbumUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(searchAlbumUseCase) as T
    }

}