package com.example.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/// here we define our database and version, we define our entities as List of EmployeeEntity
// the version can be helpful if we change our database (schema/type) in the future
@Database(entities = [EmployeeEntity::class], version = 1)
abstract class EmployeeDatabase: RoomDatabase() {

    /// Connects the database to the DAO.
    abstract fun employeeDao(): EmployeeDao

    /// can be used to instantiate a new Database object
    companion object {
        /// this will be used to access the database by all the threads, as a Singleton
        @Volatile
        private var INSTANCE: EmployeeDatabase? = null

        fun getInstance(context: Context): EmployeeDatabase {

            /// in order to prevent multiple threads calling and instantiating it multiple times,
            /// we lock it and use synchronized
            synchronized(this){
                var instance = INSTANCE

                /// if it is null, then create a new instance
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EmployeeDatabase::class.java,
                        "employee_database"
                    ).fallbackToDestructiveMigration() /// we delete the database as we dont have a migration right now
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}