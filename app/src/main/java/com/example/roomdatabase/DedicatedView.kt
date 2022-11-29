package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DedicatedView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dedicated_view)

        if(intent.hasExtra(MainActivity.DEDICATED_VIEW)){
            val employeeEntity: EmployeeEntity  = intent.getParcelableExtra<EmployeeEntity>(MainActivity.DEDICATED_VIEW) as EmployeeEntity

            findViewById<TextView>(R.id.textView).text = employeeEntity.id.toString();
        }
    }
}