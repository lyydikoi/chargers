package com.example.ergoen.domain.repository

import androidx.lifecycle.LiveData
import com.example.ergoen.domain.model.Charger

interface ChargersRepository {
    fun allChargers(): LiveData<List<Charger>>
}