package ca.kallanou.itunesfinder.di.search

import ca.kallanou.itunesfinder.view.search.SearchFragment
import dagger.Subcomponent

@SearchScope
@Subcomponent(modules = [SearchAlbumsModule::class])
interface SearchSubComponent {

    fun inject(searchFragment: SearchFragment)

}