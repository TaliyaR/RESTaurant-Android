package com.example.restaurant.ui.current.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.R
import com.example.restaurant.entities.Position
import com.example.restaurant.entities.StatusType
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_order.view.*

class OrderViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(position: Position) {
        with(itemView) {
            dish_title.text = position.dish.name
            dish_cost.text = position.dish.cost.toString()
            iv_status.setImageResource(
                when(position.status){
                    StatusType.COOKED.name -> R.drawable.ic_cooked
                    StatusType.CREATED.name -> R.drawable.ic_create
                    StatusType.DELIVERED.name -> R.drawable.ic_ready
                    else -> R.drawable.ic_cooked
                }
            )

        }
    }

    companion object {
        fun create(parent: ViewGroup) = OrderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        )
    }
}