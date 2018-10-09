package ca.kallanou.itunesfinder.data.repositories

import ca.kallanou.itunesfinder.data.api.Api
import ca.kallanou.itunesfinder.data.mappers.AlbumDataEntityMapper
import ca.kallanou.itunesfinder.model.Album
import io.reactivex.Observable

class RemoteAlbumsRepositoryImpl(private val api: Api): RemoteAlbumsRepository {

    private val albumDataMapper = AlbumDataEntityMapper()

    override fun searchAlbums(term: String): Observable<List<Album>> {
        return api.searchAlbums(term).map { results ->
            results.albums.map { albumDataMapper.mapFrom(it) }
        }
    }

}