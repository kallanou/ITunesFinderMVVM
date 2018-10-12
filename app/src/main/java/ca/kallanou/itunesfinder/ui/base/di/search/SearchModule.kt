package ca.kallanou.itunesfinder.ui.base.di.search

import ca.kallanou.itunesfinder.domain.framework.ASyncTransformer
import ca.kallanou.itunesfinder.domain.repositories.RemoteAlbumsRepository
import ca.kallanou.itunesfinder.domain.usecases.SearchAlbumUseCase
import ca.kallanou.itunesfinder.ui.features.search.SearchVMFactory
import dagger.Module
import dagger.Provides

@Module
class SearchAlbumsModule {

    @Provides
    fun provideSearchMovieUseCase(albumsRepository: RemoteAlbumsRepository): SearchAlbumUseCase {
        return SearchAlbumUseCase(ASyncTransformer(), albumsRepository)
    }

    @Provides
    fun provideSearchVMFactory(useCase: SearchAlbumUseCase): SearchVMFactory {
        return SearchVMFactory(useCase)
    }
}