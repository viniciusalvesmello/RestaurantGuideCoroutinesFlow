package io.github.viniciusalvesmello.cache.cities

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.github.viniciusalvesmello.cache.cities.mapper.toCity
import io.github.viniciusalvesmello.cache.cities.model.City
import io.github.viniciusalvesmello.cache.cities.room.CityDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface CitiesCacheRepository {
    fun citiesPagingData(): Flow<PagingData<City>>
}

class CitiesCacheRepositoryImpl @AssistedInject constructor(
    private val cityDao: CityDao,
    @Assisted private val viewModelScope: CoroutineScope
) : CitiesCacheRepository {

    override fun citiesPagingData(): Flow<PagingData<City>> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                initialLoadSize = INITIAL_LOAD_SIZE
            )
        ) {
            cityDao.pagingSource()
        }.flow.map { pagingData ->
            pagingData.map { it.toCity() }
        }.cachedIn(viewModelScope)

    @AssistedFactory
    interface CitiesCacheRepositoryFactory {
        fun create(viewModelScope: CoroutineScope): CitiesCacheRepositoryImpl
    }

    companion object {
        private const val PAGE_SIZE = 10
        private const val PREFETCH_DISTANCE = 2
        private const val INITIAL_LOAD_SIZE = 10
    }
}