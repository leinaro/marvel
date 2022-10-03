package com.leinaro.characters_list

import com.leinaro.data.MarvelCharacter
import com.leinaro.domain.usecases.GetCharactersUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class CharactersListViewModelTest {

  private val testDispatcher = TestCoroutineDispatcher()

  @ExperimentalCoroutinesApi
  @get:Rule
  var mainCoroutineRule = MainCoroutineRule()

  @MockK(relaxed = true)
  private lateinit var getCharactersUseCase: GetCharactersUseCase

  private lateinit var subject: CharactersListViewModel

  private val listFlow = flow {
    emit(
      listOf(
        MarvelCharacter(
          1, "hello", "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/standard_small.jpg"
        ), MarvelCharacter(
          1,
          "hello",
          "https://images.unsplash.com/photo-1628373383885-4be0bc0172fa?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1301&q=80"
        ), MarvelCharacter(
          1, "hello", ""
        )
      )
    )
  }

  @Before fun setUp() {
    MockKAnnotations.init(this, relaxUnitFun = true)
    subject = CharactersListViewModel(testDispatcher, getCharactersUseCase)
  }

  @Test fun `Should get characters`() = runBlockingTest {
    // given
    // every { dispatcherProvider.io.get() } returns StandardTestDispatcher()
    every { getCharactersUseCase.execute() } returns listFlow

    // when
    subject.getCharacters()

    // then
    verify(exactly = 2) { getCharactersUseCase.execute() }
  }
}