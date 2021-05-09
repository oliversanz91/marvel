package com.oliversanz.marvel.android.ui.herolist

import androidx.lifecycle.*
import com.oliversanz.marvel.domain.model.HeroListModel
import com.oliversanz.marvel.domain.model.ResultEvent
import com.oliversanz.marvel.domain.model.ResultObject
import com.oliversanz.marvel.domain.usecases.GetHeroListUseCase

class HeroListViewModel(
    private val getHeroListUseCase: GetHeroListUseCase
) : ViewModel() {

    private val _heroList: MutableLiveData<List<HeroListModel>> = MutableLiveData()
    val heroList: LiveData<List<HeroListModel>> = _heroList

    private var currentPage = 0

    fun loadHeroList() = liveData(viewModelScope.coroutineContext) {
        try {
            _heroList.value = getHeroListUseCase.invoke()
            this.emit(ResultEvent.Success)
        }
        catch (e: Exception) {
            this.emit(ResultEvent.Error(e))
        }
    }

    fun nextPage() = liveData<ResultObject<Boolean>>(viewModelScope.coroutineContext) {
        try {
            val result = getHeroListUseCase.invoke(++currentPage)
            _heroList.value = (_heroList.value.orEmpty()).toMutableList().apply { addAll(result) }
            this.emit(ResultObject.Success(result.isNotEmpty()))
        }
        catch (e: Exception) {
            this.emit(ResultObject.Error(e))
        }
    }

}