package com.learningandroid.ui.roulette

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.learningandroid.common.ResponseStatus
import com.learningandroid.model.data.RouletteInfo
import com.learningandroid.model.repository.RouletteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RouletteViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: RouletteViewModel

    @MockK
    private lateinit var repository: RouletteRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = RouletteViewModel(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun ルーレット情報の取得に成功した場合_ResponseStatusがSuccessであること() {
        // arrange
        val response = listOf(RouletteInfo(name = "test"), RouletteInfo(name = "test2"))
        coEvery { repository.getRouletteInfo() } returns response

        // act
        viewModel.getRouletteInfo()

        // assert
        coVerify { repository.getRouletteInfo() }
        assertEquals(ResponseStatus.Success(response), viewModel.rouletteInfoStatus.value)
    }

    @Test
    fun ルーレット情報の取得結果が0件の場合_ResponseStatusがZeroMatchであること() {
        // arrange
        coEvery { repository.getRouletteInfo() } returns listOf()

        // act
        viewModel.getRouletteInfo()

        // assert
        coVerify { repository.getRouletteInfo() }
        assertEquals(ResponseStatus.ZeroMatch, viewModel.rouletteInfoStatus.value)
    }

    @Test
    fun ルーレット情報の取得に失敗した場合_ResponseStatusがErrorであること() {
        // arrange
        val t = Throwable()
        coEvery { repository.getRouletteInfo() } throws t
        // act
        viewModel.getRouletteInfo()

        // assert
        coVerify { repository.getRouletteInfo() }
        assertEquals(ResponseStatus.Error(t), viewModel.rouletteInfoStatus.value)
    }
}