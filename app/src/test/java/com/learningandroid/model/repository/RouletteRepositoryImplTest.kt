package com.learningandroid.model.repository

import android.database.sqlite.SQLiteConstraintException
import com.learningandroid.model.dao.RouletteInfoDao
import com.learningandroid.model.data.RouletteInfo
import com.learningandroid.ui.roulette.response.DeleteAllStatus
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
    fun `registerRouletteInfo_登録に成功した場合_Successが返ること`() = runBlocking {
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
    fun `registerRouletteInfo_登録済みの名前を指定した場合_Errorが返ること`() = runBlocking {
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
    fun `deleteRouletteInfo_削除に成功した場合_Successが返ること`() = runBlocking {
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
    fun `deleteRouletteInfo_存在しない名前を指定した場合_Errorが返ること`() = runBlocking {
        // arrange
        coEvery { dao.deleteCell(any()) } returns 0
        val deleteName = "test"

        // act
        val result = subject.deleteRouletteInfo(deleteName)

        // assert
        coVerify { dao.deleteCell(deleteName) }
        assertEquals(DeleteStatus.Error, result)
    }

    @Test
    fun `deleteAllRouletteInfo_削除に成功した場合_Successが返ること`() = runBlocking {
        // arrange
        val targetList = listOf(RouletteInfo("test1"), RouletteInfo("test2"))
        coEvery { dao.getAll() } returns targetList
        coEvery { dao.delete(targetList) } returns mockk()

        // act
        val result = subject.deleteAllRouletteInfo()

        // assert
        coVerify { dao.getAll() }
        coVerify { dao.delete(targetList) }
        assertEquals(DeleteAllStatus.Success, result)
    }

    @Test
    fun `deleteAllRouletteInfo_一覧取得時に例外が発生した場合_Failedが返ること`() = runBlocking {
        // arrange
        coEvery { dao.getAll() } throws Exception()

        // act
        val result = subject.deleteAllRouletteInfo()

        // assert
        coVerify { dao.getAll() }
        assertEquals(DeleteAllStatus.Failed, result)
    }

    @Test
    fun `deleteAllRouletteInfo_削除時に例外が発生した場合_Failedが返ること`() = runBlocking {
        // arrange
        val targetList = listOf(RouletteInfo("test1"), RouletteInfo("test2"))
        coEvery { dao.getAll() } returns targetList
        coEvery { dao.delete(targetList) } throws Exception()

        // act
        val result = subject.deleteAllRouletteInfo()

        // assert
        coVerify { dao.getAll() }
        coVerify { dao.delete(targetList) }
        assertEquals(DeleteAllStatus.Failed, result)
    }
}