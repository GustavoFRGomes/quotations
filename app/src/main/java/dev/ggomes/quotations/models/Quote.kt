package dev.ggomes.quotations.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(
    @PrimaryKey val id: Int,
    val body: String,
    val author: String,
    val tags: List<String>
)