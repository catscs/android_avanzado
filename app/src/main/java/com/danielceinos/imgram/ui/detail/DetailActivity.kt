package com.danielceinos.imgram.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.danielceinos.imgram.databinding.ActivityDetailBinding
import com.danielceinos.imgram.ui.detail.DetailViewModel.Event
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.direct
import org.kodein.di.instance

class DetailActivity : AppCompatActivity(), DIAware {

    override val di: DI by di()

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this, direct.instance()).get(DetailViewModel::class.java)
    }

    private val albumId: String by lazy { requireNotNull(intent.extras?.getString(IMAGES_ID_EXTRA))  }
    private lateinit var adapter: DetailAdapter
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater).also { setContentView(it.root) }
        if (savedInstanceState == null) {
            viewModel.getAlbumImages(albumId)
        }


        adapter = DetailAdapter()
        binding.imageRecyclerView.adapter = adapter

        viewModel.event.observe(this, Observer(::updateUi))
    }

    private fun updateUi(event: Event) {
        binding.detailProgressBar.visibility = if (event is Event.Loading) View.VISIBLE else View.GONE

        when(event) {
            is Event.Content -> adapter.submitList(event.image)
        }
    }

    companion object {
        const val IMAGES_ID_EXTRA = "IMAGES_ID_EXTRA"
        @JvmStatic
        fun createIntent(context: Context): Intent = Intent(context, DetailActivity::class.java)
    }
}
