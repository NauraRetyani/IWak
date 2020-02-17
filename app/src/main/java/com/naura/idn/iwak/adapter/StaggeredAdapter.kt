package com.naura.idn.iwak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naura.idn.iwak.R
import com.naura.idn.iwak.FishModel
import kotlinx.android.synthetic.main.item_row_home.view.*

class StaggeredAdapter(private val listener: (FishModel) -> Unit) :
    RecyclerView.Adapter<StaggeredAdapter.ViewHolder>() {

    private val listStaggered = ArrayList<FishModel>()

    fun setData(items: ArrayList<FishModel>) {
        listStaggered.clear()
        listStaggered.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_home, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listStaggered.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listStaggered[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(fish: FishModel, listener: (FishModel) -> Unit) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(fish.image)
                    .apply(RequestOptions().override(500))
                    .into(iv_fish)
//                iv_fish.setImageResource(fish.image)
                tv_name.text = fish.name
                tv_address.text = fish.address

                itemView.setOnClickListener { listener(fish) }

            }
        }
    }
}