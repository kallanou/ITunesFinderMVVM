package ca.kallanou.itunesfinder.domain.usecases

import ca.kallanou.itunesfinder.domain.framework.Transformer
import ca.kallanou.itunesfinder.domain.framework.UseCase
import ca.kallanou.itunesfinder.domain.repositories.RemoteAlbumsRepository
import ca.kallanou.itunesfinder.domain.models.Album
import io.reactivex.Observable

class SearchAlbumUseCase(transformer: Transformer<List<Album>>,
                         private val albumRepo: RemoteAlbumsRepository): UseCase<List<Album>>(transformer) {

    companion object {
        private const val PARAM_SEARCH_QUERY = "param:term"
    }

    fun search(query: String): Observable<List<Album>> {
        val data = HashMap<String, String>()
        data[PARAM_SEARCH_QUERY] = query
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<List<Album>> {
        val query = data?.get(PARAM_SEARCH_QUERY)
        query?.let {
            return albumRepo.searchAlbums(it as String)
        } ?: return Observable.just(emptyList())
    }

}