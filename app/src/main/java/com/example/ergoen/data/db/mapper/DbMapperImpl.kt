package com.example.ergoen.data.db.mapper

import com.example.ergoen.data.db.entity.DbCharger
import com.example.ergoen.data.db.entity.DbUser
import com.example.ergoen.domain.model.Charger
import com.example.ergoen.domain.model.User

class DbMapperImpl : DbMapper {
    override fun mapDomainUserToDb(user: User): DbUser {
        return with(user) {
            DbUser(id, name)
        }
    }

    override fun mapDbUserToDomain(user: DbUser): User {
        return with(user) {
            User(id, name)
        }
    }

    override fun mapDomainChargerToDb(charger: Charger): DbCharger {
        return with(charger) {
            DbCharger(id)
        }
    }

    override fun mapDbChargerToDomain(charger: DbCharger): Charger {
        return with(charger) {
            Charger(id)
        }
    }
}
