package com.leinaro.character_details

import androidx.lifecycle.SavedStateHandle
import com.leinaro.character_details.ui_state.CharacterDetailsUiState
import com.leinaro.core.Result
import com.leinaro.domain.ui_models.CharacterUiModel
import com.leinaro.domain.usecases.GetCharacterDetailsUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class CharacterDetailViewModelTest {

  private val testDispatcher = TestCoroutineDispatcher()

  @MockK(relaxed = true)
  private lateinit var getCharacterDetailsUseCase: GetCharacterDetailsUseCase

  @MockK(relaxed = true)
  private lateinit var savedStateHandle : SavedStateHandle

  @ExperimentalCoroutinesApi
  @get:Rule
  var mainCoroutineRule = MainCoroutineRule()

  private lateinit var subject: CharacterDetailViewModel

  private val marvelCharacter = flow {
    emit(
      CharacterUiModel(
        1,
        "hello",
        "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/standard_small.jpg",
        "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/standard_small.jpg",
        "",
        listOf()
      )
    )
  }

  @Before fun setUp() {
    MockKAnnotations.init(this, relaxUnitFun = true)
  }

  @Test fun `Should get character details`() = runBlockingTest {
    // given
    every { getCharacterDetailsUseCase.execute(any()) } returns marvelCharacter
    every { savedStateHandle.get<Long>("id") } returns 12345L

    // when
    subject = CharacterDetailViewModel(savedStateHandle, testDispatcher, getCharacterDetailsUseCase)

    // then
    verify(exactly = 1) { getCharacterDetailsUseCase.execute(any()) }
    assert(subject.uiState is Result.Success)
    val data = (subject.uiState as Result.Success).data
    assert(data is CharacterDetailsUiState)
    val character = (data as CharacterDetailsUiState).characterDetails
    assert(character?.name == "hello")
  }

  @Test fun `Should show error when characterId is -1L`() = runBlockingTest {
    // given
    every { savedStateHandle.get<Long>("id") } returns -1L
    every { getCharacterDetailsUseCase.execute(any()) } returns marvelCharacter

    // when
    subject = CharacterDetailViewModel(savedStateHandle, testDispatcher, getCharacterDetailsUseCase)

    // then
    verify(exactly = 0) { getCharacterDetailsUseCase.execute(any()) }
    assert(subject.uiState is Result.Error)
    assert((subject.uiState as Result.Error).exception?.message == "Character id could not be null")
  }

  //@Test(expected = Throwable::class)
  @Test fun `Should show error when getCharacterDetailsUseCase fails`() = runBlockingTest {
    // given
    every { getCharacterDetailsUseCase.execute(any()) } returns flow{ throw Throwable("Test")}
    every { savedStateHandle.get<Long>("id") } returns 12345L

    // when
    subject = CharacterDetailViewModel(savedStateHandle, testDispatcher, getCharacterDetailsUseCase)

    // then
    verify(exactly = 1) { getCharacterDetailsUseCase.execute(any()) }
    assert(subject.uiState is Result.Error)
    assert((subject.uiState as Result.Error).message == "Test")
  }
}