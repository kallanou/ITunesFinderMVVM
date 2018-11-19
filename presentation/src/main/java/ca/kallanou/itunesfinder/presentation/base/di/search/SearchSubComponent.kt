package ca.kallanou.itunesfinder.presentation.base.di.search

import ca.kallanou.itunesfinder.presentation.ui.features.search.SearchFragment
import dagger.Subcomponent

@SearchScope
@Subcomponent(modules = [SearchAlbumsModule::class])
interface SearchSubComponent {

    fun inject(searchFragment: SearchFragment)

}