package com.leinaro.domain

import android.util.Log
import com.leinaro.apis.data.MarvelCharacterResponse
import com.leinaro.apis.services.CharactersServices
import com.leinaro.data.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

sealed class ApiStatus {
  object Success : ApiStatus()
  object Error : ApiStatus()
  object Loading : ApiStatus()
}

sealed class ApiResponse<out R>(val status: ApiStatus, val data: R?, val message: String?) {
  data class Success<out T>(val _data: T?) : ApiResponse<T>(
    status = ApiStatus.Success,
    data = _data,
    message = null,
  )

  data class Error(val exception: Exception?, val _message: String?) : ApiResponse<Nothing>(
    status = ApiStatus.Error,
    data = null,
    message = _message,
  )

  data class Loading<out R>(val _data: R? = null, val isLoading: Boolean) : ApiResponse<R>(
    status = ApiStatus.Loading,
    data = _data,
    message = null
  )
}

class RepositoryImpl @Inject constructor(
  private val charactersServices: CharactersServices,
) : Repository {
  override fun getCharacters(): Flow<ApiResponse<List<MarvelCharacter>>> = flow {
    emit(ApiResponse.Loading(isLoading = true))
    val limit: Int = 10
    val offset: Int = 10
    val response = charactersServices.fetchesListsOfCharacters(
      limit = limit,
      offset = offset,
    )


    emit(ApiResponse.Success(response.data.results?.toDomainModel()))
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
  thumbnailUrl = "${this.thumbnail.path}/$STANDARD_SMALL.${this.thumbnail.extension}"
)

private const val STANDARD_SMALL = "standard_small"
