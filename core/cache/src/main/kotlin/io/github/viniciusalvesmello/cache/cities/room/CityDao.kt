package io.github.viniciusalvesmello.cache.cities.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query

@Dao
interface CityDao {
    @Query("select * from city order by name asc")
    fun pagingSource(): PagingSource<Int, CityEntity>
}