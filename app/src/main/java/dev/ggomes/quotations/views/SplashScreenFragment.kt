package dev.ggomes.quotations.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dev.ggomes.quotations.MainActivity
import dev.ggomes.quotations.R
import dev.ggomes.quotations.viewmodels.SplashScreenViewModel
import kotlinx.android.synthetic.main.fragment_splash_screen.*

class SplashScreenFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: SplashScreenViewModel by viewModels()

        viewModel.getRandomQuote().observe(viewLifecycleOwner, Observer { quote ->
            quote_textview.text = quote.body
            author_textview.text = quote.author
        })

        viewModel.delayMoveToNextScreenBy(5).observe(viewLifecycleOwner, Observer {
            (activity as MainActivity).setFragment(HomeFragment.newInstance())
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(): SplashScreenFragment {
            return SplashScreenFragment()
        }
    }

}