package com.example.corridafacil.view.mapa.viewModel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.corridafacil.data.repository.mapa.MapRepository
import com.example.corridafacil.view.mapa.viewModel.MapsViewModel

class MapViewModelFactory (private val mapaRepository: MapRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MapsViewModel::class.java)) {
            MapsViewModel(this.mapaRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}


