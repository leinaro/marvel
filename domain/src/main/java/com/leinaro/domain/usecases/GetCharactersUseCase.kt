package com.leinaro.domain.usecases

import kotlinx.coroutines.flow.Flow

interface GetCharactersUseCase {
  fun execute() : Flow<List<Character>>
}