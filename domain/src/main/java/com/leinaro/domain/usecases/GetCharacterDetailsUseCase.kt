package com.leinaro.domain.usecases

import com.leinaro.data.MarvelCharacter
import com.leinaro.domain.Repository
import com.leinaro.domain.domain_status.ApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCharacterDetailsUseCase {
  fun execute(characterId: Long): Flow<ApiResponse<MarvelCharacter>>
}

class GetCharacterDetailsUseCaseImpl @Inject constructor(
  private val repository: Repository,
) : GetCharacterDetailsUseCase {
  override fun execute(characterId: Long): Flow<ApiResponse<MarvelCharacter>> {
    return repository.getCharacterDetails(characterId)
  }
}