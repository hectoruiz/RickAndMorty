package hector.ruiz.rickandmorty.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hector.ruiz.domain.entities.list.Results
import hector.ruiz.rickandmorty.R
import hector.ruiz.rickandmorty.databinding.ResidentItemBinding

class ResidentAdapter : RecyclerView.Adapter<ResidentAdapter.ResidentViewHolder>() {

    private var residents: MutableList<Results> = mutableListOf()

    fun addResident(resident: Results) {
        this.residents.add(resident)
    }

    inner class ResidentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ResidentItemBinding.bind(view)

        fun bind(resident: Results) {
            Picasso.get()
                .load(resident.image)
                .placeholder(R.drawable.user_placeholder)
                .error(R.drawable.user_placeholder_error)
                .into(binding.residentImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResidentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ResidentViewHolder(
            layoutInflater.inflate(
                R.layout.resident_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ResidentViewHolder, position: Int) {
        val resident = residents[position]
        holder.bind(resident)
    }

    override fun getItemCount(): Int = residents.size
}
