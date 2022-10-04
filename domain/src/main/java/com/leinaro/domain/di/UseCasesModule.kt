package com.leinaro.domain.di

import com.leinaro.domain.Repository
import com.leinaro.domain.RepositoryImpl
import com.leinaro.domain.usecases.GetCharacterDetailsUseCase
import com.leinaro.domain.usecases.GetCharacterDetailsUseCaseImpl
import com.leinaro.domain.usecases.GetCharactersUseCase
import com.leinaro.domain.usecases.GetCharactersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {
  @Binds
  abstract fun bindsGetCharactersUseCase(
    getCharactersUseCaseImpl: GetCharactersUseCaseImpl
  ): GetCharactersUseCase

  @Binds
  abstract fun bindsGetCharacterDetailsUseCase(
    getGetCharacterDetailsUseCaseImpl: GetCharacterDetailsUseCaseImpl
  ): GetCharacterDetailsUseCase

  @Binds
  abstract fun bindsRepository(
    repositoryImpl: RepositoryImpl
  ): Repository
}