package com.bc.repository

import com.bc.datasource.remote.UserRemoteDataSource
import com.bc.models.User
import com.bc.models.Result
import com.bc.utils.Connectivity
import java.lang.Exception

interface UserRepository {
    suspend fun getUsers(version: Int?): Result<List<User>>
}

class UserRepositoryImpl(private val remoteDataSource: UserRemoteDataSource, private val connectivity: Connectivity) : UserRepository{

    override suspend fun getUsers(version: Int?): Result<List<User>> {
        return if(connectivity.isOnline()){
            try {
                Result.Success(remoteDataSource.getUsers(version))
            }catch (e: Exception){
                Result.Error(e)
            }
        }else{
            Result.Disconected
        }
    }

}