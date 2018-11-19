package ca.kallanou.itunesfinder.presentation.ui.features.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ca.kallanou.itunesfinder.presentation.R
import ca.kallanou.itunesfinder.domain.models.Album
import ca.kallanou.itunesfinder.presentation.databinding.ItemAlbumBinding
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.text.SimpleDateFormat
import java.util.*

class SearchAlbumAdapter: RecyclerView.Adapter<SearchAlbumAdapter.SearchAlbumViewHolder>() {

    private lateinit var albums: List<Album>
    lateinit var listener: OnItemClickListener
    private var dateFormatter: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAlbumAdapter.SearchAlbumViewHolder {
        val binding: ItemAlbumBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_album, parent, false)
        return SearchAlbumViewHolder(binding, dateFormatter)
    }

    override fun onBindViewHolder(holder: SearchAlbumAdapter.SearchAlbumViewHolder, position: Int) {
        holder.bind(albums[position], listener)
    }

    override fun getItemCount(): Int {
        return if(::albums.isInitialized) albums.size else 0
    }

    fun updateAlbums(albums: List<Album>){
        this.albums = albums
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onAlbumClicked(album: Album)
    }

    class SearchAlbumViewHolder(private val binding: ItemAlbumBinding, private val dateFormatter: SimpleDateFormat): RecyclerView.ViewHolder(binding.root){
        fun bind(album: Album, onItemClickListener: OnItemClickListener) {
            with(binding) {
                Glide
                        .with(root.context)
                        .load(album.getCover())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(albumCover)
                albumName.text = album.collectionName
                albumReleaseDate.text = dateFormatter.format(album.releaseDate)
                root.setOnClickListener { onItemClickListener.onAlbumClicked(album) }
            }
        }

    }
}