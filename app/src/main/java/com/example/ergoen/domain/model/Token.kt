package com.example.ergoen.domain.model

data class Token(
    var accessToken: String,
    var tokenType: String,
    var expiresIn: Int,
    var token: String
) {
    companion object {
        val EMPTY = Token("", "", -1, "")
    }
}
