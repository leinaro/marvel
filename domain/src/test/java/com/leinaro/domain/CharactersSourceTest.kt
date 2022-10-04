package com.leinaro.domain

import com.leinaro.apis.services.CharactersServices
import io.mockk.impl.annotations.MockK

internal class CharactersSourceTest {
  @MockK(relaxed = true)
  private lateinit var charactersServices: CharactersServices

  private lateinit var subject: CharactersSource

}