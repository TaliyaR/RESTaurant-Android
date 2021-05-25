package com.example.restaurant.ui.current.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.entities.Position

class OrderAdapter(
    private var data: List<Position> = emptyList()
) : RecyclerView.Adapter<OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder =
        OrderViewHolder.create(parent)

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) =
        holder.bind(data[position])

    fun setList(list: List<Position>) {
        data = list
        this.notifyDataSetChanged()
    }
}