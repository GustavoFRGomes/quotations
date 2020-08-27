package dev.ggomes.quotations.data.networking

import dev.ggomes.quotations.data.networking.requestmodels.SessionRequest
import dev.ggomes.quotations.data.networking.responsemodels.QuotesResponse
import dev.ggomes.quotations.data.networking.responsemodels.UserProfileResponse
import dev.ggomes.quotations.data.networking.responsemodels.UserSessionResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface FavQsApi {

    @POST("session")
    fun createSession(@Body body: SessionRequest): Single<UserSessionResponse>

    @GET("users/{login}")
    fun getUser(@Path("login") username: String): Single<UserProfileResponse>

    @GET("quotes")
    fun getRandomQuoteList(): Single<QuotesResponse>

    @GET("quotes")
    fun getQuotesBy(@Query("filter") query: String,
                    @Query("type") type: String = "tag"): Single<QuotesResponse>

    @PUT("quotes/{id}/fav")
    fun favouriteQuoteBy(@Path("id") id: Int): Single<QuotesResponse.QuoteObjectResponse>
}