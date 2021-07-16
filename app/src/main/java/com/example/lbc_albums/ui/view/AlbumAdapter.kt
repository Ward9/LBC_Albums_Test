package com.example.lbc_albums.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.lbc_albums.data.model.AlbumModel
import com.example.lbc_albums.databinding.ItemAlbumBinding

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapterViewHolder>(){

    private val items = ArrayList<AlbumModel>()

    fun setItems(items: ArrayList<AlbumModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumAdapterViewHolder {
        val binding: ItemAlbumBinding =
            ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AlbumAdapterViewHolder, position: Int) = holder.bind(items[position])
}

class AlbumAdapterViewHolder(private val itemBinding: ItemAlbumBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var album: AlbumModel

    fun bind(item: AlbumModel) {
        this.album = item
        itemBinding.itemTitle.text = item.title
        itemBinding.itemId.text = item.id.toString()

        val urlImg = GlideUrl(
            item.thumbnailUrl, LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build()
        )

        Glide.with(itemBinding.root)
            .load(urlImg)
            .transform(CircleCrop())
            .into(itemBinding.itemImage)
    }
}