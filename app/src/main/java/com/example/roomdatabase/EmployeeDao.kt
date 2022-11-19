package com.example.roomdatabase

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/// DAO => Data interface object

@Dao
interface EmployeeDao {

    @Insert /// it will be a suspend/coroutine function, as might take time, and will happen in bg
    suspend fun insert(employeeEntity: EmployeeEntity)

    @Update
    suspend fun update(employeeEntity: EmployeeEntity)

    @Delete
    suspend fun delete(employeeEntity: EmployeeEntity)

    @Query("SELECT * FROM `employee-table`") /// we write our query here which will be used to give us the values
    fun fetchAllEmployees(): Flow<List<EmployeeEntity>>
    /// this will return a flow, which is a stream of entities, here EmployeeEntity

    @Query("SELECT * FROM `employee-table` where id=:id") /// we write our query here which will be used to give us the values
    fun fetchAllEmployeeById(id: Int): Flow<EmployeeEntity>
}