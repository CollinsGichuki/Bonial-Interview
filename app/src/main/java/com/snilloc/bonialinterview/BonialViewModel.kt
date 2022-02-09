package com.snilloc.bonialinterview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snilloc.bonialinterview.domain.BonialRepository
import com.snilloc.bonialinterview.domain.model.BrochureData
import com.snilloc.bonialinterview.util.Event
import com.snilloc.bonialinterview.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BonialViewModel @Inject constructor(private val repository: BonialRepository) : ViewModel() {
    //_brochures is private so that it cannot be changed outside this class
    //we expose brochures because it cannot be changed outside this class, we can only observe it
    private val _brochures = MutableLiveData<Event<Resource<List<BrochureData>>>>()
    val brochures: LiveData<Event<Resource<List<BrochureData>>>> = _brochures

    fun getBrochures() {
        viewModelScope.launch {
            _brochures.postValue(Event(Resource.loading()))

            val response = repository.getBrochures()

            _brochures.postValue(Event(response))
        }
    }
}