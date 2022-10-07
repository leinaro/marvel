package com.leinaro.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.leinaro.domain.CharactersSourceFactory
import com.leinaro.domain.ui_models.CharacterUiModel
import javax.inject.Inject

interface GetCharactersUseCase {
  fun execute(
    nameStartsWith: String? = null
  ): Pager<Int, CharacterUiModel>
}

private const val PAGER_SIZE = 20

class GetCharactersUseCaseImpl @Inject constructor(
  private val charactersSourceFactory: CharactersSourceFactory,
) : GetCharactersUseCase {

  override fun execute(
    nameStartsWith: String?
  ): Pager<Int, CharacterUiModel> {
    return Pager(
      PagingConfig(
        pageSize = PAGER_SIZE,
        initialLoadSize = PAGER_SIZE
      )
    ) {
      charactersSourceFactory.createCharactersSource(nameStartsWith)
    }
  }
}
