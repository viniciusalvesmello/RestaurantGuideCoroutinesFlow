package io.github.viniciusalvesmello.cache.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.viniciusalvesmello.cache.cities.CitiesCacheRepositoryImpl.CitiesCacheRepositoryFactory
import io.github.viniciusalvesmello.cache.cities.model.City
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CitiesCacheViewModel @Inject constructor(
    private val repositoryFactory: CitiesCacheRepositoryFactory
) : ViewModel() {

    private val repository: CitiesCacheRepository by lazy {
        repositoryFactory.create(viewModelScope)
    }

    val citiesPagingData: Flow<PagingData<City>>
        get() = repository.citiesPagingData()
}