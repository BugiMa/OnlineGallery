package com.bugima.onlinegallery.domain.usecase

import com.bugima.onlinegallery.domain.repository.PictureRepository
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class RefreshPicturesUseCaseTest {
    private lateinit var picturesRepository: PictureRepository
    private lateinit var refreshPicturesUseCase: RefreshPicturesUseCase

    @Before
    fun setup() {
        picturesRepository = mockk()
        refreshPicturesUseCase = RefreshPicturesUseCase(picturesRepository)
    }

    @After
    fun teardown() {
        clearAllMocks()
    }

    @Test
    fun `invoke should call repository`() = runTest {
        // Given
        coEvery { picturesRepository.resetPagination() } just Runs

        // When
        refreshPicturesUseCase.execute()

        // Then
        coVerify(exactly = 1) { picturesRepository.resetPagination() }
    }
}