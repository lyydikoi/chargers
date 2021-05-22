package com.example.ergoen.data

import androidx.lifecycle.LiveData
import com.example.ergoen.domain.model.Charger
import com.example.ergoen.domain.repository.ChargersRepository

class ChargersRepositoryImpl : ChargersRepository {
    override fun allChargers(): LiveData<List<Charger>> {
        TODO("Not yet implemented")
    }
}