package hr.ferit.magdalenabaric.myapplication_final

import android.content.ContentValues
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

class GiftFragment : Fragment() {
    val db = Firebase.firestore

    private lateinit var nameEditText: EditText
    private lateinit var colorEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var breedEditText: EditText
    private lateinit var personalityEditText: EditText

    private lateinit var btnBack: Button
    private lateinit var btnNext: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gift, container, false)

        nameEditText = view.findViewById(R.id.EditTextName)
        colorEditText = view.findViewById(R.id.EditTextColor)
        heightEditText = view.findViewById(R.id.EditTextHeight)
        breedEditText = view.findViewById(R.id.EditTextBreed)
        personalityEditText = view.findViewById(R.id.EditTextPersonality)

        btnNext = view.findViewById(R.id.btnNext)
        btnBack = view.findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            goToFirstFragment()
        }

        btnNext.setOnClickListener {
            insertDogInDatabase()
            goToGiftedFragment()
        }

        return view
    }

    private fun insertDogInDatabase(){
        val dog = Dog("",
            nameEditText.text.toString(),
            breedEditText.text.toString(),
            colorEditText.text.toString(),
            heightEditText.text.toString(),
            personalityEditText.text.toString()
        )

        db.collection("projekt")
            .add(dog)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }
    }

    private fun goToFirstFragment(){
        val firstFragment = FirstFragment()
        val fragmentTransaction: FragmentTransaction?=activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.firstPage, firstFragment)
        fragmentTransaction?.commit()
    }

    private fun goToGiftedFragment(){
        val giftedFragment = GiftedFragment()
        val fragmentTransaction: FragmentTransaction?=activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.firstPage, giftedFragment)
        fragmentTransaction?.commit()
    }
}