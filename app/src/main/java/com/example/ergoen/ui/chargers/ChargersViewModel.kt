package com.example.ergoen.ui.chargers

import androidx.lifecycle.*
import com.example.ergoen.domain.model.Charger
import com.example.ergoen.domain.repository.ChargersRepository

class ChargersViewModel(
    chargersRepository: ChargersRepository
) : ViewModel() {
    private val _isReversedSorting by lazy { MutableLiveData<Boolean>(false) }
    private val _chargersList = chargersRepository.allChargers()

    private val _sortedChargers: MediatorLiveData<MutableList<Charger>> = prepareSortedMediator()
    var ldSortedChargersList: LiveData<MutableList<Charger>> = _sortedChargers

    fun getSelectedCharger(position: Int): Charger? {
        ldSortedChargersList.value?.let {
          return it[position]
        } ?: return null
    }

    fun setIsReversedSorting(isReversed: Boolean) {
       _isReversedSorting.value = isReversed
    }

    // This makes observing of both sources: sorting type and chargers list. If any changed, list is sorted.
    private fun prepareSortedMediator(): MediatorLiveData<MutableList<Charger>> {
        val result = MediatorLiveData<MutableList<Charger>>()

        result.addSource(_chargersList) {
            result.value = sort(_isReversedSorting, _chargersList)
        }
        result.addSource(_isReversedSorting) {
            result.value = sort(_isReversedSorting, _chargersList)
        }
        return result
    }

    private fun sort(
        ldIsReversedSorting: MutableLiveData<Boolean>,
        ldChargersList: LiveData<List<Charger>>
    ): MutableList<Charger>? {
        ldIsReversedSorting.value?.let {
            return if (it) {
                ldChargersList.value?.asReversed()?.toMutableList()
            } else {
                ldChargersList.value?.toMutableList()
            }
        } ?: return null
    }
}
