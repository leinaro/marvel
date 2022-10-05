package com.leinaro.domain

import androidx.paging.PagingSource
import com.leinaro.apis.data.MarvelCharacterResponse
import com.leinaro.apis.data.MarvelCharactersResponse
import com.leinaro.apis.services.CharactersServices
import com.leinaro.data.MarvelCharacter
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

internal class CharactersSourceTest {
  @MockK(relaxed = true)
  private lateinit var charactersServices: CharactersServices

  private lateinit var subject: CharactersSource

  private val listCharacters = listOf<MarvelCharacterResponse>(
    mockk<MarvelCharacterResponse>(relaxed = true),
    mockk<MarvelCharacterResponse>(relaxed = true),
    mockk<MarvelCharacterResponse>(relaxed = true),
  )

  @Before fun setUp() {
    MockKAnnotations.init(this, relaxUnitFun = true)
    subject = CharactersSource(charactersServices)
  }

  @Test fun `Should load characters successfuly`() = runBlockingTest {
    // given

    val marvelCharactersResponse = mockk<MarvelCharactersResponse>(relaxed = true)

    coEvery {
      charactersServices.fetchesListsOfCharacters(
        any(),
        any()
      )
    } returns marvelCharactersResponse

    every { marvelCharactersResponse.data.results } returns listCharacters

    val expected: PagingSource.LoadResult<Int, MarvelCharacter> = PagingSource.LoadResult.Page(
      data = listCharacters.map { it.toDomainModel() },
      prevKey = null,
      nextKey = 2
    )

    // when
    val loadSource: PagingSource.LoadResult<Int, MarvelCharacter> = subject.load(
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