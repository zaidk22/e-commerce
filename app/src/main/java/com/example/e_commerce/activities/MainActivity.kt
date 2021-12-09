package com.example.e_commerce.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_commerce.ProductsAdapter
import com.example.e_commerce.R
import com.example.e_commerce.models.Constants
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.row_data.*


class MainActivity : AppCompatActivity() {
    lateinit var toggle : ActionBarDrawerToggle
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //supportActionBar?.hide()
        imageCartView.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)

        }

        val sharedPreferences = getSharedPreferences(Constants.E_COMMERCE_PREFERENCES,Context.MODE_PRIVATE)
        val username = sharedPreferences.getString(Constants.Logged_In_UserName,"")
        tv_title.text = "Hello $username"
        user_name_drawer?.text = "$username"

        val movieList = AllProducts().allproducts()


        val recyclerAdapter = ProductsAdapter(movieList)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        recyclerView.apply {
            adapter = recyclerAdapter

           addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout3)
        val navView : NavigationView = findViewById(R.id.nav_view2)
        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home->Toast.makeText(this,"clicked home",Toast.LENGTH_SHORT).show()


                R.id.nav_message-> Toast.makeText(this,"clicked message", Toast.LENGTH_SHORT).show()
                R.id.nav_settings-> Toast.makeText(this,"clicked settings", Toast.LENGTH_SHORT).show()

            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    }



