package ca.kallanou.itunesfinder.di.modules

import ca.kallanou.itunesfinder.data.api.Api
import ca.kallanou.itunesfinder.data.repositories.RemoteAlbumsRepository
import ca.kallanou.itunesfinder.data.repositories.RemoteAlbumsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class DataModule {

    @Provides
    @Singleton
    fun provideAlbumsRepository(api: Api): RemoteAlbumsRepository {
        return RemoteAlbumsRepositoryImpl(api)
    }
}