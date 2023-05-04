package com.learningandroid.ui.roulette.dialog.usecase

import com.learningandroid.model.repository.RouletteRepository
import com.learningandroid.ui.roulette.response.DeleteStatus
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class DeleteTargetUseCaseTest {

    private lateinit var subject: DeleteTargetUseCase

    @MockK
    private lateinit var repository: RouletteRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        subject = DeleteTargetUseCase(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `deleteRouletteInfo_ルーレットの削除処理が呼ばれること`() = runBlocking {
        // arrange
        coEvery { repository.deleteRouletteInfo(any()) } returns DeleteStatus.Success
        val deleteName = "test"

        // act
        subject.deleteRouletteInfo(deleteName)
        
        // assert
        coVerify { repository.deleteRouletteInfo(deleteName) }
    }
}