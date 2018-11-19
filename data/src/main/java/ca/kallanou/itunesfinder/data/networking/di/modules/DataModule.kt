package ca.kallanou.itunesfinder.data.networking.di.modules

import ca.kallanou.itunesfinder.data.networking.apis.ItunesApi
import ca.kallanou.itunesfinder.data.repositories.RemoteAlbumsRepositoryImpl
import ca.kallanou.itunesfinder.domain.repositories.RemoteAlbumsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideAlbumsRepository(itunesApi: ItunesApi): RemoteAlbumsRepository {
        return RemoteAlbumsRepositoryImpl(itunesApi)
    }
}