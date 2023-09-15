package com.example.myfoodapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider


class SideDishFragment : Fragment() {

    private lateinit var VMSideDish: ViewModelSideDish

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        VMSideDish = ViewModelProvider(requireActivity()).get(ViewModelSideDish::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val foodViewModel: SelectedFoodViewModel by viewModels()
        val view = inflater.inflate(R.layout.fragment_side_dish, container, false)
        val button2 = view.findViewById<Button>(R.id.button2)


        button2.setOnClickListener {
            foodViewModel.addSelectedFood("Side Dish")
            VMSideDish = ViewModelProvider(this).get(ViewModelSideDish::class.java)
        }
        return view
    }
}