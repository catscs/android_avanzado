package com.danielceinos.imgram.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.danielceinos.imgram.R
import com.danielceinos.imgram.ui.detail.DetailViewModel.*
import com.danielceinos.imgram.ui.home.HomeViewModel
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.direct
import org.kodein.di.instance

class DetailActivity : AppCompatActivity(), DIAware {

    override val di: DI by di()

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider( direct.instance()).get(DetailViewModel::class.java)
    }

    private val albumId: String by lazy { requireNotNull(intent.extras?.getString(IMAGES_ID_EXTRA))  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (savedInstanceState == null) {
            viewModel.getAlbumImages(albumId)
        }

        viewModel.event.observe(this, Observer(::updateUi))
    }

    private fun updateUi(event: Event) {
        when(event) {
            is Event.Content -> TODO()
        }
    }

    companion object {
        const val IMAGES_ID_EXTRA = "IMAGES_ID_EXTRA"
        @JvmStatic
        fun createIntent(context: Context): Intent = Intent(context, DetailActivity::class.java)
    }
}
