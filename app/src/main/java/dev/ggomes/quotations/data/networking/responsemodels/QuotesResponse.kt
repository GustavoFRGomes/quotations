package dev.ggomes.quotations.data.networking.responsemodels

import com.google.gson.annotations.SerializedName

data class QuotesResponse(
    val page: Int?,
    @SerializedName("last_page") val lastPage: Boolean?,
    val quotes: List<QuoteObjectResponse>
) {
    data class QuoteObjectResponse(
        val tags: List<String>?,
        val favorite: Boolean?,
        val id: Int?,
        val body: String?,
        val author: String?,
        @SerializedName("upvotes_count") val upvotesCount: Int?,
        @SerializedName("downvotes_count") val downvotesCount: Int?,
        @SerializedName("favorites_count") val favoritesCount: Int?,
        val url: String?
    )
}