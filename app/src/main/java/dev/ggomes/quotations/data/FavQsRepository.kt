package dev.ggomes.quotations.data

import dev.ggomes.quotations.data.db.AppDatabase
import dev.ggomes.quotations.data.networking.FavQsApi
import dev.ggomes.quotations.data.networking.requestmodels.SessionRequest
import dev.ggomes.quotations.data.networking.responsemodels.QuotesResponse
import dev.ggomes.quotations.data.networking.responsemodels.UserSessionResponse
import dev.ggomes.quotations.models.Quote
import dev.ggomes.quotations.models.UserSession
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavQsRepository(private val api: FavQsApi, private val db: AppDatabase) {

    fun login(username: String, password: String): Observable<UserSession> {
        return api.createSession(SessionRequest(SessionRequest.User(username, password)))
            .subscribeOn(Schedulers.io())
            .flatMapObservable {
                val userSession = UserSession(it.username!!,
                    "",
                    it.email!!,
                    false)

                db.sessionDao().saveUser(userSession)

                Observable.just(userSession)
            }
    }

    fun getRandomQuote(): Single<Quote> {
        return Observable.just(
            Quote(9102,
            "This is the most inspirational quote ever!",
            "Will Northman",
            emptyList()))
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .firstOrError()
    }

    fun getPublicQuotes(): Observable<List<Quote>> {
        return api.getRandomQuoteList().toObservable()
            .subscribeOn(Schedulers.io())
            .map {
                it.quotes.filter { quote ->
                    quote.id != null && quote.body != null && quote.author != null && quote.tags != null
                }.map { responseItem ->
                    Quote(responseItem.id!!,
                        responseItem.body!!,
                        responseItem.author!!,
                        responseItem.tags!!)
                }
            }
    }

    fun favoriteQuote(id: Int): Single<QuotesResponse.QuoteObjectResponse> {
        return api.favouriteQuoteBy(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}