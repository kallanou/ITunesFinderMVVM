package ca.kallanou.itunesfinder.data.repositories

import ca.kallanou.itunesfinder.model.Album
import io.reactivex.Observable

interface RemoteAlbumsRepository {

    fun searchAlbums(term: String): Observable<List<Album>>

}
