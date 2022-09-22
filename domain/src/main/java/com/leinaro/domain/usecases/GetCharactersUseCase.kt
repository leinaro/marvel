package com.leinaro.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetCharactersUseCase {
  fun execute(): Flow<List<Character>>
}

class GetCharactersUseCaseImpl : GetCharactersUseCase {
  override fun execute(): Flow<List<Character>> {
    //TODO("Not yet implemented")
    return flow {
      emit(emptyList())
    }
  }

}
