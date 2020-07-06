package me.randyarba.feature_wiki.presentation.wikilist

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.randyarba.base_library.base.presentation.navigation.NavigationManager
import me.randyarba.base_library.base.presentation.viewmodel.BaseAction
import me.randyarba.base_library.base.presentation.viewmodel.BaseViewModel
import me.randyarba.base_library.base.presentation.viewmodel.BaseViewState
import me.randyarba.feature_wiki.domain.model.WikiDomainModel
import me.randyarba.feature_wiki.domain.usecase.WikiListUseCase

internal class WikiListViewModel(
    private val navManager: NavigationManager,
    private val wikiListUseCase: WikiListUseCase
) : BaseViewModel<WikiListViewModel.ViewState, WikiListViewModel.Action>(ViewState()) {

    override fun onLoadData() {
        getAlbumList()
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.AlbumListLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            albums = viewAction.albums
        )
        is Action.AlbumListLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            albums = listOf()
        )
    }

    private fun getAlbumList() {
        viewModelScope.launch {
            wikiListUseCase.execute().also { result ->
                val action = when (result) {
                    is WikiListUseCase.Result.Success ->
                        if (result.data.isEmpty()) {
                            Action.AlbumListLoadingFailure
                        } else {
                            Action.AlbumListLoadingSuccess(result.data)
                        }

                    is WikiListUseCase.Result.Error ->
                        Action.AlbumListLoadingFailure
                }
                sendAction(action)
            }
        }
    }

    fun navigateToAlbumDetails(artistName: String, albumName: String, mbId: String?) {
        val navDirect = WikiListFragmentDirections.actionAlbumListToAlbumDetail(artistName, albumName, mbId)
        navManager.navigateTo(navDirect)
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val albums: List<WikiDomainModel> = listOf()
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class AlbumListLoadingSuccess(val albums: List<WikiDomainModel>) : Action()
        object AlbumListLoadingFailure : Action()
    }
}
