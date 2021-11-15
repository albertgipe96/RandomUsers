package com.albert.randomusers.datapersistance

import android.content.Context
import android.content.SharedPreferences

class DataPersistanceImpl(private val appContext: Context) : DataPersistance {

    private var sharedPreferences: SharedPreferences? = null

    companion object {
        const val SHARED_PREFS_NAME = "SharedPreferences"
        const val SHARED_PREFS_SEED = "SharedPrefsSeed"
        const val SHARED_PREFS_ID_LIST = "SharedPrefsIdList"
        val SEED_DEFAULT = null
        val ID_LIST_DEFAULT = hashSetOf<String>()
    }

    init {
        sharedPreferences = appContext.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }

    override fun saveSeed(value: String) {
        sharedPreferences?.edit()?.putString(SHARED_PREFS_SEED, value)?.apply()
    }

    override fun getSeed(): String? {
        return sharedPreferences?.getString(SHARED_PREFS_SEED, SEED_DEFAULT)
    }

    override fun saveDeletedUsersIdList(value: MutableList<String>) {
        val valueSet = HashSet<String>()
        valueSet.addAll(value)
        sharedPreferences?.edit()?.putStringSet(SHARED_PREFS_ID_LIST, valueSet)?.apply()
    }

    override fun getDeletedUsersIdList(): MutableList<String> {
        val valueSet = sharedPreferences?.getStringSet(SHARED_PREFS_ID_LIST, ID_LIST_DEFAULT)
        return valueSet?.toMutableList() ?: mutableListOf()
    }
}