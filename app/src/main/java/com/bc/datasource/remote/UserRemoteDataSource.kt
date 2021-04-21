package com.bc.datasource.remote


import com.bc.datasource.remote.service.UserService
import com.bc.models.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface UserRemoteDataSource {
    suspend fun getUsers(version: Int?) : List<User>
}

class UserRemoteDataSourceImpl(
        private val userService: UserService,
        private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : UserRemoteDataSource {

    override suspend fun getUsers(version: Int?): List<User> = withContext(ioDispatcher) { userService.getUsers() }

}