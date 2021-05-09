package com.oliversanz.marvel.android.herolist

import androidx.lifecycle.*
import com.oliversanz.marvel.domain.model.HeroListModel
import com.oliversanz.marvel.domain.model.ResultEvent
import com.oliversanz.marvel.domain.usecases.GetHeroListUseCase

class HeroListViewModel(
    private val getHeroListUseCase: GetHeroListUseCase
) : ViewModel() {

    private val _heroList: MutableLiveData<List<HeroListModel>> = MutableLiveData()
    val heroList: LiveData<List<HeroListModel>> = _heroList

    fun loadHeroList() = liveData(viewModelScope.coroutineContext) {
        try {
            _heroList.value = getHeroListUseCase.invoke()
            this.emit(ResultEvent.Success)
        }
        catch (e: Exception) {
            this.emit(ResultEvent.Error(e))
        }
    }


}