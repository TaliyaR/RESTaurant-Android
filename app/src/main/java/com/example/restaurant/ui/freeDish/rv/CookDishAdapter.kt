package com.example.restaurant.ui.freeDish.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.entities.Position

class CookDishAdapter(
    private var data: List<Position> = emptyList(),
    private val clickLambda: (Position) -> Unit
) : RecyclerView.Adapter<CookDishViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CookDishViewHolder =
        CookDishViewHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CookDishViewHolder, position: Int) =
        holder.bind(data[position])

    fun setList(list: List<Position>) {
        data = list
        this.notifyDataSetChanged()
    }
}