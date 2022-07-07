package com.nactem.acronym.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nactem.acronym.model.AcronymData
import com.nactem.acronym.model.LoadingState
import com.nactem.acronym.repository.AcronymApiRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AcronymViewModel(): ViewModel() {
    private val repository= AcronymApiRepository()
    val acronymLiveData = MutableLiveData<List<AcronymData>>()
    val loadingStateLiveData = MutableLiveData<LoadingState>()
    private val compositeDisposable = CompositeDisposable()

    fun getAbbreviation(word: String){
        loadingStateLiveData.postValue(LoadingState.LOADING)

        val observable = repository.getAbbriveiation(word)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onSuccess, this::onError)

        compositeDisposable.add(observable)
    }

    private fun onSuccess(acronymList: List<AcronymData>){
        if(acronymList.size > 0){
            acronymLiveData.postValue(acronymList)
            loadingStateLiveData.postValue(LoadingState.LOADED)
        }
        else{
            loadingStateLiveData.postValue(LoadingState.ERROR)
        }
    }

    private fun onError(throwable: Throwable){
        loadingStateLiveData.postValue(LoadingState.ERROR)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}