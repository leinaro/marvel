package com.leinaro.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.leinaro.apis.services.CharactersServices
import com.leinaro.data.MarvelCharacter
import javax.inject.Inject

class CharactersSource @Inject constructor(
  private val charactersServices: CharactersServices,
) : PagingSource<Int, MarvelCharacter>() {
  @ExperimentalPagingApi
  override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int? {
    return state.anchorPosition?.let { position ->
      val page = state.closestPageToPosition(position)
      page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
    }
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
    return try {
      val page = params.key ?: 1
      val limit = 20
      val offset = limit * page
      val response = charactersServices.fetchesListsOfCharacters(limit, offset)
      LoadResult.Page(
        data = response.data.results.orEmpty().toDomainModel(),
        prevKey = if (page == 1) null else page - 1,
        nextKey = if (!response.data.results.isNullOrEmpty()) page.plus(1) else null,
      )
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
  }
}
