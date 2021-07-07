package ca.kallanou.itunesfinder.data.repositories

import ca.kallanou.itunesfinder.data.networking.apis.ItunesApi
import ca.kallanou.itunesfinder.data.networking.models.toAlbums
import ca.kallanou.itunesfinder.domain.models.Album
import ca.kallanou.itunesfinder.domain.repositories.RemoteAlbumsRepository
import io.reactivex.Observable

class RemoteAlbumsRepositoryImpl(private val itunesApi: ItunesApi) : RemoteAlbumsRepository {

    override fun searchAlbums(term: String): Observable<List<Album>> {
        return itunesApi.searchAlbums(term).map { results ->
            results.albums.toAlbums()
        }
    }

}