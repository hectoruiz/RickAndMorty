package hector.ruiz.rickandmorty.ui

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hector.ruiz.domain.entities.list.Results
import hector.ruiz.rickandmorty.R
import hector.ruiz.rickandmorty.databinding.CharacterItemBinding

class CharacterAdapter(private val characters: List<Results?>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    var onItemClick: ((String?) -> Unit)? = null

    inner class CharacterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CharacterItemBinding.bind(view)

        init {
            binding.characterLocation.setOnClickListener {
                onItemClick?.invoke(characters[adapterPosition]?.location?.url)
            }
        }

        fun bind(results: Results?) {
            with(binding) {
                Picasso.get()
                    .load(results?.image)
                    .placeholder(R.drawable.user_placeholder)
                    .error(R.drawable.user_placeholder_error)
                    .into(this.characterImage)
                this.characterStatus.visibility = getVisibilityStatus(results?.status)
                this.characterName.text = results?.name
                this.characterSpecie.text = results?.species
                this.characterGender.setImageResource(getIconGender(results?.gender))
                this.characterEpisodes.text =
                    view.context.getString(
                        R.string.character_episodes,
                        getQuantityEpisodes(results?.episode?.size)
                    )
            }
        }

        private fun getVisibilityStatus(status: String?): Int {
            return status?.let {
                if (it.equals(STATUS_DEAD, true)) VISIBLE else GONE
            } ?: GONE
        }

        private fun getIconGender(gender: String?): Int {
            return when (gender) {
                GENDER_FEMALE -> R.drawable.ic_woman
                GENDER_MALE -> R.drawable.ic_man
                else -> R.drawable.ic_neutral
            }
        }

        private fun getQuantityEpisodes(episodeNumber: Int?): String? {
            val quantityEpisodes = episodeNumber ?: 0
            return view.context?.resources?.getQuantityString(
                R.plurals.number_episodes,
                quantityEpisodes, quantityEpisodes
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(
            layoutInflater.inflate(
                R.layout.character_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int = characters.size

    companion object {
        const val STATUS_DEAD = "Dead"
        const val GENDER_MALE = "Male"
        const val GENDER_FEMALE = "Female"
    }
}
