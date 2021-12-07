package com.example.e_commerce.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_commerce.ProductsAdapter
import com.example.e_commerce.R
import com.example.e_commerce.models.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_data.*


class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageCartView.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)

        }
        supportActionBar?.hide()
        val sharedPreferences = getSharedPreferences(Constants.E_COMMERCE_PREFERENCES,Context.MODE_PRIVATE)
        val username = sharedPreferences.getString(Constants.Logged_In_UserName,"")
        tv_title.text = "Hello $username"

        val movieList = AllProducts().allproducts()


        val recyclerAdapter = ProductsAdapter(movieList)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        recyclerView.apply {
            adapter = recyclerAdapter

           addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }




    }
}
