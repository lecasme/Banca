package com.bc.datasource.remote.service

import com.bc.models.User
import retrofit2.http.GET

interface UserService {

    @GET("users")
    suspend fun getUsers(): List<User>

}