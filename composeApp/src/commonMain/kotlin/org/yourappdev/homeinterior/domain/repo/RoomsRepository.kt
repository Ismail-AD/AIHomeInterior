package org.yourappdev.homeinterior.domain.repo

import org.yourappdev.homeinterior.data.local.entities.UserInfoEntity
import org.yourappdev.homeinterior.domain.model.Rooms

interface RoomsRepository {
    suspend fun getRoomsList(): Rooms
}