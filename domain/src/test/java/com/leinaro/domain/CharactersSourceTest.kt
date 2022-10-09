package com.leinaro.domain

import androidx.paging.PagingSource
import com.leinaro.data.Repository
import com.leinaro.data.data.MarvelCharacterData
import com.leinaro.domain.mapper.toUiModel
import com.leinaro.domain.ui_models.CharacterUiModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

internal class CharactersSourceTest {
  @MockK(relaxed = true)
  private lateinit var repository: Repository

  private lateinit var subject: CharactersSource

  private val listCharacters = listOf<MarvelCharacterData>(
    mockk<MarvelCharacterData>(relaxed = true),
    mockk<MarvelCharacterData>(relaxed = true),
    mockk<MarvelCharacterData>(relaxed = true),
  )

  @Before fun setUp() {
    MockKAnnotations.init(this, relaxUnitFun = true)
    subject = CharactersSource(repository)
  }

  @Test fun `Should load characters successfuly`() = runBlockingTest {
    // given

    coEvery {
      repository.fetchesListsOfCharacters(
        any(),
        any()
      )
    } returns listCharacters

    val expected: PagingSource.LoadResult<Int, CharacterUiModel> = PagingSource.LoadResult.Page(
      data = listCharacters.map {
        it.toUiModel()
      },
      prevKey = null,
      nextKey = 2
    )

    // when
    val loadSource: PagingSource.LoadResult<Int, CharacterUiModel> = subject.load(
      PagingSource.LoadParams.Refresh(
        key = null,
        loadSize = 2,
        placeholdersEnabled = false
      )
    )

    // then
    assert(expected == loadSource) {
      "expected $expected but was $loadSource"
    }
  }
}