package com.example.eatKuy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.eatKuy.R
import com.example.eatKuy.activity.DetailFoodActivity
import com.example.eatKuy.model.Food
import com.example.eatKuy.model.FoodList
import kotlinx.android.synthetic.main.activity_main_menu.view.*
import kotlinx.android.synthetic.main.item_menu_name.view.*

class ListFoodAdapter(private val listFood: FoodList) :
    RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int):
            ListViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.item_menu_name,
                viewGroup, false
            )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val food = listFood

//        holder.tvFood.text = food.listOfFood[position].name
        holder.bind(listFood, position)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailFoodActivity::class.java)

            intent.putExtra("name", food.listOfFood[position].name)
            intent.putExtra("ingredient", food.listOfFood[position].ingredient)
            intent.putExtra("image", food.listOfFood[position].image)
            intent.putExtra("pos", position)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = 10

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        var tvFood: TextView = itemView.findViewById(R.id.menu_name)
        fun bind(food: FoodList, pos: Int) {
            with(itemView) {
//                Glide.with(itemView.context)
//                    .load(food.listOfFood[pos].image)
//                    .into(img_main_menu)
                menu_name.text = food.listOfFood[pos].name
//                txt.text = food.listOfFood[pos].ingredient
            }
        }
    }

}