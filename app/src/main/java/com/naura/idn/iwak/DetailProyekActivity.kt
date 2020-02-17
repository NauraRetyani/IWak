package com.naura.idn.iwak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_proyek.*

class DetailProyekActivity : AppCompatActivity() {

    companion object {
        const val KEY_DETAIL_PROJECT = "key_detail_project"
    }

    private var fish: FishModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_proyek)

//        fish = intent.getParcelableExtra(KEY_DETAIL_PROJECT)

        title_detail.text = fish?.name
        location_detail.text = fish?.address
        Glide.with(this).load(fish?.image)
            .apply(RequestOptions().override(800))
            .into(img_detail)
    }
}
