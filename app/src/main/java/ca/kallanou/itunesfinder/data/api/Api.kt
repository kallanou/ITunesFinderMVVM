package ca.kallanou.itunesfinder.data.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("search?entity=album&attribute=artistTerm")
    fun searchAlbums(@Query("term") term: String): Observable<AlbumListResult>

}