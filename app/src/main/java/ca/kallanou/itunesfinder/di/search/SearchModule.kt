package ca.kallanou.itunesfinder.di.search

import ca.kallanou.itunesfinder.common.ASyncTransformer
import ca.kallanou.itunesfinder.data.repositories.RemoteAlbumsRepository
import ca.kallanou.itunesfinder.usecases.SearchAlbum
import ca.kallanou.itunesfinder.view.search.SearchVMFactory
import dagger.Module
import dagger.Provides

@Module
class SearchAlbumsModule {

    @Provides
    fun provideSearchMovieUseCase(albumsRepository: RemoteAlbumsRepository): SearchAlbum {
        return SearchAlbum(ASyncTransformer(), albumsRepository)
    }

    @Provides
    fun provideSearchVMFactory(useCase: SearchAlbum): SearchVMFactory {
        return SearchVMFactory(useCase)
    }
}