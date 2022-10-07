package com.leinaro.character_details

import androidx.lifecycle.SavedStateHandle
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
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterDetailViewModelTest {
  private val testDispatcher = TestCoroutineDispatcher()

  @MockK(relaxed = true)
  private lateinit var getCharacterDetailsUseCase: GetCharacterDetailsUseCase

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
    val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
    every { savedStateHandle.get<Long>("id") } returns 12345
    subject = CharacterDetailViewModel(savedStateHandle, testDispatcher, getCharacterDetailsUseCase)
  }

  @Test fun `Should get character details`() = runBlockingTest {
    // given
    every { getCharacterDetailsUseCase.execute(any()) } returns marvelCharacter

    // when
    subject.getCharacterDetails()

    // then
    verify(exactly = 1) { getCharacterDetailsUseCase.execute(any()) }
  }

}