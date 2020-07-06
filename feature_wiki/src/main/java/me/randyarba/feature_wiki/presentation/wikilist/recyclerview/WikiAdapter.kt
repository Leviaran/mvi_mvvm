package me.randyarba.feature_wiki.presentation.wikilist.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.pawegio.kandroid.hide
import com.pawegio.kandroid.show
import kotlinx.android.synthetic.main.fragment_album_list_item.view.*
import me.randyarba.base_library.base.delegate.observer
import me.randyarba.base_library.base.presentation.extension.setOnDebouncedClickListener
import me.randyarba.feature_wiki.R
import me.randyarba.feature_wiki.domain.model.WikiDomainModel

internal class WikiAdapter : RecyclerView.Adapter<WikiAdapter.MyViewHolder>() {

    var albums: List<WikiDomainModel> by observer(listOf()) {
        notifyDataSetChanged()
    }

    private var onDebouncedClickListener: ((album: WikiDomainModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_album_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    override fun getItemCount(): Int = albums.size

    fun setOnDebouncedClickListener(listener: (album: WikiDomainModel) -> Unit) {
        this.onDebouncedClickListener = listener
    }

    internal inner class MyViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private var url by observer<String?>(null) {
            itemView.coverErrorImageView.hide()

            if (it == null) {
                setDefaultImage()
            } else {
                itemView.coverImageView.load(it) {
                    crossfade(true)
//                    error(R.drawable.ic_launcher_background)
                    transformations(RoundedCornersTransformation(10F))
                }
            }
        }

        fun bind(albumDomainModel: WikiDomainModel) {
            itemView.setOnDebouncedClickListener { onDebouncedClickListener?.invoke(albumDomainModel) }
            url = albumDomainModel.getDefaultImageUrl()
        }

        private fun setDefaultImage() {
            itemView.coverErrorImageView.show()
        }
    }
}
