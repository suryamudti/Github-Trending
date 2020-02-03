package com.surya.data.repository.guest_session

import retrofit2.Response
import com.surya.data.entity.GuestSession

interface GuestSessionRepository {
    suspend fun getGuestSessionId(): Response<GuestSession>
}