package dev.ggomes.quotations.data.networking.responsemodels

import com.google.gson.annotations.SerializedName

data class UserProfileResponse(
    @SerializedName("login") val username: String?,
    @SerializedName("pic_url") val profileImageUrl: String?,
    val followers: Int?,
    val following: Int?,
    val pro: Boolean,
    @SerializedName("account_details") val accountDetails: AccountDetails?
) {
    data class AccountDetails(
        val email: String?,
        @SerializedName("private_favorites_count") val favoritesCount: Int?,
        @SerializedName("pro_expiration") val proExpiration: String?
    )
}