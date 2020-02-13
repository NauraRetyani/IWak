package com.naura.idn.iwak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_proyek.*

class DetailProyekActivity : AppCompatActivity() {
    companion object{
        const val KEY_FISH = "key_fish"
    }

    private var model : FishModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_proyek)
        model = intent.getParcelableExtra(KEY_FISH)

        tv_detail_proyek.setText(model?.name)
        tv_detail_address.setText(model?.address)
        tv_detail_desc.setText(model?.desc)
        iv_detail_proyek.setImageResource(model?.image!!)
//        Glide.with(this).load(model?.image).apply(RequestOptions().override(500)).into(iv_detail_proyek)
    }
}
