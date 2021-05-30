package com.danielceinos.imgram.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danielceinos.imgram.R

class DetailActivity : AppCompatActivity() {

    private val albumId: String by lazy { requireNotNull(intent.extras?.getString(IMAGES_ID_EXTRA))  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)



    }

    companion object {
        const val IMAGES_ID_EXTRA = "IMAGES_ID_EXTRA"
        @JvmStatic
        fun createIntent(context: Context): Intent = Intent(context, DetailActivity::class.java)
    }
}
