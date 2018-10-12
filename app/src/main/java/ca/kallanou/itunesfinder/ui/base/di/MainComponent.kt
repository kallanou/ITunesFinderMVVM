package ca.kallanou.itunesfinder.ui.base.di

//import ca.kallanou.itunesfinder.ui.base.di.modules.AppModule
import ca.kallanou.itunesfinder.data.networking.di.modules.DataModule
import ca.kallanou.itunesfinder.data.networking.di.modules.NetworkModule
import ca.kallanou.itunesfinder.ui.base.di.search.SearchAlbumsModule
import ca.kallanou.itunesfinder.ui.base.di.search.SearchSubComponent
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