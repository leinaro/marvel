package com.leinaro.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.leinaro.data.MarvelCharacter
import com.leinaro.domain.CharactersSource
import javax.inject.Inject

interface GetCharactersUseCase {
  fun execute(): Pager<Int, MarvelCharacter>
}

private const val PAGER_SIZE = 20

class GetCharactersUseCaseImpl @Inject constructor(
  private val charactersSource: CharactersSource,
) : GetCharactersUseCase {
  override fun execute(): Pager<Int, MarvelCharacter> {
    return Pager(
      PagingConfig(
        pageSize = PAGER_SIZE,
        initialLoadSize = PAGER_SIZE
      )
    ) {
      charactersSource
    }
  }
}
