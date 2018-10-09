package ca.kallanou.itunesfinder.data.api

import ca.kallanou.itunesfinder.data.entities.AlbumData
import com.google.gson.annotations.SerializedName

class AlbumListResult {

    @SerializedName("resultCount")
    var nbAlbums: Int = 0

    @SerializedName("results")
    lateinit var albums: List<AlbumData>
}