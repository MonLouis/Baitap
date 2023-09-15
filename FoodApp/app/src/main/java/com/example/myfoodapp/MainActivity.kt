package com.example.myfoodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var VMManDish:ViewModelMainDish
    private lateinit var VMSideDish:ViewModelSideDish
    private lateinit var VMDessert:ViewModelDessert
    private lateinit var VMBeverage:ViewModelBeverage

    private lateinit var viewPager2: ViewPager2
    private var currentToast: Toast? = null

    private fun showNewToast(message: String) {
        currentToast?.cancel()
        currentToast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        currentToast?.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2 = findViewById(R.id.viewpage2)
        setupViewPage()

        VMManDish = ViewModelProvider(this)[ViewModelMainDish::class.java]
        VMSideDish= ViewModelProvider(this)[ViewModelSideDish::class.java]
        VMDessert= ViewModelProvider(this)[ViewModelDessert::class.java]
        VMBeverage= ViewModelProvider(this)[ViewModelBeverage::class.java]
        
        val botNaviView = findViewById<BottomNavigationView>(R.id.bottomNavi)
        botNaviView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.maindish -> {
                    viewPager2.currentItem = 0
                    showNewToast("Main Dish")
                    true
                }

                R.id.sidedish -> {
                    viewPager2.currentItem=1
                    showNewToast("Side Dish")
                    true
                }

                R.id.dessert -> {
                    viewPager2.currentItem=2
                    showNewToast("Dessert")
                    true
                }

                R.id.beverage -> {
                    viewPager2.currentItem=3
                    showNewToast("Beverage")
                    true
                }

                else -> false
            }


        }
    }

    private fun setupViewPage() {
        val fragmentActivity = this as FragmentActivity
        val viewPagerAdapter = ViewPagerAdapter(fragmentActivity)
        val viewPager2:ViewPager2= findViewById(R.id.viewpage2)
        viewPager2.adapter=viewPagerAdapter
    }
}