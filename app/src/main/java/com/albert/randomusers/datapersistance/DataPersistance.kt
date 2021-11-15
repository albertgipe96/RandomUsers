package com.albert.randomusers.datapersistance

interface DataPersistance {

    fun saveSeed(value: String)

    fun getSeed(): String?

    fun saveDeletedUsersIdList(value: MutableList<String>)

    fun getDeletedUsersIdList(): MutableList<String>

}