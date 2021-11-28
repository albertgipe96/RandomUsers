package com.albert.randomusers.presentation.ui.randomuserlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albert.randomusers.business.usecases.RandomUserInteractor
import com.albert.randomusers.datapersistance.DataPersistance
import com.albert.randomusers.presentation.models.RandomUserUIModel
import com.albert.randomusers.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RandomUserListViewModel @ViewModelInject constructor(
    private val interactor: RandomUserInteractor,
    private val dataPersistance: DataPersistance
) : ViewModel() {

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
    val loadingError: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }

    private var isSearching = false
    private var firstTime = true
    private var cachedList: MutableList<RandomUserUIModel>? = mutableListOf()

    private var page: Int = 1

    fun getRandomUsersList() {
        viewModelScope.launch {

            if (page > 1) loadingRandomUserListOtherCalls.postValue(true)

            // Load from Data Persistance
            var seed = dataPersistance.getSeed()
            if (seed == null) seed = generateRandomSeed()

            val result = interactor.getRandomUserListUseCase(page, seed)
            when (result) {
                is Resource.Success -> {
                    loadingError.postValue("")
                    result.data?.let { resultData ->
                        mutableUsersList.addAll(resultData.filter {
                            !deletedUsersIdList.contains(it.id) && !mutableUsersList.contains(
                                it
                            )
                        })
                        randomUserList.postValue(mutableUsersList)
                        loadingRandomUserListFirstCall.postValue(false)
                        loadingRandomUserListOtherCalls.postValue(false)
                    }?:showErrorFromService(result.message)
                    page += 1
                }
                is Resource.Error -> {
                    showErrorFromService(result.message)
                }
            }
        }
    }

    private fun showErrorFromService(message: String?) {
        loadingError.postValue(message)
        loadingRandomUserListFirstCall.postValue(false)
        loadingRandomUserListOtherCalls.postValue(false)
    }

    fun deleteUserFromList(user: RandomUserUIModel) {
        user.id?.let { deletedUsersIdList.add(it) }
        dataPersistance.saveDeletedUsersIdList(deletedUsersIdList)
        mutableUsersList.remove(user)
    }

    fun searchUser(query: String) {
        if (firstTime) cachedList = randomUserList.value
        val listToSearch = randomUserList.value
        if (query.isEmpty()) {
            randomUserList.value = cachedList
            isSearching = false
            firstTime = true
        } else {
            isSearching = true
        }
        val results = listToSearch?.filter {
            it.name?.first?.contains(query.trim(), ignoreCase = true) ?: false || it.name?.last?.contains(query.trim(), ignoreCase = true) ?: false
                    || it.email?.contains(query.trim(), ignoreCase = true) ?: false
        }?.toMutableList()
        if (firstTime) {
            cachedList = randomUserList.value
            firstTime = false
        }
        results?.let { if (isSearching) randomUserList.postValue(it) else randomUserList.postValue(cachedList) }

    }

    private fun generateRandomSeed(): String {
        val seed = (0..99).random().toString()
        dataPersistance.saveSeed(seed)
        return seed
    }

    fun getDeletedUsersListSize(): Int {
        return deletedUsersIdList.size
    }

}