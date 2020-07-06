package me.randyarba.feature_wiki.domain.usecase

import me.randyarba.feature_wiki.domain.model.WikiDomainModel
import me.randyarba.feature_wiki.domain.repository.WikiRepository
import java.io.IOException

internal class WikiListUseCase(
    private val wikiRepository: WikiRepository
) {

    sealed class Result {
        data class Success(val data: List<WikiDomainModel>) : Result()
        data class Error(val e: Throwable) : Result()
    }

    suspend fun execute(): Result {
        val phrase = "sd"

        return try {
            Result.Success(wikiRepository.searchWiki(phrase)
                .filter { it.getDefaultImageUrl() != null }
            )
        } catch (e: IOException) {
            Result.Error(e)
        }
    }
}