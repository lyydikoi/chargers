package com.example.ergoen.ui.chargers

import androidx.lifecycle.*
import com.example.ergoen.data.db.entity.DbUser

class ChargersViewModel() : ViewModel() {
    private val ldIsReversedSorting by lazy { MutableLiveData<Boolean>(false) }
    //private val ldChargersList = chargersRepository.allBirds

    //private val medSortedChargers: MediatorLiveData<MutableList<DbUser>> = prepareSortedMediator()
    //var ldSortedChargersList: LiveData<MutableList<DbUser>> = medSortedChargers

    /*fun getSelectedCharger(position: Int): DbUser? {
        ldSortedChargersList.value?.let {
          return it[position]
        } ?: return null
    }*/

    fun setIsReversedSorting(isReversed: Boolean) {
        ldIsReversedSorting.value = isReversed
    }

    // This makes observing of both sources: sorting type and chargers list. If any changed, list is sorted.
    /*private fun prepareSortedMediator(): MediatorLiveData<MutableList<DbUser>> {
        val result = MediatorLiveData<MutableList<DbUser>>()

        result.addSource(ldChargersList) {
            result.value = sort(ldIsReversedSorting, ldChargersList)
        }
        result.addSource(ldIsReversedSorting) {
            result.value = sort(ldIsReversedSorting, ldChargersList)
        }
        return result
    }*/

    private fun sort(
        ldIsReversedSorting: MutableLiveData<Boolean>,
        ldChargersList: LiveData<List<DbUser>>
    ): MutableList<DbUser>? {
        ldIsReversedSorting.value?.let {
            return if (it) {
                ldChargersList.value?.asReversed()?.toMutableList()
            } else {
                ldChargersList.value?.toMutableList()
            }
        } ?: return null
    }

}