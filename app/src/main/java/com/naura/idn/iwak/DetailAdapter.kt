package com.naura.idn.iwak

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_home.view.*


class DetailAdapter(private val listener: (FishModel) -> Unit) :
    RecyclerView.Adapter<DetailAdapter.PopularViewHolder>() {
    private val listFishs = ArrayList<FishModel>()

    fun setData(items: ArrayList<FishModel>) {
        listFishs.clear()
        listFishs.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_home, parent, false)
        return PopularViewHolder(view)
    }

    override fun getItemCount(): Int = listFishs.size

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(listFishs[position], listener)
    }

    class PopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(fishs: FishModel, listener: (FishModel) -> Unit) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(fishs.image)
                    .apply(RequestOptions().override(300))
                    .into(iv_popular)
                tv_name.text = fishs.name
                tv_address.text = fishs.address

                itemView.setOnClickListener { listener(fishs) }
            }
        }
    }
}