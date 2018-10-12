package ca.kallanou.itunesfinder.data.networking.apis

import ca.kallanou.itunesfinder.data.networking.models.AlbumListResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApi {

    @GET("search?entity=album&attribute=artistTerm")
    fun searchAlbums(@Query("term") term: String): Observable<AlbumListResult>

}