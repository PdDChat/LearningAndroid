package com.learningandroid.ui.roulette.dialog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.learningandroid.rule.MainDispatcherRule
import com.learningandroid.ui.roulette.dialog.usecase.DeleteTargetUseCase
import com.learningandroid.ui.roulette.dialog.usecase.RegisterTargetUseCase
import com.learningandroid.ui.roulette.response.DeleteStatus
import com.learningandroid.ui.roulette.response.RegisterStatus
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.*
import org.junit.Assert.assertEquals

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
    fun `registerRouletteInfo_未入力の場合_NotEnteredが返ること`() {
        // act
        subject.registerRouletteInfo("")

        // assert
        coVerify {
            registerUseCase.registerRouletteInfo("") wasNot Called
        }
        assertEquals(RegisterStatus.NotEntered, subject.registerStatus.value)
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

    @Test
    fun `deleteRouletteInfo_未入力の場合_NotEnteredが返ること`() {
        // act
        subject.deleteRouletteInfo("")

        // assert
        coVerify {
            deleteUseCase.deleteRouletteInfo("") wasNot Called
        }
        assertEquals(DeleteStatus.NotEntered, subject.deleteStatus.value)
    }
}