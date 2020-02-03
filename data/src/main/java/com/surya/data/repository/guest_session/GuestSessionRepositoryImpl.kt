package com.surya.data.repository.guest_session

import retrofit2.Response
import com.surya.data.entity.GuestSession
import com.surya.data.routes.NetworkServices

class GuestSessionRepositoryImpl constructor(
    private val service: NetworkServices
): GuestSessionRepository {

    override suspend fun getGuestSessionId(): Response<GuestSession> {
        return service.getGuestSessionId()
    }

}