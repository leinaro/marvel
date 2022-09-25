package com.leinaro.domain.usecases

import app.cash.turbine.testIn
import com.google.common.truth.Truth.assertThat
import com.leinaro.data.MarvelCharacter
import com.leinaro.domain.Repository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetCharactersUseCaseImplTest {

  @MockK
  private lateinit var repository: Repository

  private lateinit var useCase: GetCharactersUseCase

  private val listFlow = flow {
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

  @Before fun setUp() {
    MockKAnnotations.init(this, relaxUnitFun = true)
    useCase = GetCharactersUseCaseImpl(repository)
  }

  @Test fun `should return element when execute use case`() = runBlocking {
    // given
    every { repository.getCharacters() } returns listFlow

    // when
    val subject = useCase.execute().testIn(this)

    // then
    assertThat(subject.awaitItem()).hasSize(3)
    subject.awaitComplete()
  }
}
