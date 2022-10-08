package com.leinaro.data.mapper

import com.leinaro.apis.data.ComicsResponse
import com.leinaro.apis.data.Thumbnail
import org.junit.Assert
import org.junit.Test

class ComicsResponseMapperKtTest {

  @Test
  fun `ComicsResponse to ComicData`() {
    // given
    val comicsResponse = ComicsResponse(
      id = 1234,
      title = "Captain",
      thumbnail = Thumbnail(
        path = "",
        extension = "jpg"
      ),
      description = "",
    )

    // when
    val subject = comicsResponse.toDomainModel()
    Assert.assertEquals("Captain", subject.name)
  }

  @Test
  fun `List of ComicsResponse to List of MarvelCharacter`() {
    // given
    val comicsResponseList = listOf(
      ComicsResponse(
        id = 1234,
        title = "Captain",
        thumbnail = Thumbnail(
          path = "",
          extension = "jpg"
        ),
        description = "",
      )
    )

    // when
    val subject = comicsResponseList.toDomainModel()
    Assert.assertEquals("Captain", subject.first().name)
  }

}