package ca.kallanou.itunesfinder.data.networking.models

import ca.kallanou.itunesfinder.data.networking.models.AlbumData
import com.google.gson.annotations.SerializedName

class AlbumListResult {

    @SerializedName("resultCount")
    var nbAlbums: Int = 0

    @SerializedName("results")
    lateinit var albums: List<AlbumData>
}