package ca.kallanou.itunesfinder.presentation

import android.app.Application
import ca.kallanou.itunesfinder.data.networking.di.modules.DataModule
import ca.kallanou.itunesfinder.data.networking.di.modules.NetworkModule
import ca.kallanou.itunesfinder.presentation.base.di.DaggerMainComponent
import ca.kallanou.itunesfinder.presentation.base.di.MainComponent
import ca.kallanou.itunesfinder.presentation.base.di.search.SearchAlbumsModule
import ca.kallanou.itunesfinder.presentation.base.di.search.SearchSubComponent

class ITunesFinderApp : Application() {

    private lateinit var mainComponent: MainComponent
    private var searchAlbumsComponent: SearchSubComponent? = null

    override fun onCreate() {
        super.onCreate()
        this.initDependencies()
    }

    private fun initDependencies() {
        this.mainComponent = DaggerMainComponent.builder()
            //.appModule(AppModule(applicationContext))
            .networkModule(NetworkModule(this.getString(R.string.api_base_url)))
            .dataModule(DataModule())
            .build()

    }

    fun createSearchComponent(): SearchSubComponent? {
        this.searchAlbumsComponent = this.mainComponent.inject(SearchAlbumsModule())
        return this.searchAlbumsComponent
    }

    fun releaseSearchComponent() {
        this.searchAlbumsComponent = null
    }
}