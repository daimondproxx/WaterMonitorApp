package com.example.watermonitorapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.watermonitorapp.data.entity.UserParams
import kotlinx.coroutines.flow.Flow


@Dao
interface UserParamsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserParams(userParams: UserParams)

    @Query("SELECT * FROM UserParams")
    fun getAllUserParams(): Flow<List<UserParams>>

    @Query("SELECT * FROM UserParams WHERE id = :id")
    fun getUserParamsById(id: Int): Flow<UserParams>

    @Query( "SELECT * FROM UserParams LIMIT 1")
    suspend fun getUser(): UserParams?

}