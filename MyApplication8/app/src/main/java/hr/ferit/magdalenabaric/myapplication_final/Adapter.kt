package hr.ferit.magdalenabaric.myapplication_final

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val dogs : MutableList<Dog>,
              private val listener: DogRecyclerViewListener):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DogViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recycler_view_item, parent,
                    false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is DogViewHolder -> {
                val currentDog = dogs[position]
                holder.bind(currentDog)
                holder.itemView.setOnClickListener {
                    listener.onItemClick(currentDog.name!!)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return this.dogs.size
    }

    class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dogName : TextView= itemView.findViewById(R.id.dogName)
        private val dogBreed : TextView= itemView.findViewById(R.id.dogBreed)
        private val dogColor : TextView= itemView.findViewById(R.id.dogColor)
        private val dogHeight : TextView= itemView.findViewById(R.id.dogHeight)
        private val dogPersonality : TextView= itemView.findViewById(R.id.dogPersonality)

        fun bind(dog: Dog){
                dogName.text = dog.name
                dogBreed.text = dog.breed
                dogColor.text = dog.color
                dogHeight.text = dog.height
                dogPersonality.text = dog.personality
        }
    }
}