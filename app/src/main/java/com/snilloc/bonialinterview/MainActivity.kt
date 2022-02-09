package com.snilloc.bonialinterview

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.snilloc.bonialinterview.databinding.ActivityMainBinding
import com.snilloc.bonialinterview.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var brochureAdapter: BrochureAdapter

    private val viewModel: BonialViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        brochureAdapter = BrochureAdapter()

        viewModel.getBrochures()

        binding.rv.apply {
            adapter = brochureAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        viewModel.brochures.observe(this) { brochures ->
            val brochuresList = brochures.getContentIfNotHandled()

            brochuresList?.let {
                when (brochuresList.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE

                        brochuresList.data?.let {
                            brochureAdapter.submitList(it)
                        }
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorTv.text = brochuresList.message
                    }
                }
            }
        }
    }
}