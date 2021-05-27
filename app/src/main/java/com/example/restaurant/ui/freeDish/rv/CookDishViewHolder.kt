package com.example.restaurant.ui.freeDish.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.R
import com.example.restaurant.entities.Position
import com.example.restaurant.entities.StatusType
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_cook_task.view.*

class CookDishViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(position: Position) {
        with(itemView) {
            dish_title.text = position.dish.name
            dish_type.text = position.dish.dishType
            dish_description.text = position.dish.description
            dish_ingredients.text = position.dish.composition
            dish_weight.text = position.dish.weight.toString()
            btn_status.setImageResource(
                when (position.status) {
                    StatusType.CREATED.name -> R.drawable.ic_add
                    else -> R.drawable.ic_check_mark
                }
            )
        }
    }

    companion object {
        fun create(parent: ViewGroup) = CookDishViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cook_task, parent, false)
        )
    }
}