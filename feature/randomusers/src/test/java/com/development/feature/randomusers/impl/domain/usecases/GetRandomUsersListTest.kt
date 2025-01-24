@file:OptIn(ExperimentalCoroutinesApi::class)

package com.development.feature.randomusers.impl.domain.usecases

import androidx.paging.PagingData
import com.development.feature.randomusers.impl.domain.model.RandomUser
import com.development.feature.randomusers.impl.domain.model.RandomUserLocation
import com.development.feature.randomusers.impl.domain.repository.RandomUsersRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetRandomUsersListTest {

    private val repository: RandomUsersRepository = mockk()
    private val testScope = TestScope()
    private val testDispatcher = StandardTestDispatcher(testScope.testScheduler)

    private lateinit var getRandomUsersList: GetRandomUsersList

    private val randomUsers = listOf(
        RandomUser("abc", "name", "surname", "email", "picture", "phone", "gender", RandomUserLocation("street", "city", "state"), "date"),
        RandomUser("def", "name", "surname", "email", "picture", "phone", "gender", RandomUserLocation("street", "city", "state"), "date"),
        RandomUser("ghi", "name", "surname", "email", "picture", "phone", "gender", RandomUserLocation("street", "city", "state"), "date")
    )
    private val randomUsersFlow: Flow<PagingData<RandomUser>> = flow {
        PagingData.from(randomUsers)
    }
    private val deletedIds = listOf("abc")

    @Before
    fun setup() {
        getRandomUsersList = GetRandomUsersList(repository, testDispatcher)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN randomUsers with no deleted users WHEN getRandomUsersList THEN returns randomUsers paged list`() = runTest {
        coEvery { repository.getRandomUsers() } returns randomUsersFlow
        coEvery { repository.getDeletedIds() } returns emptyList()

        val result = getRandomUsersList()

        advanceUntilIdle()

        coVerify(exactly = 1) { repository.getRandomUsers() }
        coVerify(exactly = 1) { repository.getDeletedIds() }
        result.collect {
            assert(it == PagingData.from(randomUsers))
        }
    }

    @Test
    fun `GIVEN randomUsers with sime deleted users WHEN getRandomUsersList THEN returns randomUsers paged list without the ones deleted`() = runTest {
        coEvery { repository.getRandomUsers() } returns randomUsersFlow
        coEvery { repository.getDeletedIds() } returns deletedIds

        val result = getRandomUsersList()

        advanceUntilIdle()

        coVerify(exactly = 1) { repository.getRandomUsers() }
        coVerify(exactly = 1) { repository.getDeletedIds() }
        result.collect {
            assert(it == PagingData.from(randomUsers.filter { it.id !in deletedIds }))
        }
    }

}