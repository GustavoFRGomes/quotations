package dev.ggomes.quotations.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.ggomes.quotations.base.BaseViewModel
import dev.ggomes.quotations.data.FavQsRepository
import dev.ggomes.quotations.data.db.AppDatabase
import dev.ggomes.quotations.data.networking.RetrofitClient
import dev.ggomes.quotations.data.networking.responsemodels.UserSessionResponse
import dev.ggomes.quotations.models.UserSession
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class LoginViewModel: BaseViewModel() {

    private val repository = FavQsRepository(RetrofitClient.api, AppDatabase.database)

    private val _errorLiveData = MutableLiveData<Error>()
    val errorLiveData: LiveData<Error> = _errorLiveData

    fun login(username: String, password: String): LiveData<Boolean> {
        val loginResponseLiveData = MutableLiveData<Boolean>()

        compositeDisposable.add(repository.login(username, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    loginResponseLiveData.value = true
                }, { error ->
                    _errorLiveData.value = Error(error.message)
                })
        )

        return loginResponseLiveData
    }
}