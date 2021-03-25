package io.github.viniciusalvesmello.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.viniciusalvesmello.cache.cities.room.CityDao
import io.github.viniciusalvesmello.cache.cities.room.CityEntity

@Database(
    entities = [
        CityEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object {
        private const val DATABASE_NAME = "app_database.db"
        private const val START_DATABASE = "database/start_app_database.db"
        fun newInstance(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .createFromAsset(START_DATABASE)
                .build()
    }
}