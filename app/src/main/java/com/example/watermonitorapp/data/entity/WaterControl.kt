package com.example.watermonitorapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class WaterControl(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val currentWater: Int,
    val totalWater: Int,
    val date: Long
)
