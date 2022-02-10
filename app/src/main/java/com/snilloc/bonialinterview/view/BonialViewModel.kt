package com.snilloc.bonialinterview.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snilloc.bonialinterview.data.cache.model.BrochureEntity
import com.snilloc.bonialinterview.data.BonialRepository
import com.snilloc.bonialinterview.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BonialViewModel @Inject constructor(private val repository: BonialRepository) : ViewModel() {

    //_brochures is private so that it cannot be changed outside this class
    //we expose brochures because it cannot be changed outside this class, we can only observe it
    private val _brochures = MutableLiveData<Resource<List<BrochureEntity>>>()
    val brochures: LiveData<Resource<List<BrochureEntity>>> = _brochures

    private val _filteredBrochures = MutableLiveData<Resource<List<BrochureEntity>>>()
    val filteredBrochures: LiveData<Resource<List<BrochureEntity>>> = _filteredBrochures

    fun getBrochures() {
        viewModelScope.launch {
            _brochures.postValue(Resource.loading())

            val response = repository.getBrochures()

            _brochures.postValue(response)
        }
    }

    fun getFilteredBrochures() {
        viewModelScope.launch(Dispatchers.IO) {
            _filteredBrochures.postValue(Resource.loading())

            val response = repository.getFilteredBrochures()

            _filteredBrochures.postValue(response)
        }
    }
}