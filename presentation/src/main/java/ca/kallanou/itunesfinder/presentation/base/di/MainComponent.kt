package ca.kallanou.itunesfinder.presentation.base.di

import ca.kallanou.itunesfinder.data.networking.di.modules.DataModule
import ca.kallanou.itunesfinder.data.networking.di.modules.NetworkModule
import ca.kallanou.itunesfinder.presentation.base.di.search.SearchAlbumsModule
import ca.kallanou.itunesfinder.presentation.base.di.search.SearchSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    //(AppModule::class),
    (NetworkModule::class),
    (DataModule::class)
])
interface MainComponent {

    fun inject(searchAlbumsModule: SearchAlbumsModule): SearchSubComponent

}