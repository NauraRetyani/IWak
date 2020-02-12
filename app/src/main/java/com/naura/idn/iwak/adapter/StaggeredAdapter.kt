package com.naura.idn.iwak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naura.idn.iwak.R
import com.naura.idn.iwak.model.FishModel
import kotlinx.android.synthetic.main.item_row_home.view.*

class StaggeredAdapter(private val listStaggered: ArrayList<FishModel>) :
    RecyclerView.Adapter<StaggeredAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_home, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listStaggered.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listStaggered[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(fish: FishModel) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(fish.image)
                    .apply(RequestOptions().override(500))
                    .into(iv_popular)
                tv_name.text = fish.name
                tv_address.text = fish.address
            }
        }
    }
}