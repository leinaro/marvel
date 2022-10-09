package com.leinaro.domain.mapper

import com.leinaro.data.data.ComicData
import com.leinaro.data.data.MarvelCharacterData
import com.leinaro.domain.ui_models.CharacterUiModel
import com.leinaro.domain.ui_models.ComicUiModel
import org.junit.Assert
import org.junit.Test

internal class MarvelCharacterDataMapperKtTest {
    @Test
    fun `MarvelCharacterData to CharacterUiModel`() {
        // given
        val marvelCharacterData = MarvelCharacterData(
            id = 1234L,
            name = "Captain",
            description = "description",
            thumbnailUrl = "image_url/portrait_xlarge.jpg",
            landscapeUrl = "image_url/portrait_xlarge.jpg",
            comics = listOf(
                ComicData(
                    name = "Captain 1",
                    imageUrl = "image_url/portrait_xlarge.jpg",
                )
            ),
        )

        // when
        val subject = marvelCharacterData.toUiModel()

        // then
        Assert.assertEquals(1234L, subject.id)
        Assert.assertEquals("Captain", subject.name)
        Assert.assertEquals("image_url/portrait_xlarge.jpg", subject.thumbnailUrl)
        Assert.assertEquals(1, subject.comics.size)
    }

    @Test
    fun `CharacterUiModel default constructor marker`() {
        // given

        // when
        val subject = CharacterUiModel()

        // then
        Assert.assertEquals(-1, subject.id)
        Assert.assertEquals("", subject.name)
        Assert.assertEquals("", subject.thumbnailUrl)
        Assert.assertEquals("", subject.description)
        Assert.assertEquals("", subject.landscapeUrl)
        Assert.assertEquals(listOf<ComicUiModel>(), subject.comics)
    }

    @Test
    fun `ComicUiModel default constructor marker`() {
        // given

        // when
        val subject = ComicUiModel()

        // then
        Assert.assertEquals("", subject.name)
        Assert.assertEquals("", subject.imageUrl)
    }
}