package com.snilloc.bonialinterview

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.snilloc.bonialinterview.data.FakeBonialRepositoryImpl
import com.snilloc.bonialinterview.domain.model.BrochureData
import com.snilloc.bonialinterview.util.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BonialViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var bonialViewModel: BonialViewModel

    @Before
    fun setUp() {
        bonialViewModel = BonialViewModel(FakeBonialRepositoryImpl())

        bonialViewModel.getBrochures()
    }

    @Test
    fun verify_get_brochures_is_a_success() {
        val brochureList = bonialViewModel.brochures.getOrAwaitValueTest()

        assertThat(brochureList.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `verify brochuresList contains brochure`() {
        val brochure = BrochureData(
            id = 1,
            contentType = "brochurePremium",
            brochureImage = "https://content-media.bonial.biz/d753a0a0-7fc7-4b92-b34b-5146d1b692ad/preview.jpg",
            retailerName = "Retailer A",
            distance = 1.0F
        )

        val brochureList = bonialViewModel.brochures.getOrAwaitValueTest()

        assertThat(brochureList.getContentIfNotHandled()?.data).contains(brochure)
    }
}