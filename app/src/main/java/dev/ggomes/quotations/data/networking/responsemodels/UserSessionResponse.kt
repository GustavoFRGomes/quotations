package dev.ggomes.quotations.data.networking.responsemodels

import com.google.gson.annotations.SerializedName

data class UserSessionResponse(
    @SerializedName(USER_TOKEN)
    val userToken: String?,
    @SerializedName("login")
    val username: String?,
    val email: String?
) {
    companion object {
        const val USER_TOKEN = "User-Token"
    }
}