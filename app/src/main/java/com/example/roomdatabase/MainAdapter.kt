package com.example.roomdatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.databinding.RecyclerviewItemBinding

/// the deleteListener is a listener to pass the employee which we want to delete to our UI
/// it is a lamda function here
class MainAdapter(
    private val employeeEntityList: List<EmployeeEntity>,
    private val deleteListener:(employeeEntity: EmployeeEntity) -> Unit
    ):
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val itemBinding: RecyclerviewItemBinding)
        : RecyclerView.ViewHolder(itemBinding.root){
            fun bindItem(employeeEntity: EmployeeEntity){
                itemBinding.id.text = employeeEntity.id.toString()
                itemBinding.name.text = employeeEntity.name
                itemBinding.email.text = employeeEntity.email

                itemBinding.delete.setOnClickListener {
                    deleteListener.invoke(employeeEntity)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            RecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        return holder.bindItem(
            employeeEntityList[position]
        )
    }

    override fun getItemCount(): Int {
        return employeeEntityList.size
    }


}