package hr.ferit.magdalenabaric.myapplication_final

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AdoptListFragment : Fragment(), DogRecyclerViewListener {
    private val db = Firebase.firestore

    private lateinit var adapter: Adapter
    private lateinit var dogListRecyclerView: RecyclerView

    private lateinit var btnBack: Button

    private lateinit var dogAttributes: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getStringArrayList("DogAttributesList")?.let {
            dogAttributes = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_adopt_list, container, false)

        btnBack = view.findViewById(R.id.btnBack)
        dogListRecyclerView = view.findViewById(R.id.dogListRecyclerView)

        fetchDogsFromDatabaseAndSetRecyclerView()

        btnBack.setOnClickListener {
            goToAdoptFragment()
        }

        return view
    }

    private fun setRecyclerView(dogList : MutableList<Dog>){
        dogListRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = Adapter(dogList, this)
        dogListRecyclerView.adapter = adapter
    }

    private fun goToAdoptFragment(){
        val adoptFragment = AdoptFragment()
        val fragmentTransaction: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.firstPage, adoptFragment)
        fragmentTransaction?.commit()
    }

    private fun fetchDogsFromDatabaseAndSetRecyclerView(){
        var dogList : MutableList<Dog> = mutableListOf()
        val collectionRef = db.collection("projekt")
        collectionRef
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    dogList.add(createDog(document))
                }
                dogList = filterDogsByChosenAttributes(dogList)
                setRecyclerView(dogList)
            }
            .addOnFailureListener { exception ->
                Log.w("ERROR", "Error getting documents: ", exception)
            }
    }

    private fun createDog(document: QueryDocumentSnapshot): Dog {
        return Dog(
            document.id,
            document["name"].toString(),
            document["breed"].toString(),
            document["color"].toString(),
            document["height"].toString(),
            document["personality"].toString()
        )
    }

    private fun filterDogsByChosenAttributes(dogList: MutableList<Dog>) : MutableList<Dog>{
        val filteredDogList = mutableListOf<Dog>()
        for(dog in dogList){
            if((dog.color?.equals(dogAttributes[0]) == true) or
              (dog.breed?.equals(dogAttributes[1]) == true) or
              (dog.height?.equals(dogAttributes[2]) == true) or
              (dog.personality?.equals(dogAttributes[3]) == true)){
               filteredDogList.add(dog)
            }
        }
        return filteredDogList
    }

    override fun onItemClick(dogName: String) {
        goToAdoptedFragment(dogName)
    }

    private fun goToAdoptedFragment(dogName: String){
        val adoptedFragment = AdoptedFragment()
        val bundle = Bundle()
        bundle.putString("dogName", dogName)
        adoptedFragment.arguments = bundle
        val fragmentTransaction: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.firstPage, adoptedFragment)
        fragmentTransaction?.commit()
    }

}