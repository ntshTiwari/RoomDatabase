package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.roomdatabase.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/// when we use room, then we need a database and a dao
/// database is where our data gets stored and dao helps us to do that
/// the database and the dao should be linked as well
class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        /// we need to create a dao here and use it throughout our app, the singleton will help us with that
        val employeeDao = (application as EmployeeApp).db.employeeDao()
        /// the database and the dao should be connected, what we did in our EmployeeDatabase class

        /// we can also directly use this instead of the above way, the above just makes sure that our db can get instatntiated at the start of the app
//        val employeeDao = EmployeeDatabase.getInstance(this).employeeDao()

        binding!!.insertButton.setOnClickListener {
            addRecord(employeeDao)
        }

        var allEmployees = employeeDao.fetchAllEmployees()
        renderAllEmployees(allEmployees)

    }

    private fun renderAllEmployees(allEmployees: Flow<List<EmployeeEntity>>) {
        lifecycleScope.launch{
            allEmployees.collect(){
                if(it.isNotEmpty()){
                    binding!!.noEmployee.visibility = View.GONE
                    binding!!.listEmployees.visibility = View.VISIBLE

                    binding!!.listEmployees.adapter = MainAdapter(
                        it,
                        /// we pass the function that will delete the employeeEntity
                        {
                            employeeEntity ->
                                deleteEmployee(employeeEntity)
                        },
                        this@MainActivity,
                        DEDICATED_VIEW,
                    )
                } else {
                    binding!!.listEmployees.visibility = View.GONE
                    binding!!.noEmployee.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun deleteEmployee(employeeEntity: EmployeeEntity) {
        lifecycleScope.launch {
            /// we can use the already created dao or get the same from the singleton
            (application as EmployeeApp).db.employeeDao().delete(employeeEntity);
            Toast.makeText(applicationContext, "Deleted ${employeeEntity.id}", Toast.LENGTH_LONG).show()
        }
    }

    private fun addRecord(employeeDao: EmployeeDao){
        lifecycleScope.launch{
            employeeDao.insert(EmployeeEntity(name = "Nitesh new neww neww", email = "niteshnew@mail.com"))
//            runOnUiThread {
//                Toast.makeText(this@MainActivity, "Record saved", Toast.LENGTH_LONG).show()
//            }
            /// the above statement can also be written as it uses applicationContext, so, it will not throw error
            Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    /// companion objects can be accessed like static methods from outside classes as well
    companion object {
        var DEDICATED_VIEW = "dedicated_view"
    }
}