package com.example.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val fruits = listOf("elma","armut","kiraz","muz","greyfurt","kavun")

    private var _counterLiveData: MutableLiveData<Int> = MutableLiveData(0)
    val counterLiveData:LiveData<Int> =_counterLiveData

    private val _fruitListLiveData: MutableLiveData<List<String>> = MutableLiveData(fruits)  // mainde kullanılmasın diye kapsülleme
    val fruitListLiveData:LiveData<List<String>> = _fruitListLiveData    // mainde bu şekilde kullanılabilir. backingproperty

    fun counterButtonClicked(){
        _counterLiveData.value?.let {
            _counterLiveData.postValue(it+1)
        }
    }

    fun addFruit(fruitText:String){
        _fruitListLiveData.value?.let {
            val list =it.toMutableList()
            list.add(fruitText)
            _fruitListLiveData.postValue(list)
        }
    }

}