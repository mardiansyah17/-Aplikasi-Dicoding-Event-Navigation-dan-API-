package com.example.dicodingevent.ui.detail_event

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.example.dicodingevent.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailEventViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding

    val args: DetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val id = args.idEvent
        viewModel.getDetailEvent(id)

        setContentView(binding.root)

        viewModel.detailEvent.observe(this) {
            binding.tvOwnerDetailEvent.text = "Diselenggarakan oleh ${it.event?.ownerName}"
            binding.tvTitleDetailEvent.text = it.event?.name
            binding.tvQuotaDetailEvent.text = "Kuota: ${it.event?.quota}"
            Glide.with(this)
                .load(it.event?.mediaCover)
                .into(binding.ivCoverDetailEvent)
        }

        viewModel.loading.observe(this) {
            if (it) {
                binding.progressBarDetailEvent.visibility = View.VISIBLE
                binding.scrollView2.visibility = View.GONE
            } else {
                binding.progressBarDetailEvent.visibility = View.GONE
                binding.scrollView2.visibility = View.VISIBLE
            }
        }
    }
}
