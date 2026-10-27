package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.NodeProgressEntity
import com.example.data.local.entity.PlayerProfileEntity
import com.example.data.local.entity.UnlockedBadgeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OdysseyDao {
  @Query("SELECT * FROM player_profile WHERE id = 1 LIMIT 1")
  fun getPlayerProfile(): Flow<PlayerProfileEntity?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertOrUpdateProfile(profile: PlayerProfileEntity)

  @Query("SELECT * FROM node_progress")
  fun getAllNodeProgress(): Flow<List<NodeProgressEntity>>

  @Query("SELECT * FROM node_progress WHERE nodeId = :nodeId LIMIT 1")
  suspend fun getNodeProgress(nodeId: String): NodeProgressEntity?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertOrUpdateNodeProgress(progress: NodeProgressEntity)

  @Query("SELECT * FROM unlocked_badges")
  fun getUnlockedBadges(): Flow<List<UnlockedBadgeEntity>>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun unlockBadge(badge: UnlockedBadgeEntity)
}
