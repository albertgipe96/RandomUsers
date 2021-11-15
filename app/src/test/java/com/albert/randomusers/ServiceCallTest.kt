package com.albert.randomusers

import androidx.lifecycle.MutableLiveData
import com.albert.randomusers.business.usecases.RandomUserInteractor
import com.albert.randomusers.datapersistance.DataPersistance
import com.albert.randomusers.presentation.models.RandomUserUIModel
import com.albert.randomusers.presentation.ui.randomuserlist.RandomUserListViewModel
import io.mockk.impl.annotations.MockK
import org.junit.Test

import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ServiceCallTest {

    val mutableUsersList = mutableListOf<RandomUserUIModel>()
    val deletedUsersIdList = dataPersistance.getDeletedUsersIdList()
    val randomUserList: MutableLiveData<MutableList<RandomUserUIModel>> by lazy {
        MutableLiveData<MutableList<RandomUserUIModel>>(mutableUsersList)
    }

    val loadingRandomUserListFirstCall: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(true)
    }

    val loadingRandomUserListOtherCalls: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    private lateinit var viewModel: RandomUserListViewModel
    private val page = 1

    @MockK
    private lateinit var interactor: RandomUserInteractor

    @MockK
    private lateinit var dataPersistance: DataPersistance

    @MockK
    private lateinit var user: RandomUserUIModel

    @Before
    fun setUp() {
        viewModel = RandomUserListViewModel(interactor, dataPersistance)
    }

    @Test
    fun check_User_List_Size_From_Service_Is_10() {
        viewModel.getRandomUsersList(page)
        assert(randomUserList.value?.size == 10)
    }

    @Test
    fun check_Loader_Set_To_False_After_Service_Call_Is_Finished() {
        viewModel.getRandomUsersList(page)
        assert(loadingRandomUserListFirstCall.value == false && loadingRandomUserListOtherCalls.value == false)
    }

    @Test
    fun delete_User_From_List_Add_New_Id_To_Deleted_List() {
        val initialSize = deletedUsersIdList.size
        viewModel.deleteUserFromList(user)
        val actualSize = deletedUsersIdList.size
        assert(initialSize == actualSize)
    }

}