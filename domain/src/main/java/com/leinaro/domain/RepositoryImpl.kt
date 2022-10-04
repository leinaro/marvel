package com.leinaro.domain

import android.util.Log
import com.leinaro.apis.data.MarvelCharacterResponse
import com.leinaro.apis.services.CharactersServices
import com.leinaro.data.MarvelCharacter
import com.leinaro.domain.domain_status.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
  private val charactersServices: CharactersServices,
) : Repository {
  override fun getCharacterDetails(characterId: Long): Flow<ApiResponse<MarvelCharacter>> = flow {
    emit(ApiResponse.Loading(isLoading = true))
    val response = charactersServices.fetchCharacter(characterId)
    emit(ApiResponse.Success(response.data.results?.first()?.toDomainModel()))
    emit(ApiResponse.Loading(isLoading = false))
  }.catch {
    Log.e(javaClass.name, it.message ?: "")
    emit(ApiResponse.Error(null, it.message))
    emit(ApiResponse.Loading(isLoading = false))
  }
}

fun List<MarvelCharacterResponse>.toDomainModel(): List<MarvelCharacter> =
  this.map { marvelCharacterResponse ->
    marvelCharacterResponse.toDomainModel()
  }

fun MarvelCharacterResponse.toDomainModel() = MarvelCharacter(
  id = this.id,
  name = this.name,
  thumbnailUrl = "${this.thumbnail.path}/$STANDARD_SMALL.${this.thumbnail.extension}",
  landscapeUrl = "${this.thumbnail.path}/$LANDSCAPE_LARGE.${this.thumbnail.extension}",
)

private const val STANDARD_SMALL = "standard_small"
private const val LANDSCAPE_LARGE = "landscape_large"
