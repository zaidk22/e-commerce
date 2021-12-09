package com.example.e_commerce

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.activities.DetailedActivity
import com.example.e_commerce.models.Products
import kotlinx.android.synthetic.main.row_data.view.*

class ProductsAdapter(var productsList: List<Products>):
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return productsList.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val view = layoutInflater.inflate(R.layout.row_data, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener {
            val intent = Intent(parent.context, DetailedActivity::class.java)
            intent.putExtra("title",productsList[holder.adapterPosition].name)
            intent.putExtra("ProductImage",productsList[holder.adapterPosition].image)
            intent.putExtra("Description",productsList[holder.adapterPosition].description)
            parent.context.startActivity(intent)
        }
        return holder
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(products: Products) {
            itemView.fname.text = products.name
            itemView.price.text = products.products.toString() +"" + "Rs"
            itemView.fImage.setImageResource(products.image)

        }



    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val products = productsList[position]
        holder.bind(products)

    }


}