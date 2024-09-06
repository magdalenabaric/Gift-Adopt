package hr.ferit.magdalenabaric.myapplication_final

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AdoptFragment : Fragment() {
    private lateinit var colorEditText: EditText
    private lateinit var breedEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var personalityEditText: EditText
    private lateinit var btnNext: Button
    private lateinit var btnBack: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_adopt, container, false)

        colorEditText = view.findViewById(R.id.colorEditText)
        breedEditText = view.findViewById(R.id.breedEditText)
        heightEditText = view.findViewById(R.id.heightEditText)
        personalityEditText = view.findViewById(R.id.personalityEditText)
        btnNext = view.findViewById(R.id.btnNext)
        btnBack = view.findViewById(R.id.btnBack)

        btnNext.setOnClickListener {
            var dogAttributesList = createDogAttributesList()
            goToAdoptListFragment(dogAttributesList)
        }
        btnBack.setOnClickListener {
            goToAdoptFragment()
        }

        return view
    }

    private fun goToAdoptListFragment(dogAttributes: ArrayList<String>){
        val adoptListFragment = AdoptListFragment()
        val bundle = Bundle()
        bundle.putStringArrayList("DogAttributesList", dogAttributes)
        adoptListFragment.arguments = bundle
        val fragmentTransaction: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.firstPage, adoptListFragment)
        fragmentTransaction?.commit()
    }

    private fun goToAdoptFragment(){
        val adoptFragment = FirstFragment()
        val fragmentTransaction: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.firstPage, adoptFragment)
        fragmentTransaction?.commit()
    }

    private fun createDogAttributesList(): ArrayList<String> {
        return arrayListOf(
            colorEditText.text.toString(),
            breedEditText.text.toString(),
            heightEditText.text.toString(),
            personalityEditText.text.toString()
        )
    }

}

