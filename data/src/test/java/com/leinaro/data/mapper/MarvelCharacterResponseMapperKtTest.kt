package com.leinaro.data.mapper

import com.leinaro.apis.data.MarvelCharacterResponse
import com.leinaro.apis.data.Thumbnail
import org.junit.Assert
import org.junit.Test

class MarvelCharacterResponseMapperKtTest {

  @Test
  fun `MarvelCharacterResponse to MarvelCharacterData`() {
    // given
    val marvelCharacterResponse = MarvelCharacterResponse(
      id = 1234,
      name = "Captain",
      thumbnail = Thumbnail(
        path = "",
        extension = "jpg"
      ),
      description = "",
    )

    // when
    val subject = marvelCharacterResponse.toDomainModel()
    Assert.assertEquals(1234, subject.id)
  }

  @Test
  fun `List of MarvelCharacterResponse to List of MarvelCharacter`() {
    // given
    val marvelCharacterResponseList = listOf(
      MarvelCharacterResponse(
        id = 1234,
        name = "Captain",
        thumbnail = Thumbnail(
          path = "url_imagen",
          extension = "jpg"
        ),
        description = "description",
      )
    )

    // when
    val subject = marvelCharacterResponseList.toDomainModel()
    Assert.assertEquals(1234, subject.first().id)
    Assert.assertEquals("Captain", subject.first().name)
    Assert.assertEquals("description", subject.first().description)
    Assert.assertEquals("url_imagen/standard_small.jpg", subject.first().thumbnailUrl)
    Assert.assertEquals("url_imagen/landscape_large.jpg", subject.first().landscapeUrl)
  }
}