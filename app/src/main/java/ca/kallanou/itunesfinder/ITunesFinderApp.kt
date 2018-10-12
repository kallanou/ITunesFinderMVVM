package ca.kallanou.itunesfinder

import android.app.Application
import ca.kallanou.itunesfinder.ui.base.di.DaggerMainComponent
import ca.kallanou.itunesfinder.ui.base.di.MainComponent
//import ca.kallanou.itunesfinder.ui.base.di.modules.AppModule
import ca.kallanou.itunesfinder.data.networking.di.modules.DataModule
import ca.kallanou.itunesfinder.data.networking.di.modules.NetworkModule
import ca.kallanou.itunesfinder.ui.base.di.search.SearchAlbumsModule
import ca.kallanou.itunesfinder.ui.base.di.search.SearchSubComponent

class ITunesFinderApp: Application() {

    private lateinit var mainComponent: MainComponent
    private var searchAlbumsComponent: SearchSubComponent? = null

    override fun onCreate() {
        super.onCreate()
        initDependencies()
    }

    private fun initDependencies() {
        mainComponent = DaggerMainComponent.builder()
                //.appModule(AppModule(applicationContext))
                .networkModule(NetworkModule(getString(R.string.api_base_url)))
                .dataModule(DataModule())
                .build()

    }

    fun createSearchComponent(): SearchSubComponent {
        searchAlbumsComponent = mainComponent.inject(SearchAlbumsModule())
        return searchAlbumsComponent!!
    }

    fun releaseSearchComponent() {
        searchAlbumsComponent = null
    }
}