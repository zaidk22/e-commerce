package com.example.e_commerce.activities

import com.example.e_commerce.R
import com.example.e_commerce.models.Products

class AllProducts {
    fun allproducts():List<Products> {
        val movieList = listOf (
            Products("1","Iron man", "9", R.drawable.ironman),
            Products("2","Ant man", "9", R.drawable.antman),
            Products("3","Avengers", "9", R.drawable.avengers),
            Products("4","Black Panther", "9", R.drawable.blackpanther),
            Products("5","Black Widow", "9", R.drawable.blackwidow),
            Products("6","Captain Marvel", "9", R.drawable.captainmarvel),

            )
        return movieList
    }
}