package hr.ferit.magdalenabaric.myapplication_final

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction

class GiftedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gifted, container, false)

        val btnStart=view.findViewById<Button>(R.id.btnStart)

        btnStart.setOnClickListener {
            goToFirstFragment()
        }

        return view
    }

    private fun goToFirstFragment(){
        val sureFragment = FirstFragment()
        val fragmentTransaction: FragmentTransaction?=activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.firstPage, sureFragment)
        fragmentTransaction?.commit()
    }
}