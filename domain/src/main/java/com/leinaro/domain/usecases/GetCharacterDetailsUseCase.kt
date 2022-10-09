package com.leinaro.domain.usecases

import com.leinaro.data.Repository
import com.leinaro.domain.mapper.toUiModel
import com.leinaro.domain.ui_models.CharacterUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetCharacterDetailsUseCase {
  fun execute(characterId: Long): Flow<CharacterUiModel?>
}

class GetCharacterDetailsUseCaseImpl @Inject constructor(
  private val repository: Repository,
) : GetCharacterDetailsUseCase {
  override fun execute(characterId: Long): Flow<CharacterUiModel?> {
    return repository.getCharacterDetails(characterId).map { it?.toUiModel() }
  }
}