package com.leinaro.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.leinaro.data.MarvelCharacter
import com.leinaro.domain.CharactersSourceFactory
import javax.inject.Inject

interface GetCharactersUseCase {
  fun execute(
    nameStartsWith: String? = null
  ): Pager<Int, MarvelCharacter>
}

private const val PAGER_SIZE = 20

class GetCharactersUseCaseImpl @Inject constructor(
  private val charactersSourceFactory: CharactersSourceFactory,
) : GetCharactersUseCase {

  override fun execute(
    nameStartsWith: String?
  ): Pager<Int, MarvelCharacter> {
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
