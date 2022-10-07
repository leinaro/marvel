package com.leinaro.characters_list

import androidx.paging.Pager
import com.leinaro.characters_list.ui_state.CharactersListUiState
import com.leinaro.domain.ui_models.CharacterUiModel
import com.leinaro.domain.usecases.GetCharactersUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class CharactersListViewModelTest {

  private val testDispatcher = TestCoroutineDispatcher()

  @MockK(relaxed = true)
  private lateinit var getCharactersUseCase: GetCharactersUseCase

  @ExperimentalCoroutinesApi
  @get:Rule
  var mainCoroutineRule = MainCoroutineRule()

  private lateinit var subject: CharactersListViewModel

  @Before fun setUp() {
    MockKAnnotations.init(this, relaxUnitFun = true)
    subject = CharactersListViewModel(testDispatcher, getCharactersUseCase)
  }

  @Test fun `Should get characters`() = runBlockingTest {
    // given
    val pager = mockk<Pager<Int, CharacterUiModel>>(relaxed = true)
    every { getCharactersUseCase.execute() } returns pager

    // when
    subject.getCharacters()

    // then
    verify(exactly = 2) { getCharactersUseCase.execute() }
    assert(subject.uiState is CharactersListUiState.ShowCharactersListUiState)
  }

  @Test fun `Should refresh characters`() = runBlockingTest {
    // given
    val pager = mockk<Pager<Int, CharacterUiModel>>(relaxed = true)
    every { getCharactersUseCase.execute() } returns pager

    // when
    subject.onRefresh()

    // then
    verify(exactly = 2) { getCharactersUseCase.execute() }
    assert(subject.uiState is CharactersListUiState.ShowCharactersListUiState)
  }
}