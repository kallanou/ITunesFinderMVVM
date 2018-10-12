package ca.kallanou.itunesfinder.ui.features.search

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
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
import ca.kallanou.itunesfinder.domain.models.Album
import ca.kallanou.itunesfinder.extensions.hideKeyboard
import ca.kallanou.itunesfinder.ui.base.framework.base.BaseFragment
import ca.kallanou.itunesfinder.ui.base.framework.extensions.observe
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class SearchFragment: BaseFragment<FragmentSearchBinding, SearchViewModel>(), SearchAlbumAdapter.OnItemClickListener {

    @Inject
    lateinit var factory: SearchVMFactory

    override fun layoutId(): Int = R.layout.fragment_search
    override fun getViewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java
    override fun getVMFactory(): ViewModelProvider.Factory = factory

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as ITunesFinderApp).createSearchComponent().inject(this)
        super.onCreate(savedInstanceState)

        viewModel.adapter = SearchAlbumAdapter()
        viewModel.adapter.setOnItemClickListener(this)

        observe(viewModel.errorState) {
            it -> it?.let {
                Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = super.onCreateView(inflater, container, savedInstanceState)
        with(binding) {
            viewModel = this@SearchFragment.viewModel
            searchMoviesRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            searchMoviesRecyclerView.adapter = this@SearchFragment.viewModel.adapter
            searchAlbumEditText.setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH || event?.keyCode == KeyEvent.KEYCODE_ENTER)
                    performSearch()
                true
            }
            searchButton.setOnClickListener { performSearch() }
        }

        return view
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
        viewModel.searchClicked()
    }

}
