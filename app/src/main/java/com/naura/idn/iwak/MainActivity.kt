package com.naura.idn.iwak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val imageContentSlider = intArrayOf(
        R.drawable.image_1,
        R.drawable.image_2,
        R.drawable.image_3
    )

    val imageContentListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView?) {
            imageView?.setImageResource(imageContentSlider[position])
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val carouselView = is_main as CarouselView
        carouselView.setImageListener(imageContentListener)
        carouselView.setPageCount(imageContentSlider.count())

    }
}
