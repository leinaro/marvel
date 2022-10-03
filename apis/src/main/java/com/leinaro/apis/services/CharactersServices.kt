package com.leinaro.apis.services

import com.leinaro.apis.data.MarvelCharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersServices {

  @GET("/v1/public/characters")
  suspend fun fetchesListsOfCharacters(
    @Query("limit") limit: Int,
    @Query("offset") offset: Int,
  ): MarvelCharactersResponse
}


