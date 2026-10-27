package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.dao.OdysseyDao
import com.example.data.local.entity.NodeProgressEntity
import com.example.data.local.entity.PlayerProfileEntity
import com.example.data.local.entity.UnlockedBadgeEntity

@Database(
  entities = [
    PlayerProfileEntity::class,
    NodeProgressEntity::class,
    UnlockedBadgeEntity::class
  ],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun odysseyDao(): OdysseyDao

  companion object {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          AppDatabase::class.java,
          "wealth_odyssey.db"
        ).fallbackToDestructiveMigration().build()
        INSTANCE = instance
        instance
      }
    }
  }
}
