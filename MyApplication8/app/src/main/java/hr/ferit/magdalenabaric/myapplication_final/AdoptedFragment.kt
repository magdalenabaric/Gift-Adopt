package hr.ferit.magdalenabaric.myapplication_final

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction

class AdoptedFragment : Fragment() {

    private lateinit var btnStart : Button
    private lateinit var dogNameTextView : TextView
    private lateinit var dogName :  String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dogName = arguments?.get("dogName") as String
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_adopted, container, false)

        btnStart = view.findViewById(R.id.btnBack2)
        dogNameTextView = view.findViewById(R.id.dogNameTextView)
        dogNameTextView.text = dogName

        btnStart.setOnClickListener { goToFirstFragment() }
        return view
    }

    private fun goToFirstFragment(){
        val firstFragment = FirstFragment()
        val fragmentTransaction: FragmentTransaction?=activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.firstPage, firstFragment)
        fragmentTransaction?.commit()
    }
}