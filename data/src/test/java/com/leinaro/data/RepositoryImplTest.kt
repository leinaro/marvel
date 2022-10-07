package com.leinaro.data

import android.util.Log
import app.cash.turbine.testIn
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

  @Test fun `Should get character sucessfuly`() = runBlocking {
    // given
    val marvelCharactersResponse =
      mockk<MarvelCharactersResponse<MarvelCharacterResponse>>(relaxed = true)
    val marvelCharacterResponse = mockk<MarvelCharacterResponse>(relaxed = true)

    every { marvelCharactersResponse.data.results } returns listOf(marvelCharacterResponse)
    coEvery { charactersServices.fetchCharacter(1234) } returns marvelCharactersResponse

    // when
    val turbine = subject.getCharacterDetails(1234).testIn(this)

    assert(turbine.awaitItem() is MarvelCharacterData)
    turbine.awaitComplete()
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

    every { marvelCharactersResponse.data.results } returns listOf(marvelCharacterResponse)
    coEvery {
      charactersServices.fetchesListsOfCharacters(
        any(),
        any()
      )
    } returns marvelCharactersResponse

    // when
    val subject = subject.fetchesListsOfCharacters(10, 0)

    assert(subject == listOf(marvelCharacterResponse.toDomainModel()))
    coVerify(exactly = 1) { charactersServices.fetchesListsOfCharacters(any(), any()) }
  }

}