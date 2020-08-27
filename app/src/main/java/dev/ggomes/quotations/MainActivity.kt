package dev.ggomes.quotations

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import dev.ggomes.quotations.views.SplashScreenFragment


class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(SplashScreenFragment.newInstance())
    }

    fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment, fragment.tag)
        transaction.commitAllowingStateLoss()
    }

    fun pushFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment, fragment.tag)
        transaction.addToBackStack(fragment.javaClass.name)
        transaction.commitAllowingStateLoss()
    }
}
