package com.leinaro.domain

import com.leinaro.apis.services.CharactersServices
import com.leinaro.apis.services.MarvelCharacterResponse
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
    val response = charactersServices.fetchesListsOfCharacters(mapOf("apikey" to ""))

    emit(ApiResponse.Success(response.results.toUiModel()))
    emit(ApiResponse.Loading(isLoading = false))
  }.catch {
    emit(ApiResponse.Error(null, it.message))
    emit(ApiResponse.Loading(isLoading = false))
  }
  /*  override fun getCharacters() = flow {
    emit(
      ApiResponse.Success(
        listOf(
          MarvelCharacter(
            1,
            "hello",
            "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/standard_small.jpg"
          ),
          MarvelCharacter(
            1,
            "hello",
            "https://images.unsplash.com/photo-1628373383885-4be0bc0172fa?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1301&q=80"
          ),
          MarvelCharacter(
            1, "hello", ""
          )
        )
      )
    )
  }*/
}

fun List<MarvelCharacterResponse>.toUiModel(): List<MarvelCharacter> =
  this.map { marvelCharacterResponse ->
    marvelCharacterResponse.toUiModel()
  }

fun MarvelCharacterResponse.toUiModel() = MarvelCharacter(
  id = this.id,
  name = this.name,
  thumbnailUrl = this.thumbnail.path + "standard_small" + this.thumbnail.extension
)
