package dev.ggomes.quotations

import android.app.Application

class QuotationsApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {
        lateinit var instance: QuotationsApplication
            private set
    }
}