package com.naura.idn.iwak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.naura.idn.iwak.adapter.StaggeredAdapter
import com.naura.idn.iwak.model.FishModel
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //    private val fishList = ArrayList<FishModel>()
    private lateinit var detailAdapter: StaggeredAdapter


    val imageContentSlider = intArrayOf(
        R.drawable.mas,
        R.drawable.cakalang,
        R.drawable.udang
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

        showRecyclerGrid()

        btn_profile.setOnClickListener {
            val list = Intent(this, ProfileActivity::class.java)
            startActivity(list)
        }

    }
    private fun getListFish(): ArrayList<FishModel> {
        val dataName = resources.getStringArray(R.array.name)
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

    private fun showRecyclerGrid() {
        detailAdapter = StaggeredAdapter { showSelected(it) }
        detailAdapter.notifyDataSetChanged()
        detailAdapter.setData(getListFish())
        rv_main.setHasFixedSize(true)
        val layoutManagerStaggered = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
        rv_main.layoutManager = layoutManagerStaggered
        rv_main.adapter = detailAdapter
    }

    private fun showSelected(it: FishModel) {
        val detail = Intent(this, DetailProyekActivity::class.java)
        detail.putExtra(DetailProyekActivity.KEY_DETAIL_PROJECT, it)
        startActivity(detail)
    }
}
