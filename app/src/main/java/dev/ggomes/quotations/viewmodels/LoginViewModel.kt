package dev.ggomes.quotations.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val _errorLiveData = MutableLiveData<Error>()
    val errorLiveData: LiveData<Error> = _errorLiveData

    fun login(username: String, password: String): LiveData<Boolean> {
        val loginResponseLiveData = MutableLiveData<Boolean>()

        return loginResponseLiveData
    }
}