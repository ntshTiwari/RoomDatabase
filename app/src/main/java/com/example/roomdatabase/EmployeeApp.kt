package com.example.roomdatabase

import android.app.Application

/// this is the first class that will be called by our app
class EmployeeApp: Application() {
    /// here we define our db, and it will be created lazily (i.e when needed)
    val db by lazy {
        EmployeeDatabase.getInstance(this)
    }
}