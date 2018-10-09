package ca.kallanou.itunesfinder.data.mappers

import ca.kallanou.itunesfinder.common.Mapper
import ca.kallanou.itunesfinder.data.entities.AlbumData
import ca.kallanou.itunesfinder.model.Album
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumDataEntityMapper @Inject constructor() : Mapper<AlbumData, Album>() {

    override fun mapFrom(from: AlbumData): Album {
        return Album(
                artistId = from.artistId,
                artworkUrl60 = from.artworkUrl60,
                artworkUrl100 = from.artworkUrl100,
                country = from.country,
                copyright = from.copyright,
                collectionViewUrl = from.collectionViewUrl,
                contentAdvisoryRating = from.contentAdvisoryRating,
                amgArtistId = from.amgArtistId,
                releaseDate = from.releaseDate,
                collectionPrice = from.collectionPrice,
                primaryGenreName = from.primaryGenreName,
                collectionName = from.collectionName,
                artistViewUrl = from.artistViewUrl,
                collectionType = from.collectionType,
                collectionExplicitness = from.collectionExplicitness,
                trackCount = from.trackCount,
                wrapperType = from.wrapperType,
                artistName = from.artistName,
                currency = from.currency,
                collectionId = from.collectionId,
                collectionCensoredName = from.collectionCensoredName
        )
    }
}