package me.randyarba.feature_wiki.presentation.wikilist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.pawegio.kandroid.visible
import kotlinx.android.synthetic.main.fragment_album_list.*
import me.randyarba.base_library.base.presentation.extension.observe
import me.randyarba.base_library.base.presentation.fragment.InjectionFragment
import me.randyarba.feature_wiki.R
import me.randyarba.feature_wiki.presentation.wikilist.recyclerview.WikiAdapter
import org.kodein.di.generic.instance

class WikiListFragment : InjectionFragment(R.layout.fragment_album_list) {

    private val viewModel: WikiListViewModel by instance()

    private val albumAdapter: WikiAdapter by instance()

    private val stateObserver = Observer<WikiListViewModel.ViewState> {
        albumAdapter.albums = it.albums
        progressBar.visible = it.isLoading
        errorAnimation.visible = it.isError
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = checkNotNull(requireContext())

        albumAdapter.setOnDebouncedClickListener {
            viewModel.navigateToAlbumDetails(it.artist, it.name, it.mbId)
        }

        recyclerView.apply {
            setHasFixedSize(true)
            val columnWidth = 10f.toInt()
            layoutManager =
                GridLayoutManager(
                    context,
                    columnWidth
                )
            adapter = albumAdapter
        }

        observe(viewModel.stateLiveData, stateObserver)
        viewModel.loadData()
    }
}