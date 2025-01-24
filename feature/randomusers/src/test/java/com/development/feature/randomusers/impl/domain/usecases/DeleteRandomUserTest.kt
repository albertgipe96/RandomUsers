package com.development.feature.randomusers.impl.domain.usecases

import androidx.paging.PagingData
import com.development.feature.randomusers.impl.domain.repository.RandomUsersRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class DeleteRandomUserTest {

    private val repository: RandomUsersRepository = mockk()
    private val testScope = TestScope()
    private val testDispatcher = StandardTestDispatcher(testScope.testScheduler)

    private lateinit var deleteRandomUser: DeleteRandomUser

    private val id = "abc"

    @Before
    fun setup() {
        deleteRandomUser = DeleteRandomUser(repository, testDispatcher)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN random user id WHEN deleteRandomUser by id is success THEN returns Result Success`() = runTest {
        coEvery { repository.deleteRandomUser(id) } returns Result.success(Unit)

        val result = deleteRandomUser(id)

        advanceUntilIdle()

        coVerify(exactly = 1) { repository.deleteRandomUser(any()) }
        assert(result.isSuccess)
    }

    @Test
    fun `GIVEN random user id WHEN deleteRandomUser by id is failure THEN returns Result Failure`() = runTest {
        coEvery { repository.deleteRandomUser(id) } returns Result.failure(Exception())

        val result = deleteRandomUser(id)

        advanceUntilIdle()

        coVerify(exactly = 1) { repository.deleteRandomUser(any()) }
        assert(result.isFailure)
    }

}