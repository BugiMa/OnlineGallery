package com.bugima.onlinegallery.domain.usecase

import com.bugima.onlinegallery.domain.model.Picture
import com.bugima.onlinegallery.domain.repository.PictureRepository
import com.bugima.onlinegallery.util.AppException
import com.bugima.onlinegallery.util.wrapper.Result
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class FetchPicturesUseCaseTest {
    private lateinit var picturesRepository: PictureRepository
    private lateinit var fetchPicturesUseCase: FetchPicturesUseCase

    @Before
    fun setup() {
        picturesRepository = mockk()
        fetchPicturesUseCase = FetchPicturesUseCase(picturesRepository)
    }

    @After
    fun teardown() {
        clearAllMocks()
    }

    @Test
    fun `invoke should return pictures when fetch is successful`() = runTest {
        // Given
        val expectedPictures = Result.Success(
            listOf(
                Picture(
                    id = "1",
                    author = "John Doe",
                    width = 1234,
                    height = 4321,
                    url = "https1",
                    downloadUrl = "https2"
                )
            )
        )
        coEvery { picturesRepository.fetchPictures() } returns expectedPictures

        // When
        val result = fetchPicturesUseCase.execute()

        // Then
        coVerify(exactly = 1) { picturesRepository.fetchPictures() }
        assertEquals(expectedPictures, result)
    }

    @Test
    fun `invoke should return error when fetch fails`() = runTest {
        // Given
        val expectedError = Result.Failure(AppException.NoMoreDataException)
        coEvery { picturesRepository.fetchPictures() } returns expectedError

        // When
        val result = fetchPicturesUseCase.execute()

        // Then
        coVerify(exactly = 1) { picturesRepository.fetchPictures() }
        assertTrue(result is Result.Failure)
        assertEquals(expectedError, result)
    }

}