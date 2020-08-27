package dev.ggomes.quotations.models

import android.os.Parcel
import android.os.Parcelable

data class Quote(
    val id: String?,
    val body: String?,
    val author: String?
)