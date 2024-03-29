package com.naura.idn.iwak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.hide()
        showSelected()

    }
    private fun showSelected() {
        iv_back_to_home.setOnClickListener {
            val list = Intent(this, MainActivity::class.java)
            startActivity(list)
        }
    }
}
