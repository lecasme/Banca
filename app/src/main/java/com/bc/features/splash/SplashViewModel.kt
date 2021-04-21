package com.bc.features.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bc.R
import com.bc.commons.BaseViewModel
import com.bc.repository.UserRepository
import kotlinx.coroutines.launch
import com.bc.models.Result
import com.bc.models.User
import com.bc.utils.Event

class SplashViewModel(private val splashRepository: UserRepository) : BaseViewModel(){

    val users = MutableLiveData<List<User>>()

    init {
        getSettings()
    }

    private fun getSettings() = viewModelScope.launch {
        when(val result =  splashRepository.getUsers(0)){
            is Result.Success -> {
                result.data.let { users.postValue(it) }
            }
            is Result.Error -> _snackBar.value = Event(R.string.error)
            is Result.Disconected -> {
                _snackBar.value = Event(R.string.connectivity)
            }
        }
    }

}