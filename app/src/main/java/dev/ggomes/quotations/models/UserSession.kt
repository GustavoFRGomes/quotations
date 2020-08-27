package dev.ggomes.quotations.models

data class UserSession(
    val login: String, // Username
    val pic_url: String,
    val account_details: AccountDetails?
) {
    data class AccountDetails (
        val email: String
    )
}