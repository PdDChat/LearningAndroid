package com.learningandroid.ui.roulette.dialog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.learningandroid.model.repository.RouletteRepository
import com.learningandroid.rule.MainDispatcherRule
import io.mockk.MockKAnnotations
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

    private lateinit var viewModel: RouletteInfoDialogViewModel

    @MockK
    private lateinit var repository: RouletteRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = RouletteInfoDialogViewModel(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `registerRouletteInfo_ルーレット情報の登録処理が走ること`() {
        // act
        viewModel.registerRouletteInfo("test")

        coVerify {
            repository.registerRouletteInfo("test")
        }
    }

    @Test
    fun `deleteRouletteInfo_ルーレット情報の削除処理が走ること`() {
        // act
        viewModel.deleteRouletteInfo("test")

        coVerify {
            repository.deleteRouletteInfo("test")
        }
    }
}