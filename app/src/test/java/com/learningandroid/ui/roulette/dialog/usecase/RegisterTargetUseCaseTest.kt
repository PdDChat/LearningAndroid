package com.learningandroid.ui.roulette.dialog.usecase

import com.learningandroid.model.repository.RouletteRepository
import com.learningandroid.ui.roulette.response.RegisterStatus
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class RegisterTargetUseCaseTest {

    private lateinit var subject: RegisterTargetUseCase

    @MockK
    private lateinit var repository: RouletteRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        subject = RegisterTargetUseCase(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `registerRouletteInfo_ルーレットの登録処理が呼ばれること`() = runBlocking {
        // arrange
        coEvery { repository.registerRouletteInfo(any()) } returns  RegisterStatus.Success
        val registerName = "test"

        // act
        subject.registerRouletteInfo(registerName)

        // assert
        coVerify { repository.registerRouletteInfo(registerName) }
    }
}