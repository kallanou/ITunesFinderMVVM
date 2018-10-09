package ca.kallanou.itunesfinder.di

//import ca.kallanou.itunesfinder.di.modules.AppModule
import ca.kallanou.itunesfinder.di.modules.DataModule
import ca.kallanou.itunesfinder.di.modules.NetworkModule
import ca.kallanou.itunesfinder.di.search.SearchAlbumsModule
import ca.kallanou.itunesfinder.di.search.SearchSubComponent
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