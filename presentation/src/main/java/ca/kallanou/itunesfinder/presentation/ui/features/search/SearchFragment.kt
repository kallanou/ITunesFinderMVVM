package ca.kallanou.itunesfinder.presentation.ui.features.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.kallanou.itunesfinder.presentation.ITunesFinderApp
import ca.kallanou.itunesfinder.presentation.R
import ca.kallanou.itunesfinder.domain.models.Album
import ca.kallanou.itunesfinder.presentation.base.framework.base.BaseFragment
import ca.kallanou.itunesfinder.presentation.base.framework.extensions.hideKeyboard
import ca.kallanou.itunesfinder.presentation.base.framework.extensions.observe
import ca.kallanou.itunesfinder.presentation.databinding.DialogAlbumDetailBinding
import ca.kallanou.itunesfinder.presentation.databinding.FragmentSearchBinding
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.apply {
            viewModel = this@SearchFragment.viewModel
            searchMoviesRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
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
        binding.apply {
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
