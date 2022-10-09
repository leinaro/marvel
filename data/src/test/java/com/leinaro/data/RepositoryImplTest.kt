package com.leinaro.data

import android.util.Log
import app.cash.turbine.testIn
import com.leinaro.apis.data.ComicsResponse
import com.leinaro.apis.data.MarvelCharacterResponse
import com.leinaro.apis.data.MarvelCharactersResponse
import com.leinaro.apis.services.CharactersServices
import com.leinaro.data.data.MarvelCharacterData
import com.leinaro.data.mapper.toDomainModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RepositoryImplTest {

  @MockK(relaxed = true)
  private lateinit var charactersServices: CharactersServices

  private lateinit var subject: Repository

  @Before fun setUp() {
    MockKAnnotations.init(this, relaxUnitFun = true)
    subject = RepositoryImpl(charactersServices)
  }

  @Test fun `Should get character successfully`() = runBlocking {
    // given
    val marvelCharactersResponse =
      mockk<MarvelCharactersResponse<MarvelCharacterResponse>>(relaxed = true)
    val marvelCharacterResponse = mockk<MarvelCharacterResponse>(relaxed = true)
    every { marvelCharactersResponse.data.results } returns listOf(marvelCharacterResponse)
    coEvery { charactersServices.fetchCharacter(1234) } returns marvelCharactersResponse

    val marvelCharactersComicsResponse =
      mockk<MarvelCharactersResponse<ComicsResponse>>(relaxed = true)
    val comicsResponse = mockk<ComicsResponse>(relaxed = true)
    every { marvelCharactersComicsResponse.data.results } returns listOf(comicsResponse)
    coEvery { charactersServices.fetchCharacterComics(1234) } returns marvelCharactersComicsResponse

    // when
    val turbine = subject.getCharacterDetails(1234).testIn(this)

    val character = turbine.awaitItem()
    assert(character is MarvelCharacterData)
    assert(character?.comics?.first() == comicsResponse.toDomainModel())
    turbine.awaitComplete()
    coVerify(exactly = 1) { charactersServices.fetchCharacterComics(any()) }
    coVerify(exactly = 1) { charactersServices.fetchCharacter(1234) }
  }

  @Test(expected = Throwable::class) fun `Should return error when get character fails`() =
    runBlocking {
      // given
      coEvery { charactersServices.fetchCharacter(1234) } throws Throwable("Test")
      mockkStatic(Log::class)
      every { Log.e(any(), any()) } returns 1

      // when
      val turbine = subject.getCharacterDetails(1234).testIn(this)

      assert(turbine.awaitItem() is MarvelCharacterData)
      turbine.awaitComplete()
      coVerify(exactly = 1) { charactersServices.fetchCharacter(1234) }
      verify(exactly = 1) { Log.e(any(), any()) }
    }

  @Test fun `Should return a list of MarvelCharacter`() = runBlocking {
    // given
    val marvelCharactersResponse =
      mockk<MarvelCharactersResponse<MarvelCharacterResponse>>(relaxed = true)
    val marvelCharacterResponse = mockk<MarvelCharacterResponse>(relaxed = true)
    val marvelCharactersComicsResponse =
      mockk<MarvelCharactersResponse<ComicsResponse>>(relaxed = true)
    val comicsResponse = mockk<ComicsResponse>(relaxed = true)

    every { marvelCharacterResponse.id } returns 1234
    every { marvelCharactersResponse.data.results } returns listOf(marvelCharacterResponse)
    every { marvelCharactersComicsResponse.data.results } returns listOf(comicsResponse)

    coEvery {
      charactersServices.fetchesListsOfCharacters(any(), any())
    } returns marvelCharactersResponse

    // when
    val subject = subject.fetchesListsOfCharacters(10, 0)

    coVerify(exactly = 1) { charactersServices.fetchesListsOfCharacters(any(), any()) }

    assert(subject == listOf(marvelCharacterResponse.toDomainModel()))
    assert(subject.first().id == 1234L)
  }

  @Test fun `Should return a list of MarvelCharacter when no character was found`() = runBlocking {
    // given
    val marvelCharactersResponse =
      mockk<MarvelCharactersResponse<MarvelCharacterResponse>>(relaxed = true)
    val marvelCharactersComicsResponse =
      mockk<MarvelCharactersResponse<ComicsResponse>>(relaxed = true)

    every { marvelCharactersResponse.data.results } returns emptyList()
    every { marvelCharactersComicsResponse.data.results } returns emptyList()

    coEvery {
      charactersServices.fetchesListsOfCharacters(any(), any(), null)
    } returns marvelCharactersResponse

    // when
    val subject = subject.fetchesListsOfCharacters(10, 0)

    coVerify(exactly = 1) { charactersServices.fetchesListsOfCharacters(any(), any(), null) }

    assert(subject.isEmpty())
  }

}