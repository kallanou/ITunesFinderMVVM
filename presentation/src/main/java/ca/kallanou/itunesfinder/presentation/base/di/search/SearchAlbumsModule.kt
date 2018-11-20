package ca.kallanou.itunesfinder.presentation.base.di.search

import ca.kallanou.itunesfinder.domain.repositories.RemoteAlbumsRepository
import ca.kallanou.itunesfinder.domain.usecases.SearchAlbumUseCase
import ca.kallanou.itunesfinder.presentation.base.framework.ASyncTransformer
import ca.kallanou.itunesfinder.presentation.ui.features.search.SearchVMFactory
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