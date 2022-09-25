package com.leinaro.domain

import com.leinaro.data.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface Repository {
  fun getCharacters(): Flow<List<MarvelCharacter>>
}

class RepositoryImpl @Inject constructor() : Repository {
  override fun getCharacters() = flow {
    emit(
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
  }
}