package io.github.viniciusalvesmello.cache.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.viniciusalvesmello.cache.cities.model.City
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CitiesCacheViewModel @Inject constructor(
    private val repository: CitiesCacheRepository
): ViewModel() {

    val citiesPagingData: Flow<PagingData<City>>
        get() = repository.citiesPagingData(viewModelScope)
}