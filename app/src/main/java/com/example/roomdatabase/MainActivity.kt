package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

/// when we use room, then we need a database and a dao
/// database is where our data gets stored and dao helps us to do that
/// the database and the dao should be linked as well
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /// we need to create a dao here and use it throughout our app, the singleton will help us with that
        val employeeDao = (application as EmployeeApp).db.employeeDao()
        /// the database and the dao should be connected, what we did in our EmployeeDatabase class

        /// we can also directly use this instead of the above way, the above just makes sure that our db can get instatntiated at the start of the app
//        val employeeDao = EmployeeDatabase.getInstance(this).employeeDao()

        findViewById<Button>(R.id.insertButton).setOnClickListener {
            addRecord(employeeDao)
        }
    }

    private fun addRecord(employeeDao: EmployeeDao){
        lifecycleScope.launch{
            employeeDao.insert(EmployeeEntity(name = "Nitesh new", email = "niteshnew@mail.com"))
//            runOnUiThread {
//                Toast.makeText(this@MainActivity, "Record saved", Toast.LENGTH_LONG).show()
//            }
            /// the above statement can also be written as it uses applicationContext, so, it will not throw error
            Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
        }
    }
}