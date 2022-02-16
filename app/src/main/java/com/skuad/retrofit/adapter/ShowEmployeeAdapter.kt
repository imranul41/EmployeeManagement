package com.skuad.retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skuad.retrofit.databinding.ItemShowBinding
import com.skuad.retrofit.model.Employee
import vh.ShowEmployeeViewHolder

class ShowEmployeeAdapter(
    private val onItemClick: (Employee) -> Unit,
    private val onDeleteClick: (Employee) -> Unit
) :
    RecyclerView.Adapter<ShowEmployeeViewHolder>() {

    private val employeeList = mutableListOf<Employee>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowEmployeeViewHolder {
        val binding = ItemShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowEmployeeViewHolder(
            binding = binding,
            onDeleteClick = onDeleteClick,
            onItemClick = onItemClick
        )
    }

    override fun onBindViewHolder(holder: ShowEmployeeViewHolder, position: Int) {
        val employee = employeeList[position]
        holder.bindData(employee)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    fun submitList(employee: List<Employee>) {
        employeeList.clear()
        employeeList.addAll(employee)
        notifyDataSetChanged()
    }
}
