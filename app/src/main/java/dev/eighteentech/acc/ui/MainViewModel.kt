package dev.eighteentech.acc.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.eighteentech.acc.entity.CardHolder
import dev.eighteentech.acc.entity.Response
import dev.eighteentech.acc.entity.SimplifiedCard
import dev.eighteentech.acc.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {

    private val _data = MutableLiveData<Response<List<SimplifiedCard>>>(Response.NotLoaded)
    val data : LiveData<Response<List<SimplifiedCard>>> get() = _data

    fun fetchData(){
        _data.value = Response.Loading
        viewModelScope.launch{
            _data.value = repository.fetchHome()
        }
    }

}