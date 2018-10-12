package ca.kallanou.itunesfinder.domain.repositories

import ca.kallanou.itunesfinder.domain.models.Album
import io.reactivex.Observable

interface RemoteAlbumsRepository {

    fun searchAlbums(term: String): Observable<List<Album>>

}
