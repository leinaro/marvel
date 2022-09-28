package com.leinaro.domain.usecases

import com.leinaro.data.MarvelCharacter
import com.leinaro.domain.ApiResponse
import com.leinaro.domain.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCharactersUseCase {
  fun execute(): Flow<ApiResponse<List<MarvelCharacter>>>
}

class GetCharactersUseCaseImpl @Inject constructor(
  private val repository: Repository
) : GetCharactersUseCase {
  override fun execute(): Flow<ApiResponse<List<MarvelCharacter>>> {
    return repository.getCharacters()
  }
}
