package dev.ggomes.quotations.data.networking.responsemodels

import com.google.gson.annotations.SerializedName

data class EnvelopeResponse<T>(
    val success: T?,
    val error: Error?
) {
    data class Error(
        @SerializedName("error_code") val code: Int,
        val message: String
    )
}