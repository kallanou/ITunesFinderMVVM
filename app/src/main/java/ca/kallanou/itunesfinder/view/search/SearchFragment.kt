package ca.kallanou.itunesfinder.view.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import ca.kallanou.itunesfinder.ITunesFinderApp
import ca.kallanou.itunesfinder.R
import ca.kallanou.itunesfinder.databinding.DialogAlbumDetailBinding
import ca.kallanou.itunesfinder.databinding.FragmentSearchBinding
import ca.kallanou.itunesfinder.extensions.hideKeyboard
import ca.kallanou.itunesfinder.model.Album
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class SearchFragment: Fragment(), SearchAlbumAdapter.OnItemClickListener {

    @Inject
    lateinit var factory: SearchVMFactory

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as ITunesFinderApp).createSearchComponent().inject(this)
        searchViewModel = ViewModelProviders.of(this, factory).get(SearchViewModel::class.java)
        searchViewModel.adapter = SearchAlbumAdapter()
        searchViewModel.adapter.setOnItemClickListener(this)

        searchViewModel.errorState.observe(this, Observer { throwable ->
            throwable?.let {
                Toast.makeText(activity, throwable.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        with(binding) {
            searchMoviesRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            searchMoviesRecyclerView.adapter = searchViewModel.adapter
            viewModel = searchViewModel
            searchAlbumEditText.setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH || event?.keyCode == KeyEvent.KEYCODE_ENTER)
                    performSearch()
                true
            }
            searchButton.setOnClickListener { performSearch() }
        }

        return binding.root
    }

    override fun onAlbumClicked(album: Album) {
        val binding: DialogAlbumDetailBinding = DataBindingUtil.inflate(LayoutInflater.from(context!!), R.layout.dialog_album_detail, null, false)
        val mBuilder = AlertDialog.Builder(context!!)
                .setView(binding.root)
                .setTitle(R.string.dialog_album_title)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, null)
        mBuilder.show()
        with(binding) {
            dialogAlbumGenreEditText.setText(album.primaryGenreName)
            dialogAlbumPriceEditText.setText(getString(R.string.dialog_album_price_format, album.collectionPrice, album.currency))
            dialogAlbumCopyrightEditText.setText(album.copyright)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity?.application as ITunesFinderApp).releaseSearchComponent()
    }

    private fun performSearch() {
        activity?.hideKeyboard()
        searchViewModel.searchClicked()
    }

}
