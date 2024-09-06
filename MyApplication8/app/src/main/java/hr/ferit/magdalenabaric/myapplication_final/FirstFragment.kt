package hr.ferit.magdalenabaric.myapplication_final

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction

class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val giftButton=view.findViewById<Button>(R.id.giftButton)
        val adoptButton=view.findViewById<Button>(R.id.adoptButton)

        adoptButton.setOnClickListener {
            goToAdoptFragment()
        }
        giftButton.setOnClickListener {
            goToGiftFragment()
        }
        return view
    }

    private fun goToAdoptFragment(){
        val adoptFragment = AdoptFragment()
        val fragmentTransaction: FragmentTransaction?=activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.firstPage, adoptFragment)
        fragmentTransaction?.commit()
    }

    private fun goToGiftFragment(){
        val sureFragment = GiftFragment()
        val fragmentTransaction: FragmentTransaction?=activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.firstPage, sureFragment)
        fragmentTransaction?.commit()
    }
}