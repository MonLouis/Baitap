package com.example.myfoodapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider

class MainDishFragment : Fragment() {
    private lateinit var VMManDish: ViewModelMainDish

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        VMManDish = ViewModelProvider(requireActivity()).get(ViewModelMainDish::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val foodViewModel: SelectedFoodViewModel by viewModels()
        val view = inflater.inflate(R.layout.fragment_main_dish, container, false)
        val button1 = view.findViewById<Button>(R.id.button1)


        button1.setOnClickListener {
            foodViewModel.addSelectedFood("Main Dish")
            VMManDish = ViewModelProvider(this).get(ViewModelMainDish::class.java)


        }
        return view
    }
}
