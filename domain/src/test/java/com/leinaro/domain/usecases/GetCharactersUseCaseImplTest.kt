package com.leinaro.domain.usecases

import com.leinaro.data.data.MarvelCharacterData
import com.leinaro.domain.CharactersSource
import com.leinaro.domain.CharactersSourceFactory
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetCharactersUseCaseImplTest {

  @MockK
  private lateinit var charactersSourceFactory: CharactersSourceFactory

  private lateinit var useCase: GetCharactersUseCase
/*
  private val listFlow = flow {
    emit(
      listOf(
        MarvelCharacterData(
          1,
          "hello",
          "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/standard_small.jpg",
          "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/standard_small.jpg",
        ),
        MarvelCharacterData(
          1,
          "hello",
          "https://images.unsplash.com/photo-1628373383885-4be0bc0172fa?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1301&q=80",
          "https://images.unsplash.com/photo-1628373383885-4be0bc0172fa?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1301&q=80",
        ),
        MarvelCharacterData(
          1, "hello", "", "", ""
        )
      )
    )
  }*/

  @Before fun setUp() {
    MockKAnnotations.init(this, relaxUnitFun = true)
    useCase = GetCharactersUseCaseImpl(charactersSourceFactory)
  }

  @Test fun `should return element when execute use case`() = runBlocking {
    // given
    //   val response = mockk<ApiResponse.Success<MarvelCharacter>>(relaxed = true)
    //   every { repository.getCharacterDetails(any()) } returns response
    val charactersSource = mockk<CharactersSource>()
    every { charactersSourceFactory.createCharactersSource() } returns charactersSource

    // when
    val subject = useCase.execute()

    //val subject = useCase.execute().testIn(this)

    // then
    //  assertThat(subject.awaitItem()).hasSize(3)
    //  subject.awaitComplete()
  }
}
