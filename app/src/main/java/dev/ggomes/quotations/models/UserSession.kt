package dev.ggomes.quotations.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class UserSession(
    @PrimaryKey val username: String, // Username
    val profileImageUrl: String,
    val email: String,
    val isPro: Boolean
)