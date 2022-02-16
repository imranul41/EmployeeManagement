package vh

import androidx.recyclerview.widget.RecyclerView
import com.skuad.retrofit.databinding.ItemShowBinding
import com.skuad.retrofit.model.Employee

class ShowEmployeeViewHolder(private val binding: ItemShowBinding,private val onDeleteClick: (Employee) -> Unit,
                             val onItemClick: (Employee) -> Unit,) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindData(employee: Employee) {
        binding.tvName.text = "${employee.firstName} ${employee.lastName}"
        binding.tvAddress.text = employee.address
        binding.tvDesignation.text = employee.designation
        binding.tvDepartment.text = employee.department

        binding.root.setOnClickListener {
            onItemClick(employee)
        }

        binding.btnDelete.setOnClickListener {
            onDeleteClick(employee)
        }
    }
}
