package com.example.roomdatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.databinding.RecyclerviewItemBinding

class MainAdapter(private val employeeEntityList: List<EmployeeEntity>):
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val itemBinding: RecyclerviewItemBinding)
        : RecyclerView.ViewHolder(itemBinding.root){
            fun bindItem(employeeEntity: EmployeeEntity){
                itemBinding.id.text = employeeEntity.id.toString()
                itemBinding.name.text = employeeEntity.name
                itemBinding.email.text = employeeEntity.email
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