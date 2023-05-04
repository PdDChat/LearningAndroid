package com.learningandroid.model.repository

import android.database.sqlite.SQLiteConstraintException
import com.learningandroid.model.dao.RouletteInfoDao
import com.learningandroid.model.data.RouletteInfo
import com.learningandroid.ui.roulette.response.DeleteStatus
import com.learningandroid.ui.roulette.response.RegisterStatus
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RouletteRepositoryImplTest {

    @MockK
    private lateinit var dao: RouletteInfoDao

    private lateinit var subject: RouletteRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        subject = RouletteRepositoryImpl(dao)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `registerRouletteInfo_成功時にSuccessが返ること`() = runBlocking {
        // arrange
        coEvery { dao.insert(any()) } just Runs
        val registerName = "test"

        // act
        val result = subject.registerRouletteInfo(registerName)

        // assert
        coVerify { dao.insert(RouletteInfo(registerName)) }
        assertEquals(RegisterStatus.Success, result)
    }

    @Test
    fun `registerRouletteInfo_登録済みの名前を指定した場合にErrorが返ること`() = runBlocking {
        // arrange
        coEvery { dao.insert(any()) } throws SQLiteConstraintException()
        val registerName = "test"

        // act
        val result = subject.registerRouletteInfo(registerName)

        // assert
        coVerify { dao.insert(RouletteInfo(registerName)) }
        assertEquals(RegisterStatus.Error, result)
    }

    @Test
    fun `deleteRouletteInfo_削除成功時にSuccessが返ること`() = runBlocking {
        // arrange
        coEvery { dao.deleteCell(any()) } returns 1
        val deleteName = "test"

        // act
        val result = subject.deleteRouletteInfo(deleteName)

        // assert
        coVerify { dao.deleteCell(deleteName) }
        assertEquals(DeleteStatus.Success, result)
    }

    @Test
    fun `deleteRouletteInfo_存在しない名前を指定した場合にErrorが返ること`() = runBlocking {
        // arrange
        coEvery { dao.deleteCell(any()) } returns 0
        val deleteName = "test"

        // act
        val result = subject.deleteRouletteInfo(deleteName)

        // assert
        coVerify { dao.deleteCell(deleteName) }
        assertEquals(DeleteStatus.Error, result)
    }
}