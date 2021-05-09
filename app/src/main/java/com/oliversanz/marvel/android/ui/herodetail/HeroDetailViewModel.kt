package com.oliversanz.marvel.android.ui.herodetail

import androidx.lifecycle.*
import com.oliversanz.marvel.domain.model.HeroModel
import com.oliversanz.marvel.domain.model.ResultEvent
import com.oliversanz.marvel.domain.usecases.GetHeroDetailUseCase

class HeroDetailViewModel(
    private val getHeroDetailUseCase: GetHeroDetailUseCase
) : ViewModel() {

    private val _hero: MutableLiveData<HeroModel> = MutableLiveData()
    val hero: LiveData<HeroModel> = _hero

    fun loadHero(heroId: String) = liveData(viewModelScope.coroutineContext) {
        try {
            _hero.value = getHeroDetailUseCase.invoke(heroId)
            this.emit(ResultEvent.Success)
        }
        catch (e: Exception) {
            this.emit(ResultEvent.Error(e))
        }
    }


}