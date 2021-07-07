package ca.kallanou.itunesfinder.data.networking.models

import ca.kallanou.itunesfinder.domain.models.Album
import java.util.*

data class AlbumData(
    val artistId: Int = 0,
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
    val collectionCensoredName: String = ""
)

fun List<AlbumData>.toAlbums(): List<Album> {
    return this.map { it.toAlbum() }
}

fun AlbumData.toAlbum(): Album {
    return Album(
        artistId = artistId,
        artworkUrl60 = artworkUrl60,
        artworkUrl100 = artworkUrl100,
        country = country,
        copyright = copyright,
        collectionViewUrl = collectionViewUrl,
        contentAdvisoryRating = contentAdvisoryRating,
        amgArtistId = amgArtistId,
        releaseDate = releaseDate,
        collectionPrice = collectionPrice,
        primaryGenreName = primaryGenreName,
        collectionName = collectionName,
        artistViewUrl = artistViewUrl,
        collectionType = collectionType,
        collectionExplicitness = collectionExplicitness,
        trackCount = trackCount,
        wrapperType = wrapperType,
        artistName = artistName,
        currency = currency,
        collectionId = collectionId,
        collectionCensoredName = collectionCensoredName
    )
}