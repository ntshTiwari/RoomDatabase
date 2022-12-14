package com.example.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee-table") /// we define the table name here that will be used to store the entity
data class EmployeeEntity(
    /// we need to make all variables var, else we get an error nowadays
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0, /// this will be the primary key, it will be autogenerated, here incremented
    var name: String = "", /// if we don't define a column name then it will be same as the variable name
    @ColumnInfo(name = "email-id") /// now the email field in table will have column name `email-id`
    var email: String = "",
)
