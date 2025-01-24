@file:OptIn(ExperimentalCoroutinesApi::class)

package com.development.feature.randomusers.impl.presentation.vm

import androidx.paging.PagingData
import com.development.feature.randomusers.impl.domain.model.RandomUser
import com.development.feature.randomusers.impl.domain.model.RandomUserLocation
import com.development.feature.randomusers.impl.domain.usecases.DeleteRandomUser
import com.development.feature.randomusers.impl.domain.usecases.GetRandomUsersList
import com.development.feature.randomusers.impl.presentation.mapper.DomainToUiMapper
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

class RandomUsersListViewModelTest {

    private val getRandomUsersList: GetRandomUsersList = mockk()
    private val deleteRandomUser: DeleteRandomUser = mockk()
    private val domainToUiMapper: DomainToUiMapper = mockk()

    private val testScope = TestScope()
    private val testDispatcher = StandardTestDispatcher(testScope.testScheduler)

    private lateinit var viewModel: RandomUsersListViewModel

    private val randomUsers = listOf(
        RandomUser("abc", "name", "surname", "email", "picture", "phone", "gender", RandomUserLocation("street", "city", "state"), "date"),
        RandomUser("def", "name", "surname", "email", "picture", "phone", "gender", RandomUserLocation("street", "city", "state"), "date"),
        RandomUser("ghi", "name", "surname", "email", "picture", "phone", "gender", RandomUserLocation("street", "city", "state"), "date")
    )
    private val randomUsersFlow: Flow<PagingData<RandomUser>> = flow {
        PagingData.from(randomUsers)
    }

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN RandomUsersListViewModel WHEN init THEN state is Loaded`() = runTest {
        coEvery { getRandomUsersList() } returns randomUsersFlow

        viewModel = RandomUsersListViewModel(getRandomUsersList, deleteRandomUser, domainToUiMapper)

        advanceUntilIdle()

        coVerify(exactly = 1) { getRandomUsersList() }
        assert(viewModel.uiState.value is RandomUsersListUiState.Loaded)
    }

    @Test
    fun `GIVEN onAction DeleteUser received WHEN random user is deleted correctly THEN event UserDeletedSuccessfully is sent`() = runTest {
        val id = "abc"
        coEvery { getRandomUsersList() } returns randomUsersFlow
        coEvery { deleteRandomUser(id) } returns Result.success(Unit)

        viewModel = RandomUsersListViewModel(getRandomUsersList, deleteRandomUser, domainToUiMapper)
        viewModel.onAction(RandomUsersListAction.DeleteUser(id))

        assert(viewModel.events.first() == RandomUsersListEvent.UserDeletedSuccessfully)
    }

    @Test
    fun `GIVEN onAction DeleteUser received WHEN random user deletion fails THEN event UserDeletedError is sent`() = runTest {
        val id = "abc"
        coEvery { getRandomUsersList() } returns randomUsersFlow
        coEvery { deleteRandomUser(id) } returns Result.failure(Exception())

        viewModel = RandomUsersListViewModel(getRandomUsersList, deleteRandomUser, domainToUiMapper)
        viewModel.onAction(RandomUsersListAction.DeleteUser(id))

        assert(viewModel.events.first() == RandomUsersListEvent.UserDeletedError)
    }

}