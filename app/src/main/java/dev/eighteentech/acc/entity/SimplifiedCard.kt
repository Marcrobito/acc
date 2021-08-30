package dev.eighteentech.acc.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SimplifiedCard(
    val card_type: String,
    @PrimaryKey
    val title: String,
    val titleFontSize: Int,
    val titleTextColor: String,
    val description: String? = null,
    val descriptionFontSize: Int? = null,
    val descriptionTextColor: String? = null,
    val url: String? = null,
    val width: Int? = null,
    val height: Int? = null,
)