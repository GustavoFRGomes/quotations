package dev.ggomes.quotations.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.ggomes.quotations.base.BaseViewModel
import dev.ggomes.quotations.data.FavQsRepository
import dev.ggomes.quotations.data.db.AppDatabase
import dev.ggomes.quotations.data.networking.RetrofitClient
import dev.ggomes.quotations.models.Quote
import io.reactivex.android.schedulers.AndroidSchedulers

class HomeViewModel: BaseViewModel() {

    private val repository = FavQsRepository(RetrofitClient.api, AppDatabase.database)

    private val _errorLiveData = MutableLiveData<Error>()
    val errorLiveData: LiveData<Error> = _errorLiveData

    fun getPublicQuotes(): LiveData<List<Quote>> {
        val quotesLiveData = MutableLiveData<List<Quote>>()
        compositeDisposable.add(
            repository.getPublicQuotes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ response ->
                    quotesLiveData.value = response
                }, { error ->
                    _errorLiveData.value = Error(error.message)
                })
        )
        return quotesLiveData
    }
}