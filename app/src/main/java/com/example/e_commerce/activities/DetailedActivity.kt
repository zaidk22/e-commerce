package com.example.e_commerce.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.e_commerce.R
import kotlinx.android.synthetic.main.activity_detailed.*

class DetailedActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        val title = intent.getStringExtra("title")
            //val Picture = intent.putExtra("ProductImage")
        //val movieList = AllProducts().allproducts()
        val bundle:Bundle?=intent.extras
        val picture = bundle?.getInt("ProductImage")
        if (picture != null) {
            detailedimageView.setImageResource(picture)
        }


        detailedTextView.text = title



        button_availability.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("$title is available")
                .setPositiveButton("OK"
                ) { dialog, which -> }
                .create()
                .show()
        }
        button_Buy.setOnClickListener {
            val intent = Intent(this, FinalActivity::class.java)
            startActivity(intent)
            finish()
        }



    }
}

