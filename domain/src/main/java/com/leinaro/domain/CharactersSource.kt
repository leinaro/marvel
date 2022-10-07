package com.leinaro.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.leinaro.data.Repository
import com.leinaro.domain.ui_models.CharacterUiModel
import com.leinaro.domain.ui_models.toUiModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@AssistedFactory
interface CharactersSourceFactory {
  fun createCharactersSource(
    nameStartsWith: String? = null
  ): CharactersSource
}

class CharactersSource @AssistedInject constructor(
  private val repository: Repository,
  @Assisted private val nameStartsWith: String? = null,
) : PagingSource<Int, CharacterUiModel>() {
  @ExperimentalPagingApi
  override fun getRefreshKey(state: PagingState<Int, CharacterUiModel>): Int? {
    return state.anchorPosition?.let { position ->
      val page = state.closestPageToPosition(position)
      page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
    }
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterUiModel> {
    return try {
      val page = params.key ?: 1
      val limit = 20
      val offset = limit * (page - 1)
      val response = repository.fetchesListsOfCharacters(
        limit = limit,
        offset = offset,
        nameStartsWith = nameStartsWith
      ).map {
        it.toUiModel()
      }
      LoadResult.Page(
        data = response,
        prevKey = if (page == 1) null else page - 1,
        nextKey = if (response.isNotEmpty()) page.plus(1) else null,
      )
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
  }
}
