package com.example.e_commerce.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.e_commerce.R
import com.example.e_commerce.models.CartItem
import com.example.e_commerce.models.Products
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_detailed.*

class CartActivity : AppCompatActivity(){
    private lateinit var database : DatabaseReference
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        val bundle:Bundle?=intent.extras
        val picture = bundle?.getInt("ProductImage")
        if (picture != null) {
            product_image.setImageResource(picture)
        }
       /* addtocart.setOnClickListener {
         
            database = FirebaseDatabase.getInstance().getReference("CartItem")
            val cart = CartItem(product = Products("1","asa","asasas",R.drawable.captainmarvel), quantity = 0)
            database.child(cart.toString()).setValue(cart).addOnSuccessListener {  }


        }*/

           /* val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val userName = binding.userName.text.toString()
            val age = binding.age.text.toString()

            database= FirebaseDatabase.getInstance("https://peoplecounter-9bb43-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Users")
            val user=Users(firstName, lastName, age, userName)
            database.child(userName).setValue(user).addOnSuccessListener {
                binding.firstName.text.clear()
                binding.lastName.text.clear()
                binding.userName.text.clear()
                binding.age.text.clear()
                Toast.makeText(this,"Registration is successfull", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"registration failure", Toast.LENGTH_SHORT).show()

            }

        }*/
    }
}
