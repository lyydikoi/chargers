package com.example.ergoen.domain.model

import com.example.ergoen.data.db.entity.DbToken

data class Token(
    var accessToken : String,
    var tokenType : String,
    var expiresIn : Int,
    var token : String
) {
    companion object {
        val EMPTY = Token("", "", -1, "")
    }
}