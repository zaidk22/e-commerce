package com.example.e_commerce.activities

import android.provider.Settings.Global.getString
import com.example.e_commerce.R
import com.example.e_commerce.models.Products
import java.time.chrono.JapaneseEra.values

class AllProducts {

    fun allproducts():List<Products> {
        val movieList = listOf (
            Products("1","Iron man", "9", R.drawable.ironman, "Iron Man: Directed by Jon Favreau. With Robert Downey Jr., Terrence Howard, Jeff Bridges, Gwyneth Paltrow. After being held captive in an Afghan cave"),
            Products("2","Ant man", "9", R.drawable.antman,"Ant-Man is a 2015 American superhero film based on the Marvel Comics characters of the same name: Scott Lang and Hank Pym. Produced by Marvel Studios"),
            Products("3","Avengers", "9", R.drawable.avengers,"Marvel's The Avengers or simply The Avengers, is a 2012 American superhero film based on the Marvel Comics superhero team of the same name."),
            Products("4","Black Panther", "9", R.drawable.blackpanther,"Black Panther is a 2018 American superhero film based on the Marvel Comics character of the same name. Produced by Marvel Studios."),
            Products("5","Black Widow", "9", R.drawable.blackwidow,"Black Widow is a 2021 American superhero film based on Marvel Comics featuring the character of the same name. Produced by Marvel Studios."),
            Products("6","Captain Marvel", "9", R.drawable.captainmarvel,"Captain Marvel is a 2019 American superhero film based on Marvel Comics featuring the character Carol Danvers .")

            )
        return movieList


    }
}