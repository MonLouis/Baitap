package com.example.myfoodapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider


class DessertFragment : Fragment() {

    private lateinit var VMDessert: ViewModelDessert

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        VMDessert = ViewModelProvider(requireActivity()).get(ViewModelDessert::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val foodViewModel: SelectedFoodViewModel by viewModels()
        val view = inflater.inflate(R.layout.fragment_dessert, container, false)
        val button3 = view.findViewById<Button>(R.id.button3)


        button3.setOnClickListener {
            foodViewModel.addSelectedFood("Main Dish")
            VMDessert = ViewModelProvider(this).get(ViewModelDessert::class.java)
        }
        return view
    }
}