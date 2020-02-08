package com.naura.idn.iwak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val fishList = ArrayList<FishModel>()

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
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Post", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val carouselView = is_main as CarouselView
        carouselView.setImageListener(imageContentListener)
        carouselView.setPageCount(imageContentSlider.count())

        rv_main.setHasFixedSize(true)
        fishList.addAll(getListFood())
        showRecyclerList()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showRecyclerList() {
        rv_main.layoutManager = GridLayoutManager(this, 2)

        val listFoodAdapter = RecyclerAdapter(fishList)
        rv_main.adapter = listFoodAdapter
    }

    private fun getListFood(): ArrayList<FishModel> {
        val dataName = resources.getStringArray(R.array.title)
        val dataAddress = resources.getStringArray(R.array.address)
        val dataImage = resources.obtainTypedArray(R.array.image)

        val listFish = ArrayList<FishModel>()
        for (position in dataName.indices) {
            val fish = FishModel(
                dataName[position],
                dataAddress[position],
                dataImage.getResourceId(position, -1)
            )
            listFish.add(fish)
        }
        return listFish
    }
}
