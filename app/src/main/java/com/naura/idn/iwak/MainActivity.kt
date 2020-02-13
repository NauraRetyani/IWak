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
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val fishList = ArrayList<FishModel>()
    private lateinit var detailProyekAdapter : DetailAdapter

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

        rv_main.setHasFixedSize(true)
        fishList.addAll(getListFish())
        showRecyclerGrid()

        btn_profile.setOnClickListener {
            val list = Intent(this, ProfileActivity::class.java)
            startActivity(list)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showRecyclerGrid() {
        detailProyekAdapter = DetailAdapter { showSelected(it) }
        detailProyekAdapter.notifyDataSetChanged()
        detailProyekAdapter.setData(getListFish())
        val layoutManagerStaggered = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
        rv_main.layoutManager = layoutManagerStaggered
        rv_main.adapter = StaggeredAdapter(fishList)
        rv_main.adapter = detailProyekAdapter
        rv_main.setHasFixedSize(true)
    }

    private fun showSelected(it: FishModel) {
        val page = Intent(this, DetailProyekActivity::class.java)
        page.putExtra(DetailProyekActivity.KEY_FISH, it)
        startActivity(page)
    }

    private fun getListFish(): ArrayList<FishModel> {
        val dataName = resources.getStringArray(R.array.title)
        val dataAddress = resources.getStringArray(R.array.address)
        val dataDesc = resources.getStringArray(R.array.desc)
        val dataImage = resources.obtainTypedArray(R.array.image)

        val listFish = ArrayList<FishModel>()
        for (position in dataName.indices) {
            val fish = FishModel(
                dataName[position],
                dataAddress[position],
                dataDesc[position],
                dataImage.getResourceId(position, -1)
            )
            listFish.add(fish)
        }
        return listFish
    }
}
