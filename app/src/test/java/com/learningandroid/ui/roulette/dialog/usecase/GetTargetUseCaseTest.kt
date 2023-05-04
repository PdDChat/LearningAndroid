package com.learningandroid.ui.roulette.dialog.usecase

import com.learningandroid.model.data.RouletteInfo
import com.learningandroid.model.repository.RouletteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetTargetUseCaseTest {

    private lateinit var subject: GetTargetUseCase

    @MockK
    private lateinit var repository: RouletteRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        subject = GetTargetUseCase(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getRouletteInfo_ルーレットの登録一覧取得処理が呼ばれること`() = runBlocking {
        // arrange
        val rouletteList = listOf(RouletteInfo(name = "test"), RouletteInfo("test2"))
        coEvery { repository.getRouletteInfo() } returns rouletteList

        // act
        val result = subject.getRouletteInfo()

        // assert
        coVerify { repository.getRouletteInfo() }
        assertEquals(rouletteList, result)
    }
}