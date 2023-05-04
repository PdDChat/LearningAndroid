package com.learningandroid.ui.roulette.dialog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.learningandroid.rule.MainDispatcherRule
import com.learningandroid.ui.roulette.dialog.usecase.DeleteTargetUseCase
import com.learningandroid.ui.roulette.dialog.usecase.RegisterTargetUseCase
import com.learningandroid.ui.roulette.response.DeleteStatus
import com.learningandroid.ui.roulette.response.RegisterStatus
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RouletteInfoDialogViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var subject: RouletteInfoDialogViewModel

    @MockK
    private lateinit var registerUseCase: RegisterTargetUseCase

    @MockK
    private lateinit var deleteUseCase: DeleteTargetUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        subject = RouletteInfoDialogViewModel(
            registerUseCase,
            deleteUseCase
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `registerRouletteInfo_ルーレット情報の登録処理が呼ばれること`() {
        // arrange
        coEvery { registerUseCase.registerRouletteInfo(any()) } returns RegisterStatus.Success

        // act
        subject.registerRouletteInfo("test")

        // assert
        coVerify {
            registerUseCase.registerRouletteInfo("test")
        }
    }

    @Test
    fun `deleteRouletteInfo_ルーレット情報の削除処理が呼ばれること`() {
        // arrange
        coEvery { deleteUseCase.deleteRouletteInfo(any()) } returns DeleteStatus.Success

        // act
        subject.deleteRouletteInfo("test")

        // assert
        coVerify {
            deleteUseCase.deleteRouletteInfo("test")
        }
    }
}