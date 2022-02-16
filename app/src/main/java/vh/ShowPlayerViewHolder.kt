package vh

import androidx.recyclerview.widget.RecyclerView
import com.skuad.retrofit.databinding.ItemPlayerShowBinding
import com.skuad.retrofit.model.Player

class ShowPlayerViewHolder(private val binding: ItemPlayerShowBinding, private val onDeleteClick: (Player) -> Unit,
                           val onItemClick: (Player) -> Unit,) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(player: Player) {
        binding.tvName.text = "${player.firstName} ${player.lastName}"
        binding.tvAddress.text = player.address
        binding.tvDepartment.text = player.sportDepartment
        binding.tvQualification.text = player.qualification

        binding.root.setOnClickListener {
            onItemClick(player)
        }

        binding.btnDelete.setOnClickListener {
            onDeleteClick(player)
        }

    }
}
