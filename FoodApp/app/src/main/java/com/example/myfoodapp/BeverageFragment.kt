package com.example.myfoodapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider


class BeverageFragment : Fragment(){

     private lateinit var VMBeverage:ViewModelBeverage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        VMBeverage = ViewModelProvider(requireActivity()).get(ViewModelBeverage::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val foodViewModel: SelectedFoodViewModel by viewModels()
        val view = inflater.inflate(R.layout.fragment_beverage, container, false)
        val button4 = view.findViewById<Button>(R.id.button4)


        button4.setOnClickListener {
            foodViewModel.addSelectedFood("Main Dish")
            VMBeverage = ViewModelProvider(this).get(VMBeverage::class.java)
        }
        return view
    }
}