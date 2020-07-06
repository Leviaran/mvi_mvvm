package me.randyarba.feature_wiki.presentation.wikidetail

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.randyarba.base_library.base.presentation.viewmodel.BaseAction
import me.randyarba.base_library.base.presentation.viewmodel.BaseViewModel
import me.randyarba.base_library.base.presentation.viewmodel.BaseViewState
import me.randyarba.feature_wiki.domain.model.WikiDomainModel
import me.randyarba.feature_wiki.domain.usecase.WikiUseCase

internal class WikiDetailViewModel(
    private val getAlbumUseCase: WikiUseCase,
    private val args: WikiDetailFragmentArgs
) : BaseViewModel<WikiDetailViewModel.ViewState, WikiDetailViewModel.Action>(ViewState()) {

    override fun onLoadData() {
        getWiki()
    }

    private fun getWiki() {
        viewModelScope.launch {
            getAlbumUseCase.execute(args.artistName, args.albumName, args.mbId).also {
                when {
                    it is WikiUseCase.Result.Success ->
                        sendAction(Action.WikiLoadSuccess(it.data))
                    it is WikiUseCase.Result.Error ->
                        sendAction(Action.WikiLoadFailur)
                }
            }
        }
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.WikiLoadSuccess -> state.copy(
            isLoading = false,
            isError = false,
            artistName = viewAction.wikiDomainModel.artist,
            albumName = viewAction.wikiDomainModel.name,
            coverImageUrl = viewAction.wikiDomainModel.getDefaultImageUrl() ?: ""
        )
        is Action.WikiLoadFailur -> state.copy(
            isLoading = false,
            isError = true,
            artistName = "",
            albumName = "",
            coverImageUrl = ""
        )
    }

    internal data
    class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val albumName: String = "",
        val artistName: String = "",
        val coverImageUrl: String = ""
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class WikiLoadSuccess(val wikiDomainModel: WikiDomainModel) : Action()
        object WikiLoadFailur : Action()
    }
}