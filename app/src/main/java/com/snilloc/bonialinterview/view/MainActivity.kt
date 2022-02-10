package com.snilloc.bonialinterview.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.google.android.material.chip.Chip
import com.snilloc.bonialinterview.data.cache.model.BrochureEntity
import com.snilloc.bonialinterview.databinding.ActivityMainBinding
import com.snilloc.bonialinterview.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var brochureAdapter: BrochureAdapter

    private lateinit var gridLayoutManager: GridLayoutManager

    private val viewModel: BonialViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getBrochures()
        observeAllBrochures()

        brochureAdapter = BrochureAdapter()

        setUpGridLayoutManager()

        binding.rv.apply {
            adapter = brochureAdapter
            layoutManager = gridLayoutManager
        }

        setUpChips()
    }

    private fun setUpGridLayoutManager() {
        /**
         * Use 3 columns in landscape layout
         * with 2 in Portrait layout
         */
        gridLayoutManager =
            if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                GridLayoutManager(this@MainActivity, 2)
            } else {
                GridLayoutManager(this@MainActivity, 3)
            }
    }

    private fun setUpChips() {
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = group.findViewById(checkedId)
            chip?.let {
                when (chip) {
                    binding.allBrochuresChip -> {
                        viewModel.getBrochures()
                        observeAllBrochures()
                    }
                    binding.filterDistanceChip -> {
                        viewModel.getFilteredBrochures()
                        observeFilteredBrochures()
                    }
                }
            }
        }
    }

    private fun observeAllBrochures() {
        viewModel.brochures.observe(this) { brochures ->

            brochures?.let {
                when (brochures.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE

                        brochures.data?.let {
                            //Update adapter list with brochure list
                            brochureAdapter.submitList(it)
                            binding.rv.smoothScrollToPosition(0)

                            setSpanSize(it)
                        }
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorTv.text = brochures.message
                    }
                }
            }
        }
    }

    private fun observeFilteredBrochures() {
        viewModel.filteredBrochures.observe(this) { filteredBrochures ->

            filteredBrochures?.let {
                when (filteredBrochures.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE

                        filteredBrochures.data?.let {
                            //Update adapter list with the filtered brochures list
                            brochureAdapter.submitList(it)
                            binding.rv.smoothScrollToPosition(0)

                            setSpanSize(it)
                        }
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorTv.text = filteredBrochures.message
                    }
                }
            }
        }
    }

    private fun setSpanSize(brochureList: List<BrochureEntity>) {
        gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                //Use the current span count to set the span size for brochurePremium brochures
                var spanSize = gridLayoutManager.spanCount

                if (brochureList.size >= position) {
                    val currentItem = brochureList[position]
                    /**
                     * spanSize for brochurePremium type is the current span count
                     * if spanCount is (Landscape),
                     * brochurePremium will span 3 columns
                     * while brochure will always span 1 column
                     */
                    spanSize = if (currentItem.contentType == "brochurePremium") {
                        spanSize
                    } else {
                        1
                    }
                }

                return spanSize
            }
        }
    }
}