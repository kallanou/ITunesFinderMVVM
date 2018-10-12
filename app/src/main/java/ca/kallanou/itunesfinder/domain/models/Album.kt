package ca.kallanou.itunesfinder.domain.models

import android.text.TextUtils
import java.util.*

data class Album(val artistId: Int = 0,
                 val artworkUrl60: String = "",
                 val artworkUrl100: String = "",
                 val country: String = "",
                 val copyright: String = "",
                 val collectionViewUrl: String = "",
                 val contentAdvisoryRating: String = "",
                 val amgArtistId: Int = 0,
                 val releaseDate: Date? = null,
                 val collectionPrice: Double = 0.0,
                 val primaryGenreName: String = "",
                 val collectionName: String = "",
                 val artistViewUrl: String = "",
                 val collectionType: String = "",
                 val collectionExplicitness: String = "",
                 val trackCount: Int = 0,
                 val wrapperType: String = "",
                 val artistName: String = "",
                 val currency: String = "",
                 val collectionId: Int = 0,
                 val collectionCensoredName: String = "") {

    fun getCover(): String? {
        return if (!TextUtils.isEmpty(artworkUrl100))
            artworkUrl100
        else if (!TextUtils.isEmpty(artworkUrl60))
            artworkUrl60
        else null
    }
}