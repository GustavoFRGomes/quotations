package dev.ggomes.quotations.data.networking.requestmodels

data class SessionRequest(val user: User) {
    data class User(val login: String, val password: String)
}