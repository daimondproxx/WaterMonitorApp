package com.example.watermonitorapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserParams(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val weight: Int,
    val gender: Boolean,
//    val isMaleSelected: Boolean,
)
