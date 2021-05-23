package com.example.ergoen.data.db.mapper

import com.example.ergoen.data.db.entity.DbCharger
import com.example.ergoen.data.db.entity.DbToken
import com.example.ergoen.domain.model.Charger
import com.example.ergoen.domain.model.Token

class DbMapperImpl : DbMapper {
    override fun mapDomainTokenToDb(tokenArg: Token): DbToken {
        with(tokenArg) {
            return DbToken(
                accessToken,
                tokenType,
                expiresIn,
                token
            )
        }
    }

    override fun mapDbTokenToDomain(tokenArg: DbToken): Token {
        with(tokenArg) {
            return Token(
                accessToken,
                tokenType,
                expiresIn,
                token
            )
        }
    }

    /*override fun mapDomainChargerToDb(charger: Charger): DbCharger {
        return with(charger) {
            DbCharger(this)
        }
    }

    override fun mapDbChargerToDomain(charger: DbCharger): Charger {
        return with(charger) {
            Charger(id)
        }
    }*/
}
