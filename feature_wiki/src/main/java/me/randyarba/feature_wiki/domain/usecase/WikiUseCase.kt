package me.randyarba.feature_wiki.domain.usecase

import me.randyarba.feature_wiki.domain.model.WikiDomainModel
import me.randyarba.feature_wiki.domain.repository.WikiRepository
import java.io.IOException

internal class WikiUseCase(
    private val albumRepository: WikiRepository
) {

    sealed class Result {
        data class Success(val data: WikiDomainModel) : Result()
        data class Error(val e: Throwable) : Result()
    }

    suspend fun execute(
        artistName: String,
        albumName: String,
        mbId: String?
    ): Result = try {
        albumRepository.getWikiInfo(artistName, albumName, mbId)?.let {
            Result.Success(it)
        } ?: Result.Error(RuntimeException("No data"))
    } catch (e: IOException) {
        Result.Error(e)
    }
}