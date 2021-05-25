package com.example.ergoen.ui.chargers

import androidx.lifecycle.*
import com.example.ergoen.domain.model.Charger
import com.example.ergoen.domain.model.LocationDetails
import com.example.ergoen.domain.repository.AuthRepository
import com.example.ergoen.domain.repository.ChargersRepository
import com.example.ergoen.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class ChargersViewModel(
    chargersRepository: ChargersRepository,
    authRepository: AuthRepository
) : BaseViewModel() {

    init {
        launchDataLoad {
            chargersRepository.updateChargers()
        }

        // Test expired token
        /*viewModelScope.launch {
            delay(1000 * 10)
            authRepository.updateToken(Token.EMPTY)
        }*/
    }

    // Control expired token
    val accessToken: LiveData<String> = authRepository
        .getTokenStream()
        .map { it.accessToken }
        .catch { }
        .asLiveData()

    val locationDetails: LiveData<LocationDetails> = chargersRepository
        .getLocationDetailsStream()
        .also {  }
        .catch {  }
        .asLiveData()

    val chargers: LiveData<List<Charger>> = chargersRepository
        .getChargersStream()
        .catch {  }
        .asLiveData()

    /*private*/ val _isReversedSorting by lazy { MutableLiveData(false) }
    //private val _chargersList = chargersRepository.closestChargers()

    //private val _sortedChargers: MediatorLiveData<MutableList<Charger>> = prepareSortedMediator()
    //var ldSortedChargersList: LiveData<MutableList<Charger>> = _sortedChargers

    /*fun getSelectedCharger(position: Int): Charger? {
        ldSortedChargersList.value?.let {
          return it[position]
        } ?: return null
    }*/

    fun setIsReversedSorting(isReversed: Boolean) {
       _isReversedSorting.value = isReversed
    }

    // This makes observing of both sources: sorting type and chargers list. If any changed, list is sorted.
    //private fun prepareSortedMediator(): MediatorLiveData<MutableList<Charger>> {
        /*val result = MediatorLiveData<MutableList<Charger>>()

        result.addSource(_chargersList) {
            result.value = sort(_isReversedSorting, _chargersList)
        }
        result.addSource(_isReversedSorting) {
            result.value = sort(_isReversedSorting, _chargersList)
        }
        return result*/
    //}

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
