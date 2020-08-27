package dev.ggomes.quotations.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.ggomes.quotations.models.Quote
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashScreenViewModel: ViewModel() {

    private val _errorLiveData = MutableLiveData<Exception>()
    val errorLiveData: LiveData<Exception> = _errorLiveData

    private val _quoteLiveData = MutableLiveData<Quote>()

    fun getRandomQuote(): LiveData<Quote> {
        // TODO: Add the request to the repository (both DB and Network)
        _quoteLiveData.value = Quote("ID", "This is the most inspirational quote ever!", "Will Northman")

        return _quoteLiveData
    }

    fun delayMoveToNextScreen(): LiveData<Unit> {
        val delayNavigationLiveData = MutableLiveData<Unit>()

        Observable.just(1)
            .delay(10, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.computation())
            .subscribe {
                delayNavigationLiveData.value = Unit
            }

        return delayNavigationLiveData
    }
}