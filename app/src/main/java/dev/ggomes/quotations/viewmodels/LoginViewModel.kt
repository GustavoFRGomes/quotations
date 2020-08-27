package dev.ggomes.quotations.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.ggomes.quotations.base.BaseViewModel
import dev.ggomes.quotations.data.FavQsRepository
import dev.ggomes.quotations.data.networking.RetrofitClient
import dev.ggomes.quotations.data.networking.responsemodels.UserSessionResponse
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class LoginViewModel: BaseViewModel() {

    private val repository = FavQsRepository(RetrofitClient.api)

    private val _errorLiveData = MutableLiveData<Error>()
    val errorLiveData: LiveData<Error> = _errorLiveData

    fun login(username: String, password: String): LiveData<Boolean> {
        val loginResponseLiveData = MutableLiveData<Boolean>()

        repository.login(username, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<UserSessionResponse> {
                override fun onSuccess(response: UserSessionResponse) {
                    loginResponseLiveData.value = true
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    _errorLiveData.value = Error(e.message)
                }

            })

        return loginResponseLiveData
    }
}