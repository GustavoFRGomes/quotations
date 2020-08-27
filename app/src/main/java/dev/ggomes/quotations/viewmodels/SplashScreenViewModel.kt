package dev.ggomes.quotations.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.ggomes.quotations.base.BaseViewModel
import dev.ggomes.quotations.data.FavQsRepository
import dev.ggomes.quotations.data.networking.RetrofitClient
import dev.ggomes.quotations.models.Quote
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashScreenViewModel: BaseViewModel() {

    // TODO: Inject instead of initializing here.
    private val repository = FavQsRepository(RetrofitClient.api)

    private val _errorLiveData = MutableLiveData<Error>()
    val errorLiveData: LiveData<Error> = _errorLiveData

    private val _quoteLiveData = MutableLiveData<Quote>()

    fun getRandomQuote(): LiveData<Quote> {
        repository.getRandomQuote()
            .subscribe(object: SingleObserver<Quote> {
                override fun onSuccess(t: Quote) {
                    _quoteLiveData.value = t
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    _errorLiveData.value = Error(e.message)
                }
            })

        return _quoteLiveData
    }

    fun delayMoveToNextScreenBy(seconds: Int): LiveData<Unit> {
        val delayNavigationLiveData = MutableLiveData<Unit>()

        compositeDisposable.add(Observable.just(1)
            .delay(seconds.toLong(), TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.computation())
            .subscribe {
                delayNavigationLiveData.value = Unit
            })

        return delayNavigationLiveData
    }
}