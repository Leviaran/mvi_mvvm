package me.randyarba.feature_wiki.presentation.wikidetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import coil.api.load
import com.pawegio.kandroid.visible
import kotlinx.android.synthetic.main.fragment_album_detail.*
import me.randyarba.base_library.base.presentation.extension.observe
import me.randyarba.base_library.base.presentation.fragment.InjectionFragment
import me.randyarba.feature_wiki.R
import org.kodein.di.generic.instance

internal class WikiDetailFragment : InjectionFragment(R.layout.fragment_album_detail) {

    private val viewModel: WikiDetailViewModel by instance()

    private val stateObserver = Observer<WikiDetailViewModel.ViewState> {
        progressBar.visible = it.isLoading

        nameTextView.text = it.albumName
        nameTextView.visible = it.albumName.isNotBlank()

        artistTextView.text = it.artistName
        artistTextView.visible = it.artistName.isNotBlank()

        errorAnimation.visible = it.isError

        val imageSize = 800

        coverImageView.load(it.coverImageUrl) {
            size(imageSize, imageSize)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.stateLiveData, stateObserver)
        viewModel.loadData()
    }
}