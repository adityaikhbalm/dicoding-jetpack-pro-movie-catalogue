package com.dicoding.bajp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.bajp.data.local.dao.FavoriteDAO
import com.dicoding.bajp.data.local.model.Favorite

@Database(
    entities = [Favorite::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favoriteDAO(): FavoriteDAO

    companion object {
        @Volatile
        private var instance: FavoriteDatabase? = null

        operator fun invoke(context: Context) =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FavoriteDatabase::class.java, "favorite"
            )
                .build()
    }
}