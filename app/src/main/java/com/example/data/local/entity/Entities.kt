package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_profile")
data class PlayerProfileEntity(
  @PrimaryKey val id: Int = 1,
  val name: String = "Voyager",
  val drachmas: Int = 100,
  val xp: Int = 0,
  val streakDays: Int = 1,
  val totalStars: Int = 0,
  val questionsAnswered: Int = 0,
  val correctAnswersCount: Int = 0,
  val currentIslandId: Int = 1
)

@Entity(tableName = "node_progress")
data class NodeProgressEntity(
  @PrimaryKey val nodeId: String,
  val islandId: Int,
  val isUnlocked: Boolean,
  val starsEarned: Int,
  val highScore: Int,
  val completedCount: Int = 0
)

@Entity(tableName = "unlocked_badges")
data class UnlockedBadgeEntity(
  @PrimaryKey val badgeId: String,
  val unlockedDate: String
)
