package ca.kallanou.itunesfinder.view.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ca.kallanou.itunesfinder.usecases.SearchAlbum

class SearchVMFactory(val searchAlbum: SearchAlbum): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(searchAlbum) as T
    }

}