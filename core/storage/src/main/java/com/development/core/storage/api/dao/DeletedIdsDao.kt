package com.development.core.storage.api.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.development.core.storage.impl.model.DeletedIdEntity

@Dao
interface DeletedIdsDao {

    @Query("SELECT * FROM deleted_ids")
    fun getDeletedIds(): List<DeletedIdEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeletedId(id: DeletedIdEntity)

}